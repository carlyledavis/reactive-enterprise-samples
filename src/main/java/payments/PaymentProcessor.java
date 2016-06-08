package payments;

import models.PaymentInformation;
import models.Itinerary;
import models.PaymentConfirmation;

public class PaymentProcessor {
    private final Bank bank;

    public PaymentProcessor(Bank bank) {
        this.bank = bank;
    }

    public PaymentConfirmation secureFunds(PaymentInformation paymentInformation) {
        return bank.secureFunds(paymentInformation);
    }
}
