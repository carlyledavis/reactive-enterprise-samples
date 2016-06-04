package eventdriven.reservation;

import eventdriven.events.EventBus;
import eventdriven.events.EventDriven;
import payments.events.PaymentFulfilledEvent;
import reservation.ReservationManager;

public class MessageDrivenReservationManager extends ReservationManager implements EventDriven{
    public void subscribeTo(EventBus eventBus) {
        eventBus.register(PaymentFulfilledEvent.class, this );
    }

    void on( PaymentFulfilledEvent event ){}
}
