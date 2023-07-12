targetScope = 'resourceGroup'

param cosmosDbAccountName string

param resourceName string

param containers array

param ownerIdentityPrincipalId string

@description('Provide a GUID to use as the SQL Defintion Role Name')
param ownerRoleName string

resource cosmos 'Microsoft.DocumentDB/databaseAccounts@2022-11-15' existing = {
  name: cosmosDbAccountName
}

resource codb 'Microsoft.DocumentDB/databaseAccounts/sqlDatabases@2022-11-15' = {
  name: resourceName
  parent: cosmos
  properties: {
    resource: {
      id: resourceName
    }
  }

  //Create reqeusted containers
  resource containerResources 'containers@2022-11-15' = [for (container, i) in containers: {
    name: container.name
    properties: {
      resource: {
        id: container.name
        partitionKey: {
          paths: [
            container.partitionKeyPath
          ]
          kind: 'Hash'
        }
      }
    }
  }]
}

//Create a role definition that covers read and write to the created database with its containers
resource appRole 'Microsoft.DocumentDB/databaseAccounts/sqlRoleDefinitions@2022-11-15' = {
  name: ownerRoleName
  parent: cosmos
  properties: {
    type: 'BuiltInRole'
    roleName: '${resourceName}-appRole'
    assignableScopes: [
      '${cosmos.id}/dbs/${resourceName}'
    ]
    permissions: [
      {
        dataActions: [
          'Microsoft.DocumentDB/databaseAccounts/readMetadata'
          'Microsoft.DocumentDB/databaseAccounts/sqlDatabases/containers/items/*'
          'Microsoft.DocumentDB/databaseAccounts/sqlDatabases/containers/executeQuery'
          'Microsoft.DocumentDB/databaseAccounts/sqlDatabases/containers/readChangeFeed'
          'Microsoft.DocumentDB/databaseAccounts/sqlDatabases/containers/executeStoredProcedure'
        ]
      }
    ]
  }
}

//Create a role assignment that utilizes the role definition for the owner identity
resource roleAssignment 'Microsoft.DocumentDB/databaseAccounts/sqlRoleAssignments@2022-11-15' = {
  name: guid(resourceName, ownerIdentityPrincipalId, '-appRole-assignment')
  parent: cosmos
  properties: {
    roleDefinitionId: appRole.id
    principalId: ownerIdentityPrincipalId
    scope: '${cosmos.id}/dbs/${resourceName}'
  }
}


output resourceId string = codb.id
output resourceName string = codb.name
