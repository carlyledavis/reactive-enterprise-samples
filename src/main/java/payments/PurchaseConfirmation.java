package payments;

import models.PaymentConfirmation;
import models.Reservation;
import models.SeatSelection;
import reservation.Flight;

public class PurchaseConfirmation {
    private final PaymentConfirmation paymentConfirmation;
    private final Reservation reservation;
    private final Flight flight;
    private final SeatSelection confirmedSeat;

    public PurchaseConfirmation(PaymentConfirmation paymentConfirmation,
                                Reservation reservation,
                                Flight flight,
                                SeatSelection confirmedSeat) {
        this.paymentConfirmation = paymentConfirmation;
        this.reservation = reservation;
        this.flight = flight;
        this.confirmedSeat = confirmedSeat;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public SeatSelection getConfirmedSeat() {
        return confirmedSeat;
    }

    public PaymentConfirmation getPaymentConfirmation() {
        return paymentConfirmation;
    }
}
