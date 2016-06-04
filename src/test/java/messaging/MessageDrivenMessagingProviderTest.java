package messaging;

import events.EventBus;
import events.SimpleEventBus;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import reservation.events.ReservationFulfilfiledEvent;

import static org.mockito.Mockito.*;

public class MessageDrivenMessagingProviderTest {

    @Test
    public void shouldSendOutConfirmationEmailWhenReservationIsConfirmed(){

        EventBus eventBus = spy(new SimpleEventBus());
        MessageDrivenMessagingProvider messaging = new MessageDrivenMessagingProvider(eventBus);

        eventBus.publish( new ReservationFulfilfiledEvent());
        ArgumentCaptor captor = ArgumentCaptor.forClass(Object.class);
        verify(eventBus).publish(captor.capture());

    }

}