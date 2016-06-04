package payments.events;

import models.Reservation;

public class PaymentFulfilledEvent {
    private Reservation reservation;

    public PaymentFulfilledEvent(Reservation reservation) {
        this.reservation = reservation;
    }


    public Reservation getReservation() {
        return reservation;
    }
}
