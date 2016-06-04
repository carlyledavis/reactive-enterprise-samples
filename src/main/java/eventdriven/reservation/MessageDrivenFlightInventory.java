package eventdriven.reservation;

import events.EventBus;
import events.EventDriven;
import reservation.FlightInventory;

public class MessageDrivenFlightInventory extends FlightInventory implements EventDriven{
    public void subscribeTo(EventBus eventBus) {
    }
}
