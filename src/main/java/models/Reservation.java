package models;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class Reservation {
    private final Itinerary itinerary;

    public Reservation(Itinerary itinerary) {
        this.itinerary = itinerary;
    }

    public Itinerary getItinerary() {
        return itinerary;
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
