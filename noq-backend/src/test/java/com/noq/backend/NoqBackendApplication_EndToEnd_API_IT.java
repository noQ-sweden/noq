package com.noq.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.noq.backend.models.Host;
import com.noq.backend.models.User;
import com.noq.backend.repositories.HostRepository;
import com.noq.backend.repositories.UserRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * This is a System Test / End-to-End Test.
 * The focus is to demonstrate (and test) usage of the APIs given by this service
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("end-to-end")
public class NoqBackendApplication_EndToEnd_API_IT extends PostgresqlContainerBase {

    private static final String AVAILABLE_HOSTS_URL = "http://localhost:%s/api/user/available-hosts";
    private static final String CREATE_BOOKING_REQUEST_URL = "http://localhost:%s/api/user/booking";
    private static final String USER_BOOKINGS_URL = "http://localhost:%s/api/user/booking/user/%s";
    private static final String HOST_BOOKINGS_URL = "http://localhost:%s/api/host/bookings/%s";
    private static final String HOST_APPROVE_BOOKING_URL = "http://localhost:%s/api/host/bookings/%s/approve";
    @LocalServerPort
    private int port;

    @Autowired
    HostRepository hostRepository;

    @Autowired
    UserRepository userRepository;

    private RestTemplate restTemplate;

    /**
     * Pilot for the first Demo.
     */
    @SneakyThrows
    @Test
    @Disabled("Disabled as for the Pilot we are using only one Hardcoded Host, hence test will fail here")
    void shouldApproveABookingRequest_whenRequestedByAnExistingUser() {
        // Given existing Host and User in the Backend
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
        var savedHost = hostRepository.save(host);
        var savedHostId = savedHost.getHostId();

        User user = User.builder()
                .firstName("Bengt")
                .lastName("Bergström")
                .dateOfBirth("19630304")
                .unokod("BB0304")
                .email("BB@Test.com")
                .caseManager("Ordnings Vakt")
                .build();
        User savedUser = userRepository.save(user);
        var userId = savedUser.getUserId();
        System.out.println("Saved userId: " + userId);
        // When Host Exists
        restTemplate = new RestTemplate();
        var availableHostsUrl = AVAILABLE_HOSTS_URL.formatted(port);
        var response = restTemplate.getForObject(availableHostsUrl, String.class);
        assertThat(response).contains(savedHostId.toString());
        try {
            // Then User Creates a booking at this host Successfully
            var createBookingUrl = CREATE_BOOKING_REQUEST_URL
                    .formatted(port);
            var createBookingRequestTemplate = Files.readString(Path.of(URI.create(String.valueOf(NoqBackendApplication_EndToEnd_API_IT.class.getClassLoader().getResource("samples/booking-request-template.json")))));
            var requestJSON = createBookingRequestTemplate.formatted(savedHostId, userId, LocalDateTime.now());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<>(requestJSON, headers);
            restTemplate.put(createBookingUrl, requestEntity);
        } catch (Exception e) {
            fail("Exception creating a booking: " + e);
        }
        // AND Booking is Available for a User
        var listBookingsUrlForUserUrl = USER_BOOKINGS_URL.formatted(port, savedUser.getUserId());
        var userBookingsResponse = restTemplate.getForObject(listBookingsUrlForUserUrl, String.class);
        assertThat(userBookingsResponse).contains(savedHostId.toString());
        assertThat(userBookingsResponse).contains(userId.toString());
        // AND Booking is Available for a Host Admin
        var listBookingsUrlForHostUrl = HOST_BOOKINGS_URL.formatted(port, savedHostId);
        var hostBookingsResponse = restTemplate.getForObject(listBookingsUrlForHostUrl, String.class);
        assertThat(hostBookingsResponse).contains(savedHostId.toString());
        assertThat(hostBookingsResponse).contains(userId.toString());
        // AND Approve Booking from Host
        var jsonNode = new ObjectMapper().readTree(hostBookingsResponse);
        var extractedBookingId = jsonNode.get("bookings").get(0).get("bookingId").asText();
        System.out.println(extractedBookingId);
        try {
            var approveBookingUrl = HOST_APPROVE_BOOKING_URL.formatted(port, savedHostId);
            var approveBookingRequestTemplate = Files.readString(Path.of(URI.create(String.valueOf(NoqBackendApplication_EndToEnd_API_IT.class.getClassLoader().getResource("samples/booking-approval-request-template.json")))));
            var approvalRequestJSON = approveBookingRequestTemplate.formatted(extractedBookingId, "APPROVE");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<>(approvalRequestJSON, headers);
            var approvalResponse = restTemplate.postForEntity(approveBookingUrl, requestEntity, String.class);;
            System.out.println(approvalResponse);
        } catch (Exception e) {
            fail("Exception approving a booking: " + e);
        }
    }
}
