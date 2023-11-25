targetScope = 'resourceGroup'

@description('Timestamp in format yyyyMMddTHHmmssZ to use as tag in deployment, defaults to utcNow')
param dateStamp string = utcNow('yyyyMMddTHHmmssZ')

@allowed([
  'Sweden Central'
])
@description('Select primary region to deploy resources in, default is West Europe')
param azureLocationName string = 'Sweden Central'

param keyVaultName string

param envShortName string

//Create user managed identities for backend app
module backendAppIdentity '../resource-templates/managed-identity-template.bicep' = {
  name: 'noq_bai_${dateStamp}'
  params: {
    resourceName: 'id-noq-backend-${toLower(envShortName)}'
    location: azureLocationName
  }
}

//Reference to the key vault resource
resource keyVault 'Microsoft.KeyVault/vaults@2023-07-01' existing = {
  name: keyVaultName
}

//Grant ContainerApp system assigned identity access to key vault
resource secretsRead 'Microsoft.Authorization/roleAssignments@2022-04-01' = {
  name: guid(keyVault.name, 'id-noq-backend', '4633458b-17de-408a-b874-0445c86b69e6')
  scope: keyVault
  properties: {
    roleDefinitionId: resourceId('Microsoft.Authorization/roleDefinitions','4633458b-17de-408a-b874-0445c86b69e6')
    principalId: backendAppIdentity.outputs.principalId
    principalType: 'ServicePrincipal'
  }
}

output backendAppIdentityId string = backendAppIdentity.outputs.principalId
output backendAppIdentityResourceId string = backendAppIdentity.outputs.resourceId
