package reservation;

import events.EventBus;
import events.EventDriven;

public class MessageDrivenReservationManager extends ReservationManager implements EventDriven{
    @Override
    public void subscribeTo(EventBus eventBus) {

    }
}
