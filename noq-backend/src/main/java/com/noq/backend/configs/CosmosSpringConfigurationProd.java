// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.noq.backend.configs;

import com.azure.cosmos.CosmosAsyncClient;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.identity.ManagedIdentityCredential;
import com.azure.identity.ManagedIdentityCredentialBuilder;
import com.azure.spring.data.cosmos.config.AbstractCosmosConfiguration;
import com.azure.spring.data.cosmos.config.CosmosConfig;
import com.azure.spring.data.cosmos.repository.config.EnableReactiveCosmosRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableConfigurationProperties(AzureCredentials.class)
@EnableReactiveCosmosRepositories(basePackages = "com.noq.backend.repository", considerNestedRepositories = true)
@PropertySource("classpath:application-secret.properties")
@Profile("prod")
public class CosmosSpringConfigurationProd extends AbstractCosmosConfiguration {
    private final AzureCredentials credentials;

    @Autowired
    public CosmosSpringConfigurationProd(AzureCredentials credentials) {
        this.credentials = credentials;
    }

    @Bean
    public CosmosClientBuilder cosmosClientBuilder() {
        ManagedIdentityCredential credentialBuilder = new ManagedIdentityCredentialBuilder().build();

        String endpoint = System.getenv("COSMOS_DB_ACCOUNT_NAME");
        return new CosmosClientBuilder()
                .endpoint(endpoint)
                .credential(credentialBuilder);
    }

    @Bean
    public CosmosAsyncClient cosmosAsyncClient() {
        return cosmosClientBuilder().buildAsyncClient();
    }

    @Bean
    public CosmosConfig cosmosConfig() {
        return CosmosConfig.builder()
                .responseDiagnosticsProcessor(new ResponseDiagnosticsProcessorImpl(credentials))
                .enableQueryMetrics(credentials.isQueryMetricsEnabled())
                .build();
    }

    @Override
    protected String getDatabaseName() {
        return credentials.getDatabase();
    }
}
