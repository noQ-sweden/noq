targetScope = 'resourceGroup'

@allowed([
  'West Europe'
  'North Europe'
  'Sweden Central'
])
@description('Select primary region to deploy resources in, default is West Europe')
param primaryLocation string = 'West Europe'

param envSuffix string

param resourceName string

@allowed([
  'Standard_LRS'
])
param skuName string

param dateStamp string = utcNow('yyyyMMddTHHmmssZ')

param containers array

param enableHns bool = false

param addToKeyVault bool = false

param keyVaultResource object = {}

@allowed([
  'Deny'
  'Allow'
])
param publicNetworkAccess string

resource account 'Microsoft.Storage/storageAccounts@2022-09-01' = {
  name: resourceName
  kind: 'StorageV2'
  location: primaryLocation
  sku: {
    name: skuName
  }
  identity: {
    type: 'SystemAssigned'
  }
  properties: {
    supportsHttpsTrafficOnly: true
    isHnsEnabled: enableHns
    allowBlobPublicAccess: false
    networkAcls: {
      bypass: 'AzureServices'
      defaultAction: publicNetworkAccess
      virtualNetworkRules: []
      ipRules: []
    }
    minimumTlsVersion: 'TLS1_2'
    allowSharedKeyAccess: true
    encryption: {
      keySource: 'Microsoft.Storage'
      services: {
        blob: {
          enabled: true
          keyType: 'Account'
        }
        file: {
          enabled: true
          keyType: 'Account'
        }
      }
    }
  }
  resource blobService 'blobServices@2021-02-01' = {
    name: 'default'
    properties: { 
      changeFeed: {
        enabled: false
       }
       restorePolicy: {
        enabled: false
       }
       containerDeleteRetentionPolicy: {
        enabled: true
        days: 7
       }
       deleteRetentionPolicy: {
        enabled: true
        days: 7
       }
       isVersioningEnabled: false
    }
  }
}

//Create requested containers
resource container 'Microsoft.Storage/storageAccounts/blobServices/containers@2021-04-01' = [for containerName in containers: {
  name: '${account.name}/default/${containerName}'
  properties: {
    publicAccess: 'None'
    defaultEncryptionScope: '$account-encryption-key'
    denyEncryptionScopeOverride: false
  }
}]

module accessKey './key-vault-secret-template.bicep' = if (addToKeyVault) {
  name: 'cribwise_api_${resourceName}-accessKey-${dateStamp}'
  scope: resourceGroup(keyVaultResource.resourceGroupName)
  params: {
    keyVaultResourceName: keyVaultResource.resourceName
    secrets: [
      {
        name: startsWith(account.name,'st-') ? '${replace(account.name, '-${toLower(envSuffix)}', '')}-connection-string' : '${replace(account.name, '${toLower(envSuffix)}', '')}-connection-string'
        contentType: 'text/plain'
        value: 'DefaultEndpointsProtocol=https;AccountName=${account.name};AccountKey=${account.listKeys().keys[0].value};EndpointSuffix=${environment().suffixes.storage}'
      }
      {
        name: startsWith(account.name,'st-') ? '${replace(account.name, '-${toLower(envSuffix)}', '')}-primary-key' : '${replace(account.name, '${toLower(envSuffix)}', '')}-primary-key'
        contentType: 'text/plain'
        value: account.listKeys().keys[0].value
      }
      {
        name: startsWith(account.name,'st-') ? '${replace(account.name, '-${toLower(envSuffix)}', '')}-secondary-key' : '${replace(account.name, '${toLower(envSuffix)}', '')}-secondary-key'
        contentType: 'text/plain'
        value: account.listKeys().keys[1].value
      }
    ]
  }
}

output resourceId string = account.id
output resourceName string = account.name
output primaryAccessKeyReference string = addToKeyVault ? startsWith(account.name,'st-') ? '${replace(account.name, '-${toLower(envSuffix)}', '')}-primary-key' : '${replace(account.name, '${toLower(envSuffix)}', '')}-primary-key' : ''
output secondaryAccessKeyReference string = addToKeyVault ? startsWith(account.name,'st-') ? '${replace(account.name, '-${toLower(envSuffix)}', '')}-secondary-key' : '${replace(account.name, '${toLower(envSuffix)}', '')}-secondary-key' : ''
output connetionStringReference string = addToKeyVault ? startsWith(account.name,'st-') ? '${replace(account.name, '-${toLower(envSuffix)}', '')}-connection-string' : '${replace(account.name, '${toLower(envSuffix)}', '')}-connection-string' : ''
output blobEndpointUrl string = account.properties.primaryEndpoints.blob
output dfsEndpointUrl string = account.properties.primaryEndpoints.dfs
