package eventdriven.commands;

import models.PaymentInformation;

import java.util.UUID;

public class SecureReservationCommand extends Command {
    private UUID reservationId;
    private PaymentInformation paymentInformation;

    public SecureReservationCommand(PaymentInformation paymentInformation) {
        super();
        this.paymentInformation = paymentInformation;
    }

    public UUID getReservationId() {
        return reservationId;
    }

    public PaymentInformation getPaymentInformation() {
        return paymentInformation;
    }
}
