package messaging;

import email.EmailServer;
import email.models.Email;
import eventdriven.email.MessageDrivenEmailCommunicationProvider;
import eventdriven.events.EventBus;
import eventdriven.events.SimpleEventBus;
import models.PaymentConfirmation;
import models.Reservation;
import models.SeatSelection;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import reservation.events.ReservationFulfilledEvent;

import static org.mockito.Mockito.*;

public class MessageDrivenEmailCommunicationProviderTest {

    private EmailServer emailServer;
    private EventBus eventBus;
    private SeatSelection seatSelection;
    private Reservation reservation;
    private PaymentConfirmation paymentConfirmation;
    private ReservationFulfilledEvent event;

    @Before
    public void setUp() throws Exception {
        emailServer = mock(EmailServer.class);
        eventBus = new SimpleEventBus();
        seatSelection = new SeatSelection("15F", true);
        reservation = mock(Reservation.class);
        paymentConfirmation = mock(PaymentConfirmation.class);
        event = new ReservationFulfilledEvent(paymentConfirmation, reservation);
    }

    @Test
    public void shouldSendOutConfirmationEmail(){
        MessageDrivenEmailCommunicationProvider provider = new MessageDrivenEmailCommunicationProvider(emailServer );
        provider.on(event);

        verify(emailServer).send(any());
    }

    @Test
    public void shouldSendOutConfirmationEmailWhenReservationIsConfirmedViaEvent(){
        new MessageDrivenEmailCommunicationProvider(emailServer).subscribeTo(eventBus);

        eventBus.publish(event);

        ArgumentCaptor<Email> captor = ArgumentCaptor.forClass(Email.class);
        verify(emailServer).send(captor.capture());
    }

    @Test
    public void shouldNotSendEmailOutIfNoEmailProviderIsListening(){
        EventBus eventBus = new SimpleEventBus();
        eventBus.publish(event);

        ArgumentCaptor<Email> captor = ArgumentCaptor.forClass(Email.class);
        verify(emailServer, never()).send(captor.capture());
    }

}