// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.noq.backend.configs;

import com.azure.cosmos.CosmosClient;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.identity.ManagedIdentityCredentialBuilder;
import com.azure.spring.data.cosmos.config.AbstractCosmosConfiguration;
import com.azure.spring.data.cosmos.config.CosmosConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("prod")
@Configuration
@EnableConfigurationProperties(AzureCredentials.class)
@AllArgsConstructor
@Slf4j
public class CosmosSpringConfigurationProd extends AbstractCosmosConfiguration {
    private final AzureCredentials credentials;

    @Bean
    public CosmosClientBuilder cosmosClientBuilder() {
        String endpoint = System.getenv("COSMOS_DB_ACCOUNT_NAME");
        log.info("COSMOS_DB_ACCOUNT_NAME: {}", endpoint);
        if (endpoint == null) throw new NullPointerException("COSMOS_DB_ACCOUNT_NAME can not be null");
        return new CosmosClientBuilder()
                .endpoint(endpoint)
                .credential(new ManagedIdentityCredentialBuilder().build());
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
