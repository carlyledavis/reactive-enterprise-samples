package models;

import java.util.UUID;

public class Itinerary {
    private UUID flightIdentifier;

    public Itinerary(UUID flightIdentifier) {
        this.flightIdentifier = flightIdentifier;
    }

    public UUID getFlightIdentifier() {
        return flightIdentifier;
    }
}
