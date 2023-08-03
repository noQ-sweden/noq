package com.noq.backend.services;

import com.azure.cosmos.CosmosClient;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.CosmosContainer;
import com.azure.cosmos.CosmosDatabase;
import com.azure.cosmos.models.CosmosItemRequestOptions;
import com.azure.cosmos.models.CosmosItemResponse;
import com.azure.cosmos.models.CosmosQueryRequestOptions;
import com.azure.cosmos.models.PartitionKey;
import com.noq.backend.DTO.CustomerDTO;
import com.noq.backend.configs.AzureCredentials;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CustomerService {
    private AzureCredentials azureCredentials;

    private static final String DATABASE_ID = "noq";
    private static final String CONTAINER_ID = "customers";

    public CustomerDTO getItem(String id) {
        var cosmosContainer = setupCosmosClient(azureCredentials.getServiceURI(), azureCredentials.getPrimarySecretKey());
        String query = "SELECT * FROM c WHERE c.customerId = '" + id + "'";
        var items = cosmosContainer.queryItems(query, new CosmosQueryRequestOptions(), CustomerDTO.class);
        return items.stream().findFirst().orElse(null);
    }

    public List<CustomerDTO> getAllItems() {
        var cosmosContainer = setupCosmosClient(azureCredentials.getServiceURI(), azureCredentials.getPrimarySecretKey());
        String query = "SELECT * FROM c";
        var items = cosmosContainer.queryItems(query, new CosmosQueryRequestOptions(), CustomerDTO.class);
        return items.stream().collect(Collectors.toList());
    }

    public List<CustomerDTO> addItem(CustomerDTO newItem) {
        var cosmosContainer = setupCosmosClient(azureCredentials.getServiceURI(), azureCredentials.getPrimarySecretKey());
        cosmosContainer.createItem(newItem);
        String query = "SELECT * FROM c";
        var items = cosmosContainer.queryItems(query, new CosmosQueryRequestOptions(), CustomerDTO.class);
        return items.stream().collect(Collectors.toList());
    }

    public CustomerDTO updatePhoneNumber(String itemId, String partitionKey, String newPhoneNumber) {
        var cosmosContainer = setupCosmosClient(azureCredentials.getServiceURI(), azureCredentials.getPrimarySecretKey());

        CustomerDTO customerToUpdate = cosmosContainer.readItem(itemId, new PartitionKey(partitionKey), CustomerDTO.class).getItem();
        CustomerDTO updatedCustomer = new CustomerDTO(
                customerToUpdate.id(),
                customerToUpdate.customerId(),
                customerToUpdate.firstName(),
                customerToUpdate.lastName(),
                customerToUpdate.email(),
                newPhoneNumber,
                customerToUpdate.address()
        );

        return cosmosContainer.replaceItem(updatedCustomer, itemId, new PartitionKey(partitionKey), new CosmosItemRequestOptions()).getItem();
    }

    private static CosmosContainer setupCosmosClient(String endpoint, String key) {
        CosmosClient cosmosClient = new CosmosClientBuilder()
                .endpoint(endpoint)
                .key(key)
                .buildClient();
        cosmosClient.getDatabase(DATABASE_ID);
        CosmosDatabase cosmosDatabase = cosmosClient.getDatabase(DATABASE_ID);
        return cosmosDatabase.getContainer(CONTAINER_ID);
    }
}
