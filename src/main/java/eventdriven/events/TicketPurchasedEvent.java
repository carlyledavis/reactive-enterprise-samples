package eventdriven.events;

import models.Itinerary;
import models.PaymentConfirmation;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class TicketPurchasedEvent {
    private Itinerary itinerary;
    private PaymentConfirmation paymentConfirmation;


    public TicketPurchasedEvent(PaymentConfirmation paymentConfirmation, Itinerary customerTicketConfirmation) {
        this.paymentConfirmation = paymentConfirmation;
        itinerary = customerTicketConfirmation;
    }

    public Itinerary getItinerary() {
        return itinerary;
    }

    public PaymentConfirmation getPaymentConfirmation() {
        return paymentConfirmation;
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
