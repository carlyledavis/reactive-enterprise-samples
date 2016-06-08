package eventdriven.reservation;

import eventdriven.events.EventBus;
import eventdriven.events.ItineraryConfirmedEvent;
import models.Itinerary;
import models.PaymentConfirmation;
import models.Reservation;
import models.SeatSelection;
import org.junit.Before;
import org.junit.Test;
import payments.events.PaymentFulfilledEvent;
import reservation.Airline;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MessageDrivenReservationManagerTest {

    private SeatSelection seatSelection;
    private Itinerary itinerary;
    private EventBus eventBus;
    private PaymentConfirmation confirmation;

    @Before
    public void setUp() throws Exception {
        seatSelection = new SeatSelection("14F");
        itinerary = mock(Itinerary.class);
        eventBus = mock(EventBus.class);
        confirmation = mock(PaymentConfirmation.class);
    }

    @Test
    public void shouldConfirmRegistrationWithRandomSeat(){
        Airline airline = mock(Airline.class);
        MessageDrivenReservationManager manager = new MessageDrivenReservationManager(airline);
        manager.subscribeTo(eventBus);

        Reservation reservation = mock(Reservation.class);
        when(itinerary.getSeatSelection()).thenReturn(seatSelection);
        when(airline.confirmItinerary(any())).thenReturn(reservation);

        manager.on( new PaymentFulfilledEvent(confirmation, itinerary));

        verify(airline).confirmItinerary(itinerary);
        verify(eventBus).publish(new ItineraryConfirmedEvent(reservation));
    }

}