param primaryLocation string = resourceGroup().location
param enableRbacAuthorization bool

@allowed([
  'standard'
  'premium'
])
param skuName string = 'standard'

@allowed([
  'A'
])
param skuFamily string = 'A'

param resourceName string

var tenantId = subscription().tenantId

//Azure Key Vault resource for secret management
resource keyVault 'Microsoft.KeyVault/vaults@2022-07-01' = {
  name: resourceName
  location: primaryLocation
  properties: {
    enabledForDeployment: true
    enabledForDiskEncryption: true
    enabledForTemplateDeployment: true
    enableRbacAuthorization: enableRbacAuthorization
    sku: {
      name: skuName
      family: skuFamily
    }
    enablePurgeProtection: true
    enableSoftDelete: true
    softDeleteRetentionInDays: 90
    tenantId: tenantId
  }
}

resource keyVaultLock 'Microsoft.Authorization/locks@2016-09-01' = {
  name: '${keyVault.name}-deletion-lock'
  scope: keyVault
  properties: {
    level: 'CanNotDelete'
    notes: 'Deletion of KeyVault resource ${keyVault.name} not allowed'
  }
}

output keyVaultId string = keyVault.id
output keyVaultName string = keyVault.name
output keyVaultUri string = keyVault.properties.vaultUri
output keyVaultResourceGroupName string = resourceGroup().name
