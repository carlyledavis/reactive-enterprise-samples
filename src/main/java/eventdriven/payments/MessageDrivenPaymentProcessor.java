package eventdriven.payments;

import eventdriven.events.EventBus;
import eventdriven.events.EventDriven;
import payments.PaymentProcessor;
import reservation.events.ReservationCreatedEvent;

public class MessageDrivenPaymentProcessor extends PaymentProcessor implements EventDriven{

    public void subscribeTo(EventBus eventBus) {
        eventBus.register( ReservationCreatedEvent.class, this );
    }
}
