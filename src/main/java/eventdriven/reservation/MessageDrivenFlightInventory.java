package eventdriven.reservation;

import eventdriven.events.EventBus;
import eventdriven.events.EventDriven;
import reservation.FlightInventory;

public class MessageDrivenFlightInventory extends FlightInventory implements EventDriven{
    public void subscribeTo(EventBus eventBus) {
    }
}
