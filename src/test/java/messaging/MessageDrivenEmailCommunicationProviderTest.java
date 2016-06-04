package messaging;

import email.EmailServer;
import email.models.Email;
import eventdriven.email.MessageDrivenEmailCommunicationProvider;
import eventdriven.events.EventBus;
import eventdriven.events.SimpleEventBus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import reservation.events.ReservationFulfilfiledEvent;

import static org.mockito.Mockito.*;

public class MessageDrivenEmailCommunicationProviderTest {

    private EmailServer emailServer;

    @Before
    public void setUp() throws Exception {
        emailServer = mock(EmailServer.class);
    }

    @Test
    public void shouldSendOutConfirmationEmailWhenReservationIsConfirmed(){
        EventBus eventBus = new SimpleEventBus();
        new MessageDrivenEmailCommunicationProvider(eventBus, emailServer);

        ReservationFulfilfiledEvent event = new ReservationFulfilfiledEvent();
        eventBus.publish(event);

        ArgumentCaptor<Email> captor = ArgumentCaptor.forClass(Email.class);
        verify(emailServer).send(captor.capture());
    }

    @Test
    public void shouldNotSendEmailOutIfNoEmailProviderIsListening(){
        EventBus eventBus = new SimpleEventBus();
        ReservationFulfilfiledEvent event = new ReservationFulfilfiledEvent();
        eventBus.publish(event);

        ArgumentCaptor<Email> captor = ArgumentCaptor.forClass(Email.class);
        verify(emailServer, never()).send(captor.capture());
    }

}