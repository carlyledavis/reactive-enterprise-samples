package eventdriven.email;

import email.EmailServer;
import eventdriven.events.EventBus;
import eventdriven.events.EventDriven;
import email.EmailCommunicationProvider;
import payments.events.PaymentFulfilledEvent;
import reservation.events.ReservationFulfilfiledEvent;

public class MessageDrivenEmailCommunicationProvider extends EmailCommunicationProvider implements EventDriven {

    public MessageDrivenEmailCommunicationProvider(EventBus eventBus, EmailServer emailServer) {
        super(emailServer);
        subscribeTo(eventBus);
    }

    public void subscribeTo(EventBus eventBus) {
        eventBus.register( ReservationFulfilfiledEvent.class, this );
    }
    public void on(ReservationFulfilfiledEvent event) {
        super.sendEmail(null);
    }
}
