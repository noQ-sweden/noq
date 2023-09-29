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

@secure()
param registryPassword string
param registryUsername string
param registry string

param appName string

param containerEnvironment string
param containerImage string
param targetPort int

param allowedOrigin string = ''
param hasExternalIngress bool = true

param backendUrl string

//Resource group for environment
var resourceGroupName = 'rg-noq-${toLower(envShortName)}'

module containerApp './resource-templates/container-app-template.bicep' = {
  name: 'noq_${appName}_${dateStamp}'
  scope: resourceGroup(resourceGroupName)
  params: {
    resourceName: 'ca-${appName}'
    containerAppName: appName
    targetPort: targetPort
    environmentName: containerEnvironment
    hasExternalIngress: hasExternalIngress
    azureLocationName: azureLocationName
    containerImage: containerImage
    registry: registry
    registryUsername: registryUsername
    registryPassword: registryPassword
    allowedOrigins: [
      allowedOrigin
    ]
    environmentVariables: [
      {
        name: 'API_BASE_URL'
        value: backendUrl
      }
    ]
  }
}

output containerFqdn string = containerApp.outputs.resourceFqdn
