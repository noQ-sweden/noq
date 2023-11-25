targetScope = 'resourceGroup'

@allowed([
  'Sweden Central'
])
@description('Select primary region to deploy resources in')
param location string = 'Sweden Central'

@minLength(3)
@maxLength(128)
@description('Name of the managed identity to create')
param resourceName string

resource identity 'Microsoft.ManagedIdentity/userAssignedIdentities@2023-01-31' = {
  name: resourceName
  location: location
}

output resourceName string = identity.name
output resourceId string = identity.id
output clientId string = identity.properties.clientId
output principalId string = identity.properties.principalId
