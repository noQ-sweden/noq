package com.noq.backend.configs;

import com.azure.spring.data.cosmos.core.ResponseDiagnostics;
import com.azure.spring.data.cosmos.core.ResponseDiagnosticsProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ResponseDiagnosticsProcessorImpl implements ResponseDiagnosticsProcessor {
//    private static final Logger logger = LoggerFactory.getLogger(CosmosSpringConfigurationLocal.class);
    private final AzureCredentials credentials;

    public ResponseDiagnosticsProcessorImpl(AzureCredentials credentials) {
        this.credentials = credentials;
    }

    @Override
    public void processResponseDiagnostics(@Nullable ResponseDiagnostics responseDiagnostics) {
        if (credentials.isResponseDiagnosticsEnabled()) {
//            logger.info("Response Diagnostics {}", responseDiagnostics);
        }
    }
}
