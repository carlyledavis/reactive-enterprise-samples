package payments;

import models.PaymentConfirmation;
import models.PaymentInformation;

public interface Bank {
    PaymentConfirmation secureFunds(PaymentInformation paymentInformation);
}
