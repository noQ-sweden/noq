package com.noq.backend.controllers.user.dto;

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
    String hasAccessToInternet;
    String caseManager;

    public static User toDomain() {
        // TODO Implement Tests
        return User.builder().build();
    }
}
