package models;

import java.util.UUID;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class Itinerary {
    private UUID flightIdentifier;
    private final SeatSelection seatSelection;

    public Itinerary(UUID flightIdentifier, SeatSelection seatSelection) {
        this.flightIdentifier = flightIdentifier;
        this.seatSelection = seatSelection;
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

    public SeatSelection getSeatSelection() {
        return seatSelection;
    }
}
