# Camel Microservices Example

This example demonstrates an microservice example using Apache Camel framework. It uses `rest` DSL for configuring route for each `http` verb appropriately.

### Create Resource
```shell script
curl -v -X POST 'http://localhost:8080/api/users' --data '{ "firstName": "Ashish","lastName": "nayyar","email": "ABC@XYZ.COM","enabled": true}' -H 'Content-Type: application/json'
```
### Exception handling
```shell script
curl -v -X POST 'http://localhost:8080/api/users' --data '{ "firstName": "Ashish","lastName": "nayyar","email": "ABC@XYZ.COM","enabled": true}' -H 'Content-Type: application/json'
```
### Swagger Documentation
```shell script
# Yaml
http://localhost:8080/api/docs/swagger.yaml

# Json
http://localhost:8080/api/docs/swagger.json
```
