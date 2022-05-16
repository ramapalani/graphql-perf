#!/bin/bash

echo "Test subgraph"
curl --request POST \
  --url http://localhost:8080/ \
  --header 'Content-Type: application/json' \
  --data '{"query":"query {\n  shows {\n    id\n    title\n  }\n}"}'

echo "Test supergraph Introspection"
curl --request POST \
  --url http://localhost:4000/ \
  --header 'Content-Type: application/json' \
  --data '{"query":"{\n  __schema {\n    types {\n      name\n    }\n  }\n}"}'

echo "Test supergraph"
curl --request POST \
  --url http://localhost:4000/ \
  --header 'Content-Type: application/json' \
  --data '{"query":"query {\n  shows {\n    id\n    title\n  }\n}"}'
