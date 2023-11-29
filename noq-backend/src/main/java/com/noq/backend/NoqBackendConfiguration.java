package com.noq.backend;

import com.noq.backend.repositories.BookingRepository;
import com.noq.backend.repositories.HostRepository;
import com.noq.backend.repositories.UserRepository;
import com.noq.backend.services.BookingService;
import com.noq.backend.services.BookingServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NoqBackendConfiguration {

    @Bean
    BookingService bookingService(HostRepository hostRepository, BookingRepository bookingRepository, UserRepository userRepository) {
        return new BookingServiceImpl(hostRepository, bookingRepository, userRepository);
    }
}
