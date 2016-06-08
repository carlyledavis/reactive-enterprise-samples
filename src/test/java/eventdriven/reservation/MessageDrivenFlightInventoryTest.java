package eventdriven.reservation;

import eventdriven.events.EventBus;
import eventdriven.events.SimpleEventBus;
import eventdriven.events.TicketPurchasedEvent;
import models.Itinerary;
import models.PaymentConfirmation;
import models.Reservation;
import models.SeatSelection;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import payments.events.PaymentFulfilledEvent;
import reservation.Flight;
import reservation.events.ReservationFulfilledEvent;

import java.util.List;
import java.util.UUID;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class MessageDrivenFlightInventoryTest {

    private EventBus eventBus;
    private SeatSelection seatSelection;
    private Reservation reservation;
    private List<Flight> flights;
    private UUID flightId;
    private Flight flight;
    private PaymentConfirmation confirmation;
    private Itinerary itinerary;
    private TicketPurchasedEvent purchasedEvent;

    @Before
    public void setUp() throws Exception {
        eventBus = new SimpleEventBus();
        seatSelection = new SeatSelection("15F");
        flightId = randomUUID();

        reservation = new Reservation(new Itinerary(flightId, seatSelection), new SeatSelection( "15F", true));
        flights = newArrayList();
        flight = mock(Flight.class);
        when(flight.getId()).thenReturn(flightId);
        CustomerTicketConfirmation ticketConfirmation = new CustomerTicketConfirmation(itinerary);
        when(flight.purchaseTicket(any())).thenReturn(ticketConfirmation);

        flights.add(flight);
        confirmation = mock(PaymentConfirmation.class);
        itinerary = new Itinerary(flightId, seatSelection );
    }

    @Test
    public void shouldReserveSeatOnFilfilledEvent(){
        MessageDrivenFlightInventory flightInventory = new MessageDrivenFlightInventory(flights);
        flightInventory.subscribeTo(eventBus);

        flightInventory.on( new PaymentFulfilledEvent(confirmation, itinerary));
        verify(flight).purchaseTicket(itinerary);
    }

    @Test
    public void shouldNotRespondIfFlightInventoryNotSubscribed(){
        eventBus = new SimpleEventBus();
        eventBus.publish( new PaymentFulfilledEvent(confirmation, null ));

        verify( flight, never() ).selectSeat(seatSelection );
    }

    @Test
    public void shouldOnlyPublishReservationFulfilledEventIfSeatIsConfirmed(){
        eventBus = mock(EventBus.class);
        MessageDrivenFlightInventory inventory = new MessageDrivenFlightInventory(flights);
        inventory.subscribeTo(eventBus);

        PaymentFulfilledEvent event = mock(PaymentFulfilledEvent.class);
        when(event.getItinerary()).thenReturn(itinerary);
        when(event.getPaymentConfirmation()).thenReturn(confirmation);

        inventory.on(event);

        ArgumentCaptor<TicketPurchasedEvent> captor = ArgumentCaptor.forClass(TicketPurchasedEvent.class);
        verify(eventBus,atMost(1)).publish( captor.capture() );

        assertThat( captor.getValue() ).isInstanceOf(TicketPurchasedEvent.class);
    }

//    @Test
//    public void shouldPublishFulfilledEventOnceTicketIsIssuedAndSeatIsSelected(){
//        eventBus = mock(EventBus.class);
//        MessageDrivenFlightInventory inventory = new MessageDrivenFlightInventory(flights);
//        inventory.subscribeTo(eventBus);
//
//        PaymentFulfilledEvent event = mock(PaymentFulfilledEvent.class);
//        when(event.getItinerary()).thenReturn(itinerary);
//        when(event.getPaymentConfirmation()).thenReturn(confirmation);
//
//        inventory.on(event);
//
//        ArgumentCaptor<TicketPurchasedEvent> captor = ArgumentCaptor.forClass(TicketPurchasedEvent.class);
//        purchasedEvent = captor.capture();
//        verify(eventBus,atMost(1)).publish(purchasedEvent);
//
//        inventory.on(purchasedEvent);
//        ArgumentCaptor<ReservationFulfilledEvent> resCaptor = ArgumentCaptor.forClass(ReservationFulfilledEvent.class);
//        verify(eventBus).publish(resCaptor.capture());
//
//
//    }

    @Test
    public void shouldPurchaseTicketPaymentHasBeenConfirmedViaEventWiring(){
        new MessageDrivenFlightInventory(flights).subscribeTo(eventBus);
        PaymentFulfilledEvent event = new PaymentFulfilledEvent(confirmation, itinerary);

        eventBus.publish(event);
        verify( flight ).purchaseTicket( itinerary );
    }

    @Test
    public void shouldReserveSeatOnceTicketed(){
        new MessageDrivenFlightInventory(flights).subscribeTo(eventBus);

        eventBus.publish(new TicketPurchasedEvent(confirmation, itinerary));
        verify( flight ).selectSeat( itinerary.getSeatSelection() );
    }

}