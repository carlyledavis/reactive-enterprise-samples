package reservation;

import java.util.List;
import java.util.UUID;

public class FlightInventory {
    private final List<Flight> flights;

    public FlightInventory(List<Flight> flights) {
        this.flights = flights;
    }

    public Flight getFlight(UUID flightIdentifier) {
        return flights.stream()
                .filter(x->x.getId().equals(flightIdentifier))
                .findFirst()
                .get();
    }
}
