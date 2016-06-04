package eventdriven.email;

import eventdriven.events.EventBus;
import eventdriven.events.EventDriven;
import email.EmailCommunicationProvider;
import reservation.events.ReservationFulfilfiledEvent;

public class MessageDrivenEmailCommunicationProvider extends EmailCommunicationProvider implements EventDriven {

    private final EventBus eventBus;

    public MessageDrivenEmailCommunicationProvider(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void subscribeTo(EventBus eventBus) {
        eventBus.register( ReservationFulfilfiledEvent.class, this );
    }
}
