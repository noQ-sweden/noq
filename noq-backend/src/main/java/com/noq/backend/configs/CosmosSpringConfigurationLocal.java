// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.noq.backend.configs;

import com.azure.cosmos.CosmosClientBuilder;
import com.azure.spring.data.cosmos.config.AbstractCosmosConfiguration;
import com.azure.spring.data.cosmos.config.CosmosConfig;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("default")
@Configuration
@EnableConfigurationProperties(AzureCredentials.class)
@AllArgsConstructor
public class CosmosSpringConfigurationLocal extends AbstractCosmosConfiguration {
    private final AzureCredentials credentials;

    @Bean
    public CosmosClientBuilder cosmosClientBuilder() {
        return new CosmosClientBuilder()
                .endpoint(credentials.getServiceURI())
                .key(credentials.getPrimarySecretKey());
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

