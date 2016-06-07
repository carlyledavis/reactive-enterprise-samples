package models;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class Reservation {
    private final Itinerary itinerary;
    private final SeatSelection seatSelection;


    public Reservation(Itinerary itinerary, SeatSelection seatSelection) {

        this.itinerary = itinerary;
        this.seatSelection = seatSelection;
    }

    public Itinerary getItinerary() {
        return itinerary;
    }

    public SeatSelection getSeatSelection() {
        return seatSelection;
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
