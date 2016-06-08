package payments.events;

import models.Itinerary;
import models.PaymentConfirmation;
import models.Reservation;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class PaymentFulfilledEvent {
    private PaymentConfirmation confirmation;
    private final Itinerary itinerary;
    private PaymentConfirmation paymentConfirmation;

    public PaymentFulfilledEvent(PaymentConfirmation paymentConfirmation, Itinerary itinerary) {
        this.paymentConfirmation = paymentConfirmation;
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

    public PaymentConfirmation getPaymentConfirmation() {
        return paymentConfirmation;
    }
}
