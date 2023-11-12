# noQ backend

## Data store

noQ utilizes Postgresql Database as data store.

### Setup

- Create file `resources/application-secret.properties`

For linux

- ```cd scripts```
- ```docker compose up```
- ```curl -k https://localhost:8081/_explorer/emulator.pem > ./emulatorcert1.crt```
- ```sudo keytool -import -file emulatorcert1.crt -alias emulatorcert1 -keystore ~/.jdks/corretto-17.0.8.1/lib/security/cacerts -storepass changeit```

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
