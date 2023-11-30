# noQ backend
REST:ful API to support noQ frontend perform functions like Finding a Host, Administrating Hosts and Users Profiles.

## Data store
noQ utilizes Postgresql Database as data store.

### Requirements
- Java 17 (Soon to be upgraded to Java 21)
- Maven
- JAVA_HOME environment variable points to the correct Java installation

### API Documentation Links
These are available once the server is running successfully
- [Swagger UI for API Documentation](http://localhost:8080/swagger-ui/index.html)
- [API Documentation JSON](http://localhost:8080/api-docs)

### Building the project
```
mvn clean install
```

## Running the tests
```
mvn clean verify
```

## Run the application
### Using java jar command
```
java -jar noq-backend-0.0.1.jar
```
### Using Docker
##### Build and Push the Docker image
```
docker build -t noq-backend:0.0.1 .
docker push <INSERT-your-docker-registry-location>/noq-backend:0.0.1
```
##### Run Docker container
```
docker run -p 8080:8080 -dit noq-backend:0.0.1
```

### Provision new containers

If a new container need to be provisioned, follow the steps below:
Edit the [noq-backend-app-bicep](../infrastructure/noq-backend-app.bicep) file and add a new object to the containers
array. The object have to contain properties "name" and "partitionKeyPath". See example below on line 10 to 13. Here a
new container with name `tmp_test` is provisioned and the property `id` is used as partition key.

TODO steps to setup Postgresql database in Azure Cloud
### Authentication
TODO
### Development tools
#### Data Explorer

[pgadmin](https://www.pgadmin.org/) is a graphical tool to see details inside a postgresql database.
