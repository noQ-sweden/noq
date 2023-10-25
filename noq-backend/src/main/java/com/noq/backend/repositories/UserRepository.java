package com.noq.backend.repositories;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.noq.backend.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CosmosRepository<User, String> {
}
