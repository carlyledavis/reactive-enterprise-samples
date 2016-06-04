package eventdriven.email;

import email.EmailServer;
import eventdriven.events.EventBus;
import eventdriven.events.EventDriven;
import email.EmailCommunicationProvider;
import reservation.events.ReservationFulfilledEvent;

public class MessageDrivenEmailCommunicationProvider extends EmailCommunicationProvider implements EventDriven {

    public MessageDrivenEmailCommunicationProvider(EventBus eventBus, EmailServer emailServer) {
        super(emailServer);
        subscribeTo(eventBus);
    }

    public void subscribeTo(EventBus eventBus) {
        eventBus.register( ReservationFulfilledEvent.class, this );
    }
    public void on(ReservationFulfilledEvent event) {
        super.sendEmail(null);
    }
}
