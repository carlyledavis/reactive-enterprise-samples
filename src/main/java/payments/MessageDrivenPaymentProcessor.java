package payments;

import events.EventBus;
import events.EventDriven;
import reservation.events.ReservationCreatedEvent;

public class MessageDrivenPaymentProcessor extends PaymentProcessor implements EventDriven{

    public void subscribeTo(EventBus eventBus) {
        eventBus.register( ReservationCreatedEvent.class, this );
    }
}
