package payments.events;

import models.Itinerary;
import models.PaymentConfirmation;
import models.Reservation;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class PaymentFulfilledEvent {
    private PaymentConfirmation confirmation;
    private final Itinerary itinerary;

    public PaymentFulfilledEvent(PaymentConfirmation confirmation, Itinerary itinerary) {
        this.confirmation = confirmation;
        this.itinerary = itinerary;
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return reflectionEquals(this,obj);
    }

    public Itinerary getItinerary() {
        return itinerary;
    }
}
