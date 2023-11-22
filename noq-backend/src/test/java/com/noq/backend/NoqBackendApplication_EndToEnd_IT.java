package com.noq.backend;

import com.noq.backend.models.Host;
import com.noq.backend.models.User;
import com.noq.backend.repositories.HostRepository;
import com.noq.backend.repositories.UserRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This is a System Tests / End-to-End Test that runs the whole Application with a Backend DB behind it.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("end-to-end")
class NoqBackendApplication_EndToEnd_IT extends PostgresqlContainerBase {

    @Autowired
    DataSource dataSource;

    @Autowired
    UserRepository userRepository;

    @Autowired
    HostRepository hostRepository;

    @SneakyThrows
    @Test
    void contextLoads_And_DB_ConnectionSuccessful() {
        // This runs the whole application & verifies all dependencies were injected successfully
        // Also asserts below that connection to Postgres Datasource Exists
        assertThat(dataSource.getConnection().isClosed()).isFalse();
    }

    @Test
    void shouldSuccessfullySetupUserRepository_withDatabaseTablesFromDDL() {
        // Given
        User user = User.builder()
                .userId(UUID.randomUUID())
                .firstName("Bengt")
                .lastName("Bergström")
                .dateOfBirth("19630304")
                .unokod("BB0304")
                .email("BB@Test.com")
                .caseManager("Ordnings Vakt")
                .build();

        // When
        userRepository.save(user);

        // Then
        assertThat(userRepository.findAll()).hasSize(1);

        // And
        assertThat(userRepository.findById(user.getUserId())).hasValue(user);

        // And
        userRepository.delete(user);
        assertThat(userRepository.findAll()).isEmpty();
    }

    @Test
    void shouldSuccessfullySetupHostRepository_withDatabaseTablesFromDDL() {
        // Given
        var host = Host.builder()
                .name("Boende Härbärge 1")
                .address_1("Address 1")
                .address_2("Street 1")
                .city("Stockholm")
                .addressPostcode("111 20")
                .email("host1@example.com")
                .countOfAvailablePlaces(10)
                .totalPlaces(50)
                .facilities("Boende, Mat, Dusch, Tvätt, Samtalsstöd")
                .targetAudience("Homeless")
                .build();
        ;

        // When
        hostRepository.save(host);

        // Then
        assertThat(hostRepository.findAll()).hasSize(1);

        // And
        assertThat(hostRepository.findById(host.getHostId())).hasValue(host);

        // And
        hostRepository.delete(host);
        assertThat(hostRepository.findAll()).isEmpty();
    }
}
