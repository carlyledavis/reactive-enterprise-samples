package eventdriven.events;

import models.Itinerary;
import models.PaymentConfirmation;
import models.Reservation;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class ItineraryConfirmedEvent {

    private final Reservation reservation;
    private PaymentConfirmation paymentConfirmation;
    private Itinerary itinerary;

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

    public PaymentConfirmation getPaymentConfirmation() {
        return paymentConfirmation;
    }

    public Itinerary getItinerary() {
        return itinerary;
    }

    public Reservation getReservation() {
        return reservation;
    }
}
