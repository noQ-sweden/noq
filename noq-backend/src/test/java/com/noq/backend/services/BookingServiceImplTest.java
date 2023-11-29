package com.noq.backend.services;

import com.noq.backend.exceptions.HostNotFoundException;
import com.noq.backend.models.*;
import com.noq.backend.repositories.BookingRepository;
import com.noq.backend.repositories.HostRepository;
import com.noq.backend.repositories.UserRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class BookingServiceImplTest {

    @Mock
    private HostRepository hostRepository;
    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private UserRepository userRepository;

    private BookingServiceImpl bookingService;

    @BeforeEach
    void setup() {
        openMocks(this);
        bookingService = new BookingServiceImpl(hostRepository, bookingRepository, userRepository);
    }


    @Test
    void shouldThrowException_whenUserDoesNotExist() {
        // TODO Implement .....whenUserDoesNotExist.. in future. Assuming now that only one User Exists in the system
    }

    @Test
    @SneakyThrows
    void shouldThrowException_whenHostDoesNotExist() {
        when(hostRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        UUID userId = UUID.randomUUID();
        var user = User.builder()
                .userId(userId)
                .firstName("Bengt")
                .lastName("Bergström")
                .dateOfBirth("19630304")
                .unokod("BB0304")
                .email("BB@Test.com")
                .caseManager("Ordnings Vakt")
                .build();
        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.of(user));
        assertThatThrownBy(() -> bookingService.createBooking(UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now())).isInstanceOfAny(HostNotFoundException.class);
    }

    @Test
    void shouldThrowException_whenHostDoesNotHaveVacantPlacesAvailable() {
        // TODO ..whenHostDoesNotHaveVacantPlacesAvailable
    }
    @Test
    void shouldCreateBooking_whenHostHasVacantPlacesAvailable() {
        var hostId = UUID.randomUUID();
        var host = Host.builder()
                .hostId(hostId)
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
        when(hostRepository.findById(any(UUID.class))).thenReturn(Optional.of(host));

        UUID userId = UUID.randomUUID();
        var user = User.builder()
                .userId(userId)
                .firstName("Bengt")
                .lastName("Bergström")
                .dateOfBirth("19630304")
                .unokod("BB0304")
                .email("BB@Test.com")
                .caseManager("Ordnings Vakt")
                .build();
        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.of(user));
        when(bookingRepository.save(any(Booking.class))).thenAnswer((Answer<Booking>) invocation -> invocation.getArgument(0));

        var createdBooking = bookingService.createBooking(hostId, userId, LocalDateTime.now());

        assertThat(createdBooking.getBookingStatus()).isEqualTo(BookingStatus.PENDING);
        assertThat(createdBooking.getApprovalStatus()).isEqualTo(ApprovalStatus.PENDING);
        // TODO By Default creating a booking for 8 hours after the current Time, Validate with Product
        assertThat(createdBooking.getEndDateTime().getHour()).isEqualTo(LocalDateTime.now().plusHours(8).getHour());
    }

}