targetScope = 'subscription'

@allowed([
  'West Europe'
  'North Europe'
  'Sweden Central'
])
@description('Select primary region to deploy resources in, default is West Europe')
param primaryAzureRegionName string = 'West Europe'

@description('Provide an array with wanted names of the resource groups')
param resoureGroupNames array

//Deploy requested resource group's
resource newRg 'Microsoft.Resources/resourceGroups@2021-01-01' = [for rg in resoureGroupNames: {
  name: rg
  location: primaryAzureRegionName
}]
