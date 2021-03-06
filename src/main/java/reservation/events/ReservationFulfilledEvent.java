package reservation.events;

import models.PaymentConfirmation;
import models.Reservation;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class ReservationFulfilledEvent {
    private final PaymentConfirmation paymentConfirmation;
    private final Reservation reservation;

    public ReservationFulfilledEvent(PaymentConfirmation paymentConfirmation, Reservation reservation) {
        this.paymentConfirmation = paymentConfirmation;
        this.reservation = reservation;
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
        return reflectionEquals(this,obj);
    }

    public Reservation getReservation() {
        return reservation;
    }
}
