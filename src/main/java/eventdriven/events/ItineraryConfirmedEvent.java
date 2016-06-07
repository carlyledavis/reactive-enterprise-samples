package eventdriven.events;

import models.Reservation;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class ItineraryConfirmedEvent {

    private final Reservation reservation;

    public ItineraryConfirmedEvent(Reservation reservation) {
        this.reservation = reservation;
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
