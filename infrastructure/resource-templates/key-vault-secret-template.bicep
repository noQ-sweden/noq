param keyVaultResourceName string

@description('Provide array of objects with format { name: secretName, contentType: text, value: secret }')
param secrets array

resource keyVault 'Microsoft.KeyVault/vaults@2019-09-01' existing = {
  name: keyVaultResourceName
  resource newSecret 'secrets' = [for secret in secrets: {
    name: secret.name
    properties: {
      contentType: secret.contentType
      value: secret.value
    }
  }]
}
