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
@secure()
param registryPassword string

// Reference the managed environment resource
resource environment 'Microsoft.App/managedEnvironments@2022-10-01' existing = {
  name: environmentName
}

// Create a container app resource
resource containerApp 'Microsoft.App/containerApps@2022-03-01' ={
  name: resourceName
  location: azureLocationName
  identity: {
    type: 'SystemAssigned'
  }
  properties:{
    managedEnvironmentId: environment.id
    configuration: {
      secrets: [
        { 
          name: 'registry-password'
          value: registryPassword
        }
      ]
      ingress: {
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
        }
      ]
      scale: {
        minReplicas: 0
        maxReplicas: 2
      }
    }
  }
}

output resourceId string = containerApp.id
output resourceName string = containerApp.name
output resourceFqdn string = containerApp.properties.configuration.ingress.fqdn
output managedIdentityId string = containerApp.identity.principalId
