package com.noq.backend;

import com.noq.backend.models.Host;
import com.noq.backend.models.User;
import com.noq.backend.repositories.HostRepository;
import com.noq.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NoqBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoqBackendApplication.class, args);
    }

//    @Bean
//    ApplicationRunner applicationRunner (
//            HostRepository hostRepository,
//            UserRepository userRepository
//    ){
//        return args -> {
//            userRepository.deleteAll();
//            hostRepository.deleteAll();
//            System.out.println("Hello World");
//
//            User user = User.create("John", "Doe", "1990-01-01", "Case Manager");
//            userRepository.save(user);
//
//            for(int i = 0; i < 10; i++) {
//                Host host = Host.create( "Host Name", "Address 1", "Address 2", "City", "12345", " email", 1, 1, "Facilities", "Target Audience");
//                hostRepository.save(host);
//            }
//        };
//    }
}
