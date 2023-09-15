package com.noq.backend.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "azure")
public class AzureCredentials {
    private String serviceURI;
    private String primarySecretKey;
    private String database;
//    @Value("${COSMOS_DB_ACCOUNT_ENDPOINT}")
//    private String accountEndPoint;
    private boolean queryMetricsEnabled;
    private boolean responseDiagnosticsEnabled;
}
