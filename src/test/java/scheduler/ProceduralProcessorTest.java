package scheduler;

import messaging.EmailCommunicationProvider;
import models.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import payments.PaymentProcessor;
import payments.PurchaseConfirmation;
import reservation.Flight;
import reservation.FlightInventory;
import reservation.ReservationManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProceduralProcessorTest {

    @Mock
    private PaymentProcessor paymentProcessor;
    @Mock
    private EmailCommunicationProvider emailCommunicationProvider;
    @Mock
    private ReservationManager reservationManager;
    @Mock
    private FlightInventory flightInventory;
    private ProceduralProcessor processor;
    private Itinerary draftItinerary;
    private SeatSelection seatSelection;
    private PaymentInformation paymentInformation;
    private EmailAddress emailAddress;

    @Before
    public void setUp() throws Exception {
        processor = new ProceduralProcessor(paymentProcessor,
                emailCommunicationProvider,
                reservationManager,
                flightInventory);

        draftItinerary = new Itinerary("BDL", "LAS");
        seatSelection = new SeatSelection("15F");
        paymentInformation = new PaymentInformation();
        emailAddress = new EmailAddress("fake-email@email.com");
    }

    @Test
    public void shouldResolveAPurchaseConfirmation(){
        SeatSelection confirmedSeat = new SeatSelection(seatSelection.getSeat(), true);
        Reservation reservation = mock(Reservation.class);
        PaymentConfirmation paymentConfirmation = mock(PaymentConfirmation.class);
        Flight flight = mock(Flight.class);

        when(paymentProcessor.secureFunds(draftItinerary,paymentInformation)).thenReturn(paymentConfirmation);
        when(flightInventory.getFlight(anyObject())).thenReturn(flight);
        when(flight.selectSeat(seatSelection)).thenReturn(confirmedSeat);
        when(reservationManager.createItinerary(draftItinerary, paymentConfirmation,seatSelection )).thenReturn(reservation);

        PurchaseConfirmation purchase = processor.processReservation(draftItinerary,
                seatSelection, paymentInformation, emailAddress);

        assertThat( purchase.getReservation()).isEqualTo( reservation );
        assertThat( purchase.getConfirmedSeat()).isEqualTo(confirmedSeat);
        assertThat( purchase.getPaymentConfirmation()).isEqualTo(paymentConfirmation);

        verify(emailCommunicationProvider).sendEmail(purchase);
    }

}