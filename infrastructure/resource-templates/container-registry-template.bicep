@description('Provide a globally unique name of your Azure Container Registry')
param resourceName string

@description('Provide a location for the registry.')
param azureLocationName string = resourceGroup().location

@description('Provide a tier of your Azure Container Registry.')
@allowed([
  'Basic'
])
param acrSku string

param dateStamp string = utcNow('yyyyMMddTHHmmssZ')

param keyVaultResource object

resource acrResource 'Microsoft.ContainerRegistry/registries@2022-12-01' = {
  name: resourceName
  location: azureLocationName
  sku: {
    name: acrSku
  }
  identity: {
    type: 'SystemAssigned'
  }
  properties: {
    adminUserEnabled: true
  }
}

//Add needed credentials to Key Vault
module registryCredentials './key-vault-secret-template.bicep' = {
  name: 'noq_acr_creds_${dateStamp}'
  scope: resourceGroup(keyVaultResource.resourceGroupName)
  params: {
    keyVaultResourceName: keyVaultResource.resourceName
    secrets: [
      {
        name: 'acr-loginserver'
        contentType: 'text/plain'
        value: acrResource.properties.loginServer
      }
      {
        name: 'acr-username'
        contentType: 'text/plain'
        value: acrResource.listCredentials().username
      }
      {
        name: 'acr-password-primary'
        contentType: 'text/plain'
        value: acrResource.listCredentials().passwords[0].value
      }
      {
        name: 'acr-password-secondary'
        contentType: 'text/plain'
        value: acrResource.listCredentials().passwords[1].value
      }
    ]
  }
}

@description('Output the login server property')
output registryloginServer string = acrResource.properties.loginServer

@description('Output the principal id')
output resourceIdentityId string = acrResource.identity.principalId

output loginServerSecretName string = 'acr-loginserver'
output usernameSecretName string = 'acr-username'
output pwdPrimarySecretName string = 'acr-password-primary'
output pwdSecondarySecretName string = 'acr-password-secondary'
output pwdPrimaryKeyVaultUri string = '${keyVaultResource.resourceUri}/secrets/acr-password-primary'
