package com.noq.backend.clients.host.myLocations;

public record MyLocationsDTO(
        String id,
        Location[] locations
) {
    public record Location(
            String id,
            String name,
            String address1,
            String address2,
            int countOfAvailablePlaces,
            int totalAvailablePlaces
    ) {
    }
}

