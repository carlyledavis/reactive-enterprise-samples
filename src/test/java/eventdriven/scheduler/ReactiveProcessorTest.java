package eventdriven.scheduler;

import eventdriven.commands.SchedulingService;
import eventdriven.commands.SecureReservationCommand;
import eventdriven.email.MessageDrivenEmailCommunicationProvider;
import eventdriven.events.SimpleEventBus;
import eventdriven.payments.MessageDrivenPaymentProcessor;
import eventdriven.reservation.MessageDrivenFlightInventory;
import eventdriven.reservation.MessageDrivenReservationManager;
import models.Itinerary;
import models.PaymentInformation;
import models.Reservation;
import models.SeatSelection;
import org.junit.Before;
import org.junit.Test;
import reservation.events.CustomerTicketPurchaseInitiatedEvent;

import java.util.UUID;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class ReactiveProcessorTest {

    private SimpleEventBus eventBus;
    private Itinerary itinerary;
    private PaymentInformation paymentInformation;
    private MessageDrivenEmailCommunicationProvider emailCommunicationProvider;
    private MessageDrivenFlightInventory flightInventory;
    private MessageDrivenReservationManager messageDrivenReservationManager;
    private MessageDrivenPaymentProcessor messageDrivenPaymentProcessor;
    private SchedulingService service;
    private SeatSelection seatSelection;

    @Before
    public void setUp() throws Exception {
        eventBus = spy(new SimpleEventBus());
        seatSelection = new SeatSelection("15F");
        itinerary = new Itinerary(UUID.randomUUID(), seatSelection);
        paymentInformation = new PaymentInformation();
        emailCommunicationProvider = spy(new MessageDrivenEmailCommunicationProvider(email -> { }));
        flightInventory = spy(new MessageDrivenFlightInventory(newArrayList()));
        messageDrivenReservationManager = spy(new MessageDrivenReservationManager((itinerary) -> null));
        messageDrivenPaymentProcessor = spy(new MessageDrivenPaymentProcessor(paymentInformation -> null));
        service = new SchedulingService(eventBus);
    }

    @Test
    public void shouldOrchestrateTheEntireSystem(){
        ReactiveProcessor processor = new ReactiveProcessor(eventBus);
        asList(emailCommunicationProvider,
                flightInventory,
                messageDrivenReservationManager,
                messageDrivenPaymentProcessor)
                .forEach(processor::wireEventHandlers);

        service.handle( new SecureReservationCommand(itinerary, paymentInformation));

        verify(messageDrivenPaymentProcessor).on(
                new CustomerTicketPurchaseInitiatedEvent(paymentInformation, itinerary));

    }
}