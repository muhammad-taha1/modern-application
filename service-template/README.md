# service-template

This is a template for generating a new service depending on the needs of the modern app.

The template itself can be started as a service, but its main intention is to be used to generate boilerplate code,
enforce package structure based on hexagonal architecture and a docker compose for local development.

## How to generate service from service template

Simply run `./generate-service.sh` while under project's root directory. You will be asked to enter groupId and artifactId of the project. Once completed, 
a new project will be created in the directory above.

