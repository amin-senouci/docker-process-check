#!/bin/bash

#################################################################################################################################
# 1. Run the project using Maven                                                                       #
#################################################################################################################################

## Can be run with Docker
#docker build -f Dockerfile -t v/nexthink-check-ws:1.0.0 .
#docker run -p 8080:8080  -it --rm -v "$PWD":/nexthink-check-ws  -w /nexthink-check-ws  v/nexthink-check-ws:1.0.0  mvn -U spring-boot:run

###################


## Or, directly with mvn to track server process performance
 mvn -U spring-boot:run
