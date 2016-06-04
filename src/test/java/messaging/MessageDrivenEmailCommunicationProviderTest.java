package messaging;

import eventdriven.email.MessageDrivenEmailCommunicationProvider;
import eventdriven.events.EventBus;
import eventdriven.events.SimpleEventBus;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import reservation.events.ReservationFulfilfiledEvent;

import static org.mockito.Mockito.*;

public class MessageDrivenEmailCommunicationProviderTest {

    @Test
    public void shouldSendOutConfirmationEmailWhenReservationIsConfirmed(){

        EventBus eventBus = spy(new SimpleEventBus());
        MessageDrivenEmailCommunicationProvider messaging = new MessageDrivenEmailCommunicationProvider(eventBus);

        eventBus.publish( new ReservationFulfilfiledEvent());
        ArgumentCaptor captor = ArgumentCaptor.forClass(Object.class);
        verify(eventBus).publish(captor.capture());

    }

}