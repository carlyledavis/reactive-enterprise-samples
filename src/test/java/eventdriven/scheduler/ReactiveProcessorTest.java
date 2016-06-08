package eventdriven.scheduler;

import eventdriven.commands.SchedulingService;
import eventdriven.commands.SecureReservationCommand;
import eventdriven.email.MessageDrivenEmailCommunicationProvider;
import eventdriven.events.SimpleEventBus;
import eventdriven.events.TicketPurchasedEvent;
import eventdriven.payments.MessageDrivenPaymentProcessor;
import eventdriven.reservation.CustomerTicketConfirmation;
import eventdriven.reservation.MessageDrivenFlightInventory;
import eventdriven.reservation.MessageDrivenReservationManager;
import models.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import payments.events.PaymentFulfilledEvent;
import reservation.Flight;
import reservation.events.CustomerTicketPurchaseInitiatedEvent;
import reservation.events.ReservationFulfilledEvent;

import java.util.UUID;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ReactiveProcessorTest {

    private SimpleEventBus eventBus;
    private Itinerary itinerary;
    private PaymentInformation paymentInformation;
    private MessageDrivenEmailCommunicationProvider emailCommunicationProvider;
    private MessageDrivenFlightInventory flightInventory;
    private MessageDrivenReservationManager reservationManager;
    private MessageDrivenPaymentProcessor paymentProcessor;
    private SchedulingService service;
    private SeatSelection seatSelection;
    private PaymentConfirmation paymentConfirmation;
    private UUID flightIdentifier;
    private Flight flight;
    private SeatSelection confirmedSeat;

    @Before
    public void setUp() throws Exception {
        eventBus = spy(new SimpleEventBus());
        seatSelection = new SeatSelection("15F");
        confirmedSeat = new SeatSelection("15F", true);
        flightIdentifier = UUID.randomUUID();
        itinerary = new Itinerary(flightIdentifier, seatSelection);
        flight = mock(Flight.class);
        when(flight.getId()).thenReturn(flightIdentifier);
        when(flight.selectSeat(any())).thenReturn(confirmedSeat);

        CustomerTicketConfirmation ticketConfirmation = new CustomerTicketConfirmation(itinerary);
        when(flight.purchaseTicket(any())).thenReturn(ticketConfirmation);

        paymentInformation = new PaymentInformation();
        emailCommunicationProvider = spy(new MessageDrivenEmailCommunicationProvider(email -> { }));
        flightInventory = spy(new MessageDrivenFlightInventory(newArrayList(flight)));
        reservationManager = spy(new MessageDrivenReservationManager((itinerary) -> null));
        paymentConfirmation = mock(PaymentConfirmation.class);
        paymentProcessor = spy(new MessageDrivenPaymentProcessor(paymentInformation ->
                paymentConfirmation));
        service = new SchedulingService(eventBus);
    }

    @Test
    public void shouldOrchestrateTheEntireSystem(){
        ReactiveProcessor processor = new ReactiveProcessor(eventBus);
        asList(emailCommunicationProvider,
                flightInventory,
                reservationManager,
                paymentProcessor)
                .forEach(processor::wireEventHandlers);

        service.handle( new SecureReservationCommand(itinerary, paymentInformation));

        PaymentFulfilledEvent paymentFulfilledEvent = new PaymentFulfilledEvent(paymentConfirmation, itinerary);

        verify(flightInventory).on(paymentFulfilledEvent);
        verify(flightInventory).on(new TicketPurchasedEvent(paymentConfirmation, itinerary));
        verify(reservationManager).on(paymentFulfilledEvent);
        verify(paymentProcessor).on(
                new CustomerTicketPurchaseInitiatedEvent(paymentInformation, itinerary));

        ArgumentCaptor<ReservationFulfilledEvent> captor = ArgumentCaptor.forClass(ReservationFulfilledEvent.class);

        verify(emailCommunicationProvider).on( captor.capture() );
        assertThat( captor.getValue()).isEqualToComparingFieldByField(new ReservationFulfilledEvent(paymentConfirmation,
                new Reservation(itinerary, confirmedSeat)));
    }
}