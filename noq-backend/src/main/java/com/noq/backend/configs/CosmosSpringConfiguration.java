// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.noq.backend.configs;

import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.DirectConnectionConfig;
import com.azure.spring.data.cosmos.config.AbstractCosmosConfiguration;
import com.azure.spring.data.cosmos.config.CosmosConfig;
import com.azure.spring.data.cosmos.repository.config.EnableReactiveCosmosRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableConfigurationProperties(AzureCredentials.class)
@EnableReactiveCosmosRepositories(basePackages = "com.noq.backend.repository")
@PropertySource("classpath:application-secret.properties")
public class CosmosSpringConfiguration extends AbstractCosmosConfiguration {


    private final AzureCredentials credentials;

    @Autowired
    public CosmosSpringConfiguration(AzureCredentials credentials) {
        this.credentials = credentials;
    }

    @Bean
    public CosmosClientBuilder cosmosBuildClient() {
        return new CosmosClientBuilder()
                .endpoint(credentials.getServiceURI())
                .key(credentials.getPrimarySecretKey())
                .directMode(DirectConnectionConfig.getDefaultConfig());

        //.credential(new ClientSecretCredentialBuilder()
        //        .clientId(properties.getClientId())
        //        .clientSecret(properties.getClientSecret())
        //        .tenantId(properties.getTenantId())
        //        .build());
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