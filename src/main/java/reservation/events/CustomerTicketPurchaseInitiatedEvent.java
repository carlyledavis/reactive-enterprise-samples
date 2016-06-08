package reservation.events;

import models.Itinerary;
import models.PaymentInformation;
import models.Reservation;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class CustomerTicketPurchaseInitiatedEvent {
    private PaymentInformation paymentInformation;
    private Itinerary itinerary;

    public CustomerTicketPurchaseInitiatedEvent(PaymentInformation paymentInformation, Itinerary itinerary) {
        this.paymentInformation = paymentInformation;
        this.itinerary = itinerary;
    }

    public PaymentInformation getPaymentInformation() {
        return paymentInformation;
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
