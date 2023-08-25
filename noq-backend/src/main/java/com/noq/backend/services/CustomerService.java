package com.noq.backend.services;

import com.azure.core.credential.AzureKeyCredential;
import com.azure.cosmos.*;
import com.azure.cosmos.models.CosmosItemRequestOptions;
import com.azure.cosmos.models.CosmosItemResponse;
import com.azure.cosmos.models.CosmosQueryRequestOptions;
import com.azure.cosmos.models.PartitionKey;
import com.azure.cosmos.util.CosmosPagedFlux;
import com.noq.backend.DTO.CustomerDTO;
import com.noq.backend.configs.AzureCredentials;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CustomerService {
    private AzureCredentials azureCredentials;

    private static final String DATABASE_ID = "noq";
    private static final String CONTAINER_ID = "tmp_test";
 /*
    public CustomerDTO getItem(String id) {
        var cosmosContainer = setupCosmosClient(azureCredentials.getServiceURI(), azureCredentials.getPrimarySecretKey());
        String query = "SELECT * FROM c WHERE c.id = '" + id + "'";
        var items = cosmosContainer.queryItems(query, new CosmosQueryRequestOptions(), CustomerDTO.class);
        return items.stream().findFirst().orElse(null);
    }*/
  public CosmosPagedFlux<CustomerDTO> getAllItems() {
        var cosmosContainer = setupCosmosClient(azureCredentials.getServiceURI(), azureCredentials.getPrimarySecretKey());
        String query = "SELECT * FROM c";
        var items = cosmosContainer.queryItems(query, new CosmosQueryRequestOptions(), CustomerDTO.class);
        return items;
    }
 /*
    public List<CustomerDTO> addItem(CustomerDTO newItem) {
        var cosmosContainer = setupCosmosClient(azureCredentials.getServiceURI(), azureCredentials.getPrimarySecretKey());
        cosmosContainer.createItem(newItem);
        String query = "SELECT * FROM c";
        var items = cosmosContainer.queryItems(query, new CosmosQueryRequestOptions(), CustomerDTO.class);
        return items.stream().collect(Collectors.toList());
    }*/

    public Mono<CustomerDTO> updatePhoneNumber(String itemId, String partitionKey, String newPhoneNumber) {
        var cosmosContainer = setupCosmosClient(azureCredentials.getServiceURI(), azureCredentials.getPrimarySecretKey());

        var customerToUpdate = cosmosContainer
                .readItem(itemId, new PartitionKey(partitionKey), CustomerDTO.class)
                .map(CosmosItemResponse::getItem)
                .map(customerDTO -> new CustomerDTO(
                        customerDTO.id(),
                        customerDTO.customerId(),
                        customerDTO.firstName(),
                        customerDTO.lastName(),
                        customerDTO.email(),
                          newPhoneNumber,
                        customerDTO.address()))
                .flatMap(customerDTO -> cosmosContainer.replaceItem(customerDTO, itemId, new PartitionKey(partitionKey), new CosmosItemRequestOptions())
                        .map(CosmosItemResponse::getItem));


        return customerToUpdate;
    }

    private static CosmosAsyncContainer setupCosmosClient(String endpoint, String key) {
        CosmosAsyncClient client = new CosmosClientBuilder()
                .endpoint(endpoint)
                .credential(new AzureKeyCredential(key))
                .buildAsyncClient();

        CosmosAsyncDatabase database = client.getDatabase(DATABASE_ID);
        return database.getContainer(CONTAINER_ID);
    }
}
