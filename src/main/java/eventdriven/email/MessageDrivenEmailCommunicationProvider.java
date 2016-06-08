package eventdriven.email;

import email.EmailServer;
import eventdriven.events.EventBus;
import eventdriven.events.EventDriven;
import email.EmailCommunicationProvider;
import eventdriven.events.ItineraryConfirmedEvent;
import payments.PurchaseConfirmation;
import reservation.events.ReservationFulfilledEvent;

public class MessageDrivenEmailCommunicationProvider extends EmailCommunicationProvider implements EventDriven {

    public MessageDrivenEmailCommunicationProvider(EmailServer emailServer) {
        super(emailServer);
    }

    public void subscribeTo(EventBus eventBus) {
        eventBus.register( ReservationFulfilledEvent.class, this );
    }
    public void on(ReservationFulfilledEvent event) {
        super.sendEmail(new PurchaseConfirmation(event.getPaymentConfirmation(), event.getReservation()));
    }
}
