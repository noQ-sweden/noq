package com.noq.backend.DTO;

import java.util.UUID;

public record UserDTO(UUID id, String name, Boolean reservation) {

}