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
      ingress: {
        targetPort: targetPort
        external: hasExternalIngress
        allowInsecure: false
      }
    }
    template: {
      containers: [
        {
          image: containerImage
          name: containerAppName
        }
      ]
    }
  }
}

// Assign container app MI AcrPull role
resource containerAppAcrPullRoleAssignment 'Microsoft.Authorization/roleAssignments@2020-04-01-preview' = {
  name: guid(containerApp.id, 'AcrPull')
  scope: resourceGroup()
  properties: {
    roleDefinitionId: subscriptionResourceId('Microsoft.Authorization/roleDefinitions', '7f951dda-4ed3-4680-a7ca-43fe172d538d')
    principalId: containerApp.identity.principalId
  }
}

output resourceId string = containerApp.id
output resourceName string = containerApp.name
output resourceFqdn string = containerApp.properties.configuration.ingress.fqdn
output managedIdentityId string = containerApp.identity.principalId
