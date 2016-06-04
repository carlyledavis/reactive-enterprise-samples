package email;

import email.models.Email;
import models.EmailConfirmation;
import payments.PurchaseConfirmation;

public class EmailCommunicationProvider {
    private final EmailServer emailServer;

    public EmailCommunicationProvider(EmailServer emailServer) {
        this.emailServer = emailServer;
    }

    public EmailConfirmation sendEmail(PurchaseConfirmation purchaseConfirmation) {
        emailServer.send( createEmail(purchaseConfirmation));
        return null;
    }

    private Email createEmail(PurchaseConfirmation purchaseConfirmation) {
        return null;
    }
}
