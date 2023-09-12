package com.noq.backend.repository.cosmos;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.noq.backend.models.cosmos.ReservationCosmos;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepositoryCosmos extends ReactiveCosmosRepository <ReservationCosmos, String> {
}
