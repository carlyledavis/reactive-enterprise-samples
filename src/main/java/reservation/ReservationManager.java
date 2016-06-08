package reservation;

import models.Itinerary;
import models.Reservation;

public class ReservationManager {
    private final Airline airline;

    public ReservationManager(Airline airline) {
        this.airline = airline;
    }

    public Reservation confirmItinerary(Itinerary itinerary) {
        return airline.confirmItinerary(itinerary);
    }

}
