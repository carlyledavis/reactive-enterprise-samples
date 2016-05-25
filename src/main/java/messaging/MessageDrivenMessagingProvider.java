package messaging;

import events.EventBus;
import events.EventDriven;
import reservation.events.ReservationFulfilfiledEvent;

public class MessageDrivenMessagingProvider extends MessagingProvider implements EventDriven {

    public void subscribeTo(EventBus eventBus) {
        eventBus.register( ReservationFulfilfiledEvent.class, this );
    }
}
