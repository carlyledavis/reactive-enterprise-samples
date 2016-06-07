package eventdriven.reservation;

import eventdriven.events.EventBus;
import eventdriven.events.ItineraryConfirmedEvent;
import models.Itinerary;
import models.Reservation;
import models.SeatSelection;
import org.junit.Before;
import org.junit.Test;
import payments.events.PaymentFulfilledEvent;
import reservation.Airline;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MessageDrivenReservationManagerTest {

    private SeatSelection seatSelection;
    private Itinerary itinerary;
    private EventBus eventBus;

    @Before
    public void setUp() throws Exception {
        seatSelection = new SeatSelection("14F");
        itinerary = mock(Itinerary.class);
        eventBus = mock(EventBus.class);
    }

    @Test
    public void shouldConfirmRegistrationWithRandomSeat(){
        Airline airline = mock(Airline.class);
        MessageDrivenReservationManager manager = new MessageDrivenReservationManager(airline);
        manager.subscribeTo(eventBus);

        Reservation reservation = mock(Reservation.class);
        when(reservation.getItinerary()).thenReturn(itinerary);
        when(reservation.getSeatSelection()).thenReturn(seatSelection);

        manager.on( new PaymentFulfilledEvent(reservation));

        verify(airline).createItinerary(itinerary, seatSelection);
        verify(eventBus).publish(new ItineraryConfirmedEvent(reservation));
    }

}