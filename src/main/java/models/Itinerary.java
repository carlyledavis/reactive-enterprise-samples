package models;

import java.util.UUID;

public class Itinerary {
    private final String source;
    private final String destination;
    private UUID flightIdentifier;

    public Itinerary(String source, String destination, UUID flightIdentifier) {
        this.source = source;
        this.destination = destination;
        this.flightIdentifier = flightIdentifier;
    }

    public UUID getFlightIdentifier() {
        return flightIdentifier;
    }
}
