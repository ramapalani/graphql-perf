#!/bin/sh

#Store Router Arguments
ROUTER_ARGUMENTS="$@"

# Don't give arguments here, so that it could be overridden in Docker run or Kubernetes Pods
# $@ gets all command line arguments passed to this script
echo "Router Arguments: ${ROUTER_ARGUMENTS}"
/dist/router ${ROUTER_ARGUMENTS}
