package com.noq.backend.repository.cosmos;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;
import com.noq.backend.models.cosmos.HostCosmos;
import com.noq.backend.models.cosmos.ReservationCosmos;
import com.noq.backend.models.cosmos.UserCosmos;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ReservationRepositoryCosmos extends ReactiveCosmosRepository<ReservationCosmos, String> {
    Mono<ReservationCosmos> findReservationCosmosByUser(UserCosmos user);
    Mono<ReservationCosmos> findReservationCosmosByHost(HostCosmos host);
}
