package com.noq.backend;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This is a System Tests / End-to-End Test that runs the whole Application with a Backend DB behind it.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("end-to-end")
class NoqBackendApplication_EndToEnd_IT extends PostgresqlContainerBase {

    @Autowired
    DataSource dataSource;

    @SneakyThrows
    @Test
    void contextLoads_And_DB_ConnectionSuccessful() {
        // This runs the whole application & verifies all dependencies were injected successfully
        // Also asserts below that connection to Postgres Datasource Exists
        assertThat(dataSource.getConnection().isClosed()).isFalse();
    }
}
