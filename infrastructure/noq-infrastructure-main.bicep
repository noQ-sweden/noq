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

//Resource group for environment
var resourceGroupName = 'rg-noq-${toLower(envShortName)}'

//Creates a resource group in noQ subscription that will represent a container for the environment
module envResourceGroup 'resource-templates/resourcegroup-template.bicep' = {
name: 'noq_rg_${dateStamp}'
  scope: subscription()
  params: {
    primaryAzureRegionName: azureLocationName
    resoureGroupNames: [
      resourceGroupName
    ]
  }
}

//Creates a Azure Key Vault resource
module keyVault 'resource-templates/key-vault-template.bicep' = {
  name: 'noq_kv_${dateStamp}'
  dependsOn: [
    envResourceGroup
  ]
  scope: resourceGroup(resourceGroupName)
  params: {
    resourceName: 'kv-noq-${toLower(envShortName)}'
    azureLocationName: azureLocationName
    enableRbacAuthorization: true
    skuName: 'standard'
    skuFamily: 'A'    
  }
}

//Creates a Azure Storage Account and adds connection string and access keys to Key Vault
module storageAccount 'resource-templates/storageaccount-template.bicep' = {
  name: 'noq_st_${dateStamp}'
  dependsOn: [
    envResourceGroup
    keyVault
  ]
  scope: resourceGroup(resourceGroupName)
  params: {
    resourceName: 'stnoq${toLower(envShortName)}'
    skuName: 'Standard_LRS'
    publicNetworkAccess: 'Deny'
    enableHns: false
    keyVaultResource: keyVault.outputs.keyVaultResource
    containers: [
      'log'
    ]
    envSuffix: envShortName
    addToKeyVault: true
    dateStamp: dateStamp
    primaryLocation: azureLocationName
  }
}

//Creates a Azure Container Registry and adds url and credentials to Key Vault
module containerRegistry 'resource-templates/container-registry-template.bicep' = {
  name: 'noq_acr_${dateStamp}'
  dependsOn: [
    envResourceGroup
    keyVault
  ]
  scope: resourceGroup(resourceGroupName)
  params: {
    resourceName: 'acrnoq${toLower(envShortName)}'
    azureLocationName: azureLocationName
    keyVaultResource: keyVault.outputs.keyVaultResource
    acrSku: 'Basic'
    dateStamp: dateStamp
  }
}

//Creates a Azure Container Apps environment
module containerAppEnv './resource-templates/container-apps-environment-template.bicep' = {
  name: 'noq_cae_${dateStamp}'
  dependsOn: [
    envResourceGroup
    containerRegistry
    keyVault
  ]
  scope: resourceGroup(resourceGroupName)
  params: {
    resourceName: 'cae-noq-${toLower(envShortName)}'
    azureLocationName: azureLocationName
  }
}

output registryLoginServer string = containerRegistry.outputs.registryloginServer
output registryUsernameSecretName string = containerRegistry.outputs.usernameSecretName
output registryPwdSecretName string = containerRegistry.outputs.pwdPrimarySecretName
output keyVaultName string = keyVault.outputs.keyVaultName
