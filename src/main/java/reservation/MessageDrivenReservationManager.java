package reservation;

import events.EventBus;
import events.EventDriven;
import payments.events.PaymentFulfilledEvent;

public class MessageDrivenReservationManager extends ReservationManager implements EventDriven{
    public void subscribeTo(EventBus eventBus) {
        eventBus.register(PaymentFulfilledEvent.class, this );
    }

    void on( PaymentFulfilledEvent event ){}
}
