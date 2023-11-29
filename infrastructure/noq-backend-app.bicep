targetScope = 'subscription'

@description('Timestamp in format yyyyMMddTHHmmssZ to use as tag in deployment, defaults to utcNow')
param dateStamp string = utcNow('yyyyMMddTHHmmssZ')

@description('Suffix for the environment eg. dev, tst or prod')
param envShortName string

@allowed([
  'Sweden Central'
])
@description('Select primary region to deploy resources in, default is West Europe')
param azureLocationName string = 'Sweden Central'

param appName string

param containerImage string

param containerEnvironment string

param registry string
param registryUsername string
param targetPort int
param allowedOrigin string = ''

param psqlServerName string
param psqlUsernameUri string

#disable-next-line secure-secrets-in-params
param psqlPasswordUri string

#disable-next-line secure-secrets-in-params
param registryPasswordUri string

param backendAppIdentityName string

//Resource group for environment
var resourceGroupName = 'rg-noq-${toLower(envShortName)}'

resource identity 'Microsoft.ManagedIdentity/userAssignedIdentities@2023-01-31' existing = {
  name: backendAppIdentityName
  scope: resourceGroup(resourceGroupName)
}

module containerApp './resource-templates/container-app-template.bicep' = {
  name: 'noq_be_app_${dateStamp}'
  scope: resourceGroup(resourceGroupName)
  params: {
    resourceName: 'ca-${appName}'
    containerAppName: appName
    targetPort: targetPort
    environmentName: containerEnvironment
    hasExternalIngress: true
    azureLocationName: azureLocationName
    managedIdentityResourceId: identity.id
    containerImage: containerImage
    registry: registry
    registryUsername: registryUsername
    allowedOrigins: [
      allowedOrigin
    ]
    keyVaultSecretReferences: [
      {
        name: 'registry-password'
        keyVaultUrl: registryPasswordUri
        identity: identity.id
      }
      {
        name: 'postgres-username'
        keyVaultUrl: psqlUsernameUri
        identity: identity.id
      }
      {
        name: 'postgres-password'
        keyVaultUrl: psqlPasswordUri
        identity: identity.id
      }
    ]
    environmentVariables: [
      {
        name: 'POSTGRES_URL'
        value: '${psqlServerName}.postgres.database.azure.com'
      }
      {
        name: 'POSTGRES_USER'
        secretRef: 'postgres-username'
      }
      {
        name: 'POSTGRES_PWD'
        secretRef: 'postgres-password'
      }
    ]
  }
}

output containerFqdn string = containerApp.outputs.resourceFqdn
