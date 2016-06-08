package reservation;

import models.Itinerary;
import models.Reservation;
import models.SeatSelection;

public interface Airline {
    Reservation confirmItinerary(Itinerary itinerary);
}
