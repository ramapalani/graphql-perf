// Open Telemetry (optional)
const { ApolloOpenTelemetry } = require('supergraph-demo-opentelemetry');

if (process.env.APOLLO_OTEL_EXPORTER_TYPE) {
  new ApolloOpenTelemetry({
    type: 'router',
    name: 'router',
    exporter: {
      type: process.env.APOLLO_OTEL_EXPORTER_TYPE, // console, zipkin, collector
      host: process.env.APOLLO_OTEL_EXPORTER_HOST,
      port: process.env.APOLLO_OTEL_EXPORTER_PORT,
    }
  }).setupInstrumentation();
}

// Prometheus
const express = require('express')
const http = require('http')
const https = require('https')
const promClient = require('prom-client')
const gcStats = require('prometheus-gc-stats')

const metricApp = express()
metricApp.use('/actuator/prometheus', async (req, res) => {
  res.writeHead(200, { 'Content-Type': 'text/plain' })
  res.end(promClient.register.metrics())
})

let metricServer
if (process.env.SSL_ENABLED) {
  metricServer = https.createServer(
    {
      key: readFileSync(process.env.SSL_KEY_PATH),
      cert: readFileSync(process.env.SSL_CERT_PATH)
    },
    metricApp
  )
} else {
  metricServer = http.createServer(metricApp)
}

metricServer.listen(8490, () => {
  console.info(`Metrics server listening on ${8490}`)
})

promClient.collectDefaultMetrics()
const startGcStats = gcStats(promClient.register)
startGcStats()

// Main
const { ApolloServer } = require('apollo-server');
const { ApolloGateway } = require('@apollo/gateway');
const { RemoteGraphQLDataSource } = require('@apollo/gateway');
const { readFileSync } = require('fs');
const { Request } = require ('express');
require('console-stamp')(console);

const port = process.env.APOLLO_PORT || 4000;
const embeddedSchema = process.env.APOLLO_SCHEMA_CONFIG_EMBEDDED == "true" ? true : false;

const config = {
  buildService({ url }) {
    return new CustomDataSource({ url });
  }
};

if (embeddedSchema){
  const supergraph = "/etc/config/supergraph.graphql"
  config['supergraphSdl'] = readFileSync(supergraph).toString();
  console.log('Starting Apollo Gateway in local mode ...');
  console.log(`Using local: ${supergraph}`)
} else {
  console.log('Starting Apollo Gateway in managed mode ...');
}

class CustomDataSource extends RemoteGraphQLDataSource {
  willSendRequest({ request, context }) {
    let x_latency = context.customHeaders.headers['x_latency'];
    if (typeof x_latency != 'undefined') {
      request.http.headers.set('x_latency', x_latency);
    }
  }
}

const gateway = new ApolloGateway(config);

const loggingPlugin = {
  // Fires whenever a GraphQL request is received from a client.
  async requestDidStart(requestContext) {
    const startTime = process.hrtime();

    return {
      async willSendResponse(requestContext) {
        if (requestContext.operationName == "IntrospectionQuery") {
          return;
        }
        const requestHrTime = process.hrtime(startTime)
        const requestExecutionTime = requestHrTime[0] * 1000 + requestHrTime[1] / 1000000
        const currentTimeInMilliseconds=Date.now();
        console.log("epoch="+currentTimeInMilliseconds + ", operationName="+requestContext.operationName + ", elapsedTime="+requestExecutionTime);
      }
    }
  },
};

const server = new ApolloServer({
  gateway,
  debug: true,
  context: ({ req }) => ({
    req,
    customHeaders: {
      headers: {
        ...req.headers,
      },
    },
  }),
  plugins: [
    loggingPlugin
  ],
  // Subscriptions are unsupported but planned for a future Gateway version.
  subscriptions: false
});

server.listen( {port: port} ).then(({ url }) => {
  console.log(`🚀 Graph Router ready at ${url}`);
}).catch(err => {console.error(err)});

