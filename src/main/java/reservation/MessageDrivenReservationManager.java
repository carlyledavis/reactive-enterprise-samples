package reservation;

import events.EventBus;
import events.EventDriven;

public class MessageDrivenReservationManager extends ReservationManager implements EventDriven{
    public void subscribeTo(EventBus eventBus) {
    }
}
