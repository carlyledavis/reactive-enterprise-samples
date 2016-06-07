package reservation;

import models.Itinerary;
import models.SeatSelection;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ReservationManagerTest {

    @Test
    public void shouldCreateItineraryWithAirlineForSelectedFlight(){
        Airline airline = mock(Airline.class);
        Itinerary itinerary = mock(Itinerary.class);
        SeatSelection seatSelection = mock(SeatSelection.class);

        ReservationManager manager = new ReservationManager(airline);
        manager.createItinerary(itinerary,seatSelection);

        verify( airline ).createItinerary(itinerary,seatSelection);
    }

}