package models;

import java.util.UUID;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class Itinerary {
    private UUID flightIdentifier;

    public Itinerary(UUID flightIdentifier) {
        this.flightIdentifier = flightIdentifier;
    }

    public UUID getFlightIdentifier() {
        return flightIdentifier;
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return reflectionEquals(this, obj );
    }
}
