targetScope = 'resourceGroup'

@allowed([
  'West Europe'
  'Sweden Central'
])
@description('Select primary region to deploy resources in, default is West Europe')
param azureLocationName string = 'West Europe'

param resourceName string

//Azure Container Apps Environment resource defintion
resource containerAppEnvironment 'Microsoft.App/managedEnvironments@2022-10-01' = {
  name: resourceName
  location: azureLocationName
  properties: {
    appLogsConfiguration: {}
    zoneRedundant: false
  }
}

output resourceId string = containerAppEnvironment.id
output resourceName string = containerAppEnvironment.name
