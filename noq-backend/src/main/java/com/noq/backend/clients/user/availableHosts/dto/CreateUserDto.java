package com.noq.backend.clients.user.availableHosts.dto;

import com.noq.backend.models.User;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CreateUserDto {
    String firstName;
    String lastName;
    String dateOfBirth;
    String phone;
    String email;
    String caseManager;

    public User toDomain() {
        return User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .dateOfBirth(dateOfBirth)
                .phone(phone)
                .email(email)
                .caseManager(caseManager)
                .build();
    }
}
