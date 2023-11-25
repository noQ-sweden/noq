targetScope = 'resourceGroup'

@description('Timestamp in format yyyyMMddTHHmmssZ to use as tag in deployment, defaults to utcNow')
param dateStamp string = utcNow('yyyyMMddTHHmmssZ')

@allowed([
  'West Europe'
  'Sweden Central'
])
@description('Select primary region to deploy resources in, default is West Europe')
param azureLocationName string = 'West Europe'

param resourceName string

param keyVaultResource object

param serverAdminLogin string
@secure()
param serverAdminPassword string

param envShortName string

var userNameSecretName = 'postgres-username'
var passwordSecretName = 'postgres-password'

resource postgresServer 'Microsoft.DBforPostgreSQL/flexibleServers@2022-12-01' = {
  name: resourceName
  location: azureLocationName
  sku: {
    name: 'Standard_B1ms'
    tier: 'Burstable'
  }
  properties: {
    version: '16'
    administratorLogin: serverAdminLogin
    administratorLoginPassword: serverAdminPassword
    createMode: 'Create'
    storage: {
      storageSizeGB: 32
    }
    highAvailability: {
      mode: 'Disabled'
    }
  }
  resource fwAllowAll 'firewallRules@2022-12-01' = if (envShortName == 'dev') {
    name: 'AllowAll_dev'
    properties: {
      startIpAddress: '0.0.0.0'
      endIpAddress: '255.255.255.255'
    }
  }
}

//Add username and password to Key Vault for later reference
module secrets 'key-vault-secret-template.bicep' = {
  scope: resourceGroup(keyVaultResource.resourceGroupName)
  name: 'noq_psql_secrets_${dateStamp}'
  params: {
    keyVaultResourceName: keyVaultResource.resourceName
    secrets: [
      {
        name: userNameSecretName
        contentType: 'text/plain'
        value: serverAdminLogin
      }
      {
        name: passwordSecretName
        contentType: 'text/plain'
        value: serverAdminPassword
      }
    ]
  }
}

output postgresServerName string = postgresServer.name
output postgresServerFQDN string = postgresServer.properties.fullyQualifiedDomainName

#disable-next-line outputs-should-not-contain-secrets
output postgresAdminUsernameUri string = '${keyVaultResource.resourceUri}secrets/${userNameSecretName}'

#disable-next-line outputs-should-not-contain-secrets
output postgresAdminPasswordUri string = '${keyVaultResource.resourceUri}secrets/${passwordSecretName}'
