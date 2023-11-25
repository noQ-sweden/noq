targetScope = 'resourceGroup'

@description('Timestamp in format yyyyMMddTHHmmssZ to use as tag in deployment, defaults to utcNow')
param dateStamp string = utcNow('yyyyMMddTHHmmssZ')

@allowed([
  'West Europe'
  'Sweden Central'
])
@description('Select primary region to deploy resources in, default is West Europe')
param azureLocationName string = 'West Europe'

param keyVaultResource object

param resourceName string

var connectionStringSecretName = 'cosmosConnectionString'
var cosmosPrimaryKeySecretName = 'cosmosPrimaryKey'
var cosmosReadOnlyKeySecretName = 'cosmosReadOnlyKey'
var cosmosDocumentEndpointSecretName = 'cosmosDocumentEndpoint'

resource codb 'Microsoft.DocumentDB/databaseAccounts@2021-03-15' = {
  name: resourceName
  kind: 'GlobalDocumentDB'
  location: azureLocationName
  identity: {
    type: 'SystemAssigned'
  }
  properties: {
    capabilities: [
      {
        name: 'EnableServerless'
      }
    ]
    databaseAccountOfferType: 'Standard'
    locations: [
      {
        locationName: azureLocationName
        failoverPriority: 0
        isZoneRedundant: false
      }
    ]
    backupPolicy: {
      type: 'Periodic'
      periodicModeProperties: {
        backupIntervalInMinutes: 1440
        backupRetentionIntervalInHours: 48
      }
    }
    consistencyPolicy: {
      defaultConsistencyLevel: 'Strong'
    }
    enableFreeTier: false
  }
}

module cosmosSecrets 'key-vault-secret-template.bicep' = {
  scope: resourceGroup(keyVaultResource.resourceGroupName)
  name: 'noq_cosno_secrets_${dateStamp}'
  params: {
    keyVaultResourceName: keyVaultResource.resourceName
    secrets: [
      {
        name: connectionStringSecretName
        contentType: 'text/plain'
        value: 'AccountEndpoint=${codb.properties.documentEndpoint}/;AccountKey=${codb.listKeys().primaryMasterKey};'
      }
      {
        name: cosmosPrimaryKeySecretName
        contentType: 'text/plain'
        value: codb.listKeys().primaryMasterKey
      }
      {
        name: cosmosReadOnlyKeySecretName
        contentType: 'text/plain'
        value: codb.listKeys().primaryReadonlyMasterKey
      }
      {
        name: cosmosDocumentEndpointSecretName
        contentType: 'text/plain'
        value: codb.properties.documentEndpoint
      }
    ]
  }
}

output resourceId string = codb.id
output resourceName string = codb.name
output resourceIdentityId string = codb.identity.principalId
output connectionStringSecretName string = connectionStringSecretName
output primaryKeySecretName string = cosmosPrimaryKeySecretName
output readOnlyKeySecretName string = cosmosReadOnlyKeySecretName
output documentEndpointSecretName string = cosmosDocumentEndpointSecretName
output accountUrl string = codb.properties.documentEndpoint //https://******.documents.azure.com:443/
