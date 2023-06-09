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
@secure()
param registryPassword string
param targetPort int
param allowedOrigin string = ''

//Resource group for environment
var resourceGroupName = 'rg-noq-${toLower(envShortName)}'

module containerApp './resource-templates/container-app-template.bicep' = {
  name: 'noq_fe_app_${dateStamp}'
  scope: resourceGroup(resourceGroupName)
  params: {
    resourceName: 'ca-${appName}'
    containerAppName: appName
    targetPort: targetPort
    environmentName: containerEnvironment
    hasExternalIngress: true
    azureLocationName: azureLocationName
    containerImage: containerImage
    registry: registry
    registryUsername: registryUsername
    registryPassword: registryPassword
    allowedOrigins: [
      allowedOrigin
    ]
  }
}

output containerFqdn string = containerApp.outputs.resourceFqdn
