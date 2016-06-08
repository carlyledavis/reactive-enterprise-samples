package payments;

import models.PaymentConfirmation;
import models.Reservation;

public class PurchaseConfirmation {


    private final PaymentConfirmation paymentConfirmation;
    private final Reservation reservation;
    private boolean confirmedSeat;

    public PurchaseConfirmation(PaymentConfirmation paymentConfirmation, Reservation reservation) {
        this.paymentConfirmation = paymentConfirmation;
        this.reservation = reservation;
    }


    public PaymentConfirmation getPaymentConfirmation() {
        return paymentConfirmation;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public boolean getConfirmedSeat() {
        return confirmedSeat;
    }
}
