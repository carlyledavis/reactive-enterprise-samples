package messaging;

import email.EmailServer;
import email.models.Email;
import eventdriven.email.MessageDrivenEmailCommunicationProvider;
import eventdriven.events.EventBus;
import eventdriven.events.SimpleEventBus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import reservation.events.ReservationFulfilledEvent;

import static org.mockito.Mockito.*;

public class MessageDrivenEmailCommunicationProviderTest {

    private EmailServer emailServer;
    private EventBus eventBus;

    @Before
    public void setUp() throws Exception {
        emailServer = mock(EmailServer.class);
        eventBus = new SimpleEventBus();
    }

    @Test
    public void shouldSendOutConfirmationEmail(){
        MessageDrivenEmailCommunicationProvider provider = new MessageDrivenEmailCommunicationProvider(eventBus, emailServer );
        provider.on( new ReservationFulfilledEvent());

        verify(emailServer).send(any());
    }

    @Test
    public void shouldSendOutConfirmationEmailWhenReservationIsConfirmedViaEvent(){
        new MessageDrivenEmailCommunicationProvider(eventBus, emailServer);

        ReservationFulfilledEvent event = new ReservationFulfilledEvent();
        eventBus.publish(event);

        ArgumentCaptor<Email> captor = ArgumentCaptor.forClass(Email.class);
        verify(emailServer).send(captor.capture());
    }

    @Test
    public void shouldNotSendEmailOutIfNoEmailProviderIsListening(){
        EventBus eventBus = new SimpleEventBus();
        ReservationFulfilledEvent event = new ReservationFulfilledEvent();
        eventBus.publish(event);

        ArgumentCaptor<Email> captor = ArgumentCaptor.forClass(Email.class);
        verify(emailServer, never()).send(captor.capture());
    }

}