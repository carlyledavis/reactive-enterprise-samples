package reservation.events;

import models.PaymentInformation;

public class ReservationCreatedEvent {
    private PaymentInformation paymentInformation;

    public ReservationCreatedEvent(PaymentInformation paymentInformation) {
        this.paymentInformation = paymentInformation;
    }

    public PaymentInformation getPaymentInformation() {
        return paymentInformation;
    }
}
