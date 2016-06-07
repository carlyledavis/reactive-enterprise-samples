package payments.events;

import models.Reservation;
import models.SeatSelection;

public class PaymentFulfilledEvent {
    private Reservation reservation;
    private SeatSelection seatSelection;

    public PaymentFulfilledEvent(Reservation reservation) {
        this.reservation = reservation;
    }


    public Reservation getReservation() {
        return reservation;
    }
}
