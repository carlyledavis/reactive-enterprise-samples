package reservation;

import events.EventBus;
import events.EventDriven;

public class MessageDrivenFlightInventory extends FlightInventory implements EventDriven{
    @Override
    public void subscribeTo(EventBus eventBus) {

    }
}
