package payments.events;

import models.PaymentConfirmation;
import models.Reservation;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class PaymentFulfilledEvent {
    private PaymentConfirmation confirmation;
    private Reservation reservation;

    public PaymentFulfilledEvent(PaymentConfirmation confirmation, Reservation reservation) {
        this.confirmation = confirmation;
        this.reservation = reservation;
    }

    public Reservation getReservation() {
        return reservation;
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return reflectionEquals(this,obj);
    }
}
