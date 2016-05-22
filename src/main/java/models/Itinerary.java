package models;

import java.util.UUID;

public class Itinerary {
    private UUID flightIdentifier;

    public Itinerary(String source, String destination) {
    }

    public UUID getFlightIdentifier() {
        return flightIdentifier;
    }
}
