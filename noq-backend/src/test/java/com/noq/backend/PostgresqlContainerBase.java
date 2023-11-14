package com.noq.backend;

import org.testcontainers.containers.PostgreSQLContainer;

/**
 * Starts a REAL Postgresql server using testcontainers library.
 * Use this sparsely for "higher level tests" like System tests.
 * Using this "everywhere" is NOT Recommended.
 * Unit tests should use Mocks whenever required certain behaviours to test various paths of execution
 */
abstract class PostgresqlContainerBase {
    static final PostgreSQLContainer<?> POSTGRE_SQL_CONTAINER;

    private static final String LATEST_DOCKER_IMAGE = "postgres:16";

    private static final String DDL_SCRIPT_PATH = "ddl/createTables.sql";

    static {
        POSTGRE_SQL_CONTAINER = new PostgreSQLContainer<>(LATEST_DOCKER_IMAGE);
        POSTGRE_SQL_CONTAINER
                .withDatabaseName("postgres")
                .withPassword("postgres")
                .withUsername("postgres")
                .withInitScript(DDL_SCRIPT_PATH)
                .start();

        System.setProperty("POSTGRES_URL", POSTGRE_SQL_CONTAINER.getJdbcUrl());
        System.setProperty("POSTGRES_USER", POSTGRE_SQL_CONTAINER.getUsername());
        System.setProperty("POSTGRES_PWD", POSTGRE_SQL_CONTAINER.getPassword());
    }
}
