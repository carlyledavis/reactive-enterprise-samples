package eventdriven.reservation;

import eventdriven.events.SimpleEventBus;
import models.Itinerary;
import models.PaymentConfirmation;
import models.Reservation;
import models.SeatSelection;
import org.junit.Before;
import org.junit.Test;
import payments.events.PaymentFulfilledEvent;
import reservation.Flight;

import java.util.List;
import java.util.UUID;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.*;

public class MessageDrivenFlightInventoryTest {

    private SimpleEventBus eventBus;
    private SeatSelection seatSelection;
    private Reservation reservation;
    private List<Flight> flights;
    private UUID flightId;
    private Flight flight;
    private PaymentConfirmation confirmation;

    @Before
    public void setUp() throws Exception {
        eventBus = new SimpleEventBus();
        seatSelection = new SeatSelection("15F");
        flightId = randomUUID();

        reservation = new Reservation(new Itinerary(flightId), seatSelection);
        flights = newArrayList();
        flight = mock(Flight.class);
        when(flight.getId()).thenReturn(flightId);

        flights.add(flight);
        confirmation = mock(PaymentConfirmation.class);
    }

    @Test
    public void shouldReserveSeatOnFilfilledEvent(){
        MessageDrivenFlightInventory flightInventory = new MessageDrivenFlightInventory(flights);
        flightInventory.on( new PaymentFulfilledEvent(confirmation, reservation));

        verify(flight).selectSeat(seatSelection);
    }

    @Test
    public void shouldNotRespondIfFlightInventoryNotSubscribed(){
        eventBus = new SimpleEventBus();
        eventBus.publish( new PaymentFulfilledEvent(confirmation, null ));

        verify( flight, never() ).selectSeat(seatSelection );
    }

    @Test
    public void shouldReserveSeatOncePaymentHasBeenConfirmedViaEventWiring(){
        new MessageDrivenFlightInventory(flights).subscribeTo(eventBus);
        PaymentFulfilledEvent event = new PaymentFulfilledEvent(confirmation, reservation);

        eventBus.publish(event);
        verify( flight ).selectSeat( seatSelection );
    }

}