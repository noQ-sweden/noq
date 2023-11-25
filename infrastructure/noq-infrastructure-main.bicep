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
param postgresUsername string

@secure()
param postgresPassword string

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

//Create a Azure Cosmos DB account
module cosmosDbAccount 'resource-templates/cosmos-db-account-template.bicep' = {
  name: 'now_cdb_${dateStamp}'
  dependsOn: [
    envResourceGroup
    keyVault
  ]
  scope: resourceGroup(resourceGroupName)
  params: {
    keyVaultResource: keyVault.outputs.keyVaultResource
    resourceName: 'cosno-noq-${toLower(envShortName)}'
    azureLocationName: azureLocationName
  }
}

//Create a PostgrSQL server
module postgreSqlServer 'resource-templates/postgres-flexible-server-template.bicep' = {
  name: 'noq_psql_${dateStamp}'
  dependsOn: [
    envResourceGroup
    keyVault
  ]
  scope: resourceGroup(resourceGroupName)
  params: {
    dateStamp: dateStamp
    serverAdminLogin: postgresUsername
    serverAdminPassword: postgresPassword
    resourceName: 'psql-noq-${toLower(envShortName)}'
    azureLocationName: azureLocationName
    keyVaultResource: keyVault.outputs.keyVaultResource
    envShortName: envShortName
  }
}

module identities 'modules/managed-identity-create.bicep' = {
  name: 'noq_mi_${dateStamp}'
  dependsOn: [
    envResourceGroup
    keyVault
  ]
  scope: resourceGroup(resourceGroupName)
  params: {
    azureLocationName: azureLocationName
    envShortName: envShortName
    keyVaultName: keyVault.outputs.keyVaultName
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
output containerEnvironmentName string = containerAppEnv.outputs.resourceName
output cosmosDbAccountName string = cosmosDbAccount.outputs.resourceName
output cosmosDbAccountEndpoint string = cosmosDbAccount.outputs.accountUrl
output cosmosDbAccountKeySecretName string = cosmosDbAccount.outputs.primaryKeySecretName
output psqlServerName string = postgreSqlServer.outputs.postgresServerName

#disable-next-line outputs-should-not-contain-secrets
output postgresAdminUsernameUri string = postgreSqlServer.outputs.postgresAdminUsernameUri

#disable-next-line outputs-should-not-contain-secrets
output postgresAdminPasswordUri string = postgreSqlServer.outputs.postgresAdminPasswordUri

#disable-next-line outputs-should-not-contain-secrets
output containerRegistryPasswordUri string = containerRegistry.outputs.pwdPrimaryKeyVaultUri

output backendAppIdentityName string = identities.outputs.backendAppIdentityName
