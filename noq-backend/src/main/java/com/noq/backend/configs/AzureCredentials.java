package com.noq.backend.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "azure")
public class AzureCredentials {
    private String serviceURI;
    private String primarySecretKey;
    private String database;
    private boolean queryMetricsEnabled;
    private boolean responseDiagnosticsEnabled;
}
