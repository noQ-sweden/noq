targetScope = 'resourceGroup'

@allowed([
  'West Europe'
  'Sweden Central'
])
@description('Select primary region to deploy resources in, default is West Europe')
param azureLocationName string = 'West Europe'

param resourceName string
param environmentName string
param containerAppName string
param containerImage string
param targetPort int
param hasExternalIngress bool

param registry string
param registryUsername string

param keyVaultName string

param allowedOrigins array = []

param environmentVariables array = []

param keyVaultSecretReferences array = []

var corsPolicy = empty(allowedOrigins) ? null : allowedOrigins

//Reference to the key vault resource
resource keyVault 'Microsoft.KeyVault/vaults@2023-07-01' existing = {
  name: keyVaultName
}

// Reference the managed environment resource
resource environment 'Microsoft.App/managedEnvironments@2022-10-01' existing = {
  name: environmentName
}

// Create a container app resource
resource containerApp 'Microsoft.App/containerApps@2023-05-01' ={
  name: resourceName
  location: azureLocationName
  identity: {
    type: 'SystemAssigned'
  }
  properties:{
    managedEnvironmentId: environment.id
    configuration: {
      secrets: keyVaultSecretReferences
      ingress: {
        corsPolicy: {
          allowedOrigins: corsPolicy!
        }
        targetPort: targetPort
        external: hasExternalIngress
        allowInsecure: false
      }
      registries: [
        {
          server: registry
          username: registryUsername
          passwordSecretRef: 'registry-password'
        }
      ]
    }
    template: {
      containers: [
        {
          image: containerImage
          name: containerAppName
          env: environmentVariables
        }
      ]
      scale: {
        minReplicas: 0
        maxReplicas: 2
      }
    }
  }
}

//Grant ContainerApp system assigned identity access to key vault
resource secretsRead 'Microsoft.Authorization/roleAssignments@2022-04-01' = {
  name: guid(keyVault.name, containerApp.name, '4633458b-17de-408a-b874-0445c86b69e6')
  scope: keyVault
  properties: {
    roleDefinitionId: resourceId('Microsoft.Authorization/roleDefinitions','4633458b-17de-408a-b874-0445c86b69e6')
    principalId: containerApp.identity.principalId
    principalType: 'ServicePrincipal'
  }
}

output resourceId string = containerApp.id
output resourceName string = containerApp.name
output resourceFqdn string = containerApp.properties.configuration.ingress.fqdn
output managedIdentityId string = containerApp.identity.principalId
