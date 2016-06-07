package reservation;

import eventdriven.events.ItineraryConfirmedEvent;
import models.Itinerary;
import models.Reservation;
import models.SeatSelection;

public class ReservationManager {
    private final Airline airline;

    public ReservationManager(Airline airline) {
        this.airline = airline;
    }

    public Reservation createItinerary(Itinerary itinerary, SeatSelection seatSelection) {
        return airline.createItinerary(itinerary,seatSelection);
    }

}
