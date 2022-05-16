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

// Main
const { ApolloServer } = require('apollo-server');
const { ApolloGateway } = require('@apollo/gateway');
const { RemoteGraphQLDataSource } = require('@apollo/gateway');
const { readFileSync } = require('fs');
const { Request } = require ('express');

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
  // Subscriptions are unsupported but planned for a future Gateway version.
  subscriptions: false
});

server.listen( {port: port} ).then(({ url }) => {
  console.log(`🚀 Graph Router ready at ${url}`);
}).catch(err => {console.error(err)});
