package models;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class Reservation {
    private final Itinerary itinerary;
    private SeatSelection confirmedSeat;

    public Reservation(Itinerary itinerary, SeatSelection confirmedSeat) {
        this.itinerary = itinerary;
        this.confirmedSeat = confirmedSeat;
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

    public SeatSelection getConfirmedSeat() {
        return confirmedSeat;
    }

    public void setConfirmedSeat(SeatSelection confirmedSeat) {
        this.confirmedSeat = confirmedSeat;
    }
}
