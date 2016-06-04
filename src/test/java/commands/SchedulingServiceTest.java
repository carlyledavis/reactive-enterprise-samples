package commands;

import events.EventBus;
import models.PaymentInformation;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import reservation.events.ReservationCreatedEvent;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SchedulingServiceTest {

    private PaymentInformation paymentInformation;

    @Test
    public void shouldPublishAReservationCreatedEventWhenCommandIsExecutedSuccessfully(){
        EventBus eventBus = mock(EventBus.class);
        SchedulingService service = new SchedulingService(eventBus);

        paymentInformation = new PaymentInformation();
        service.handle( new SecureReservationCommand(paymentInformation));

        ArgumentCaptor captor = ArgumentCaptor.forClass(Object.class);
        verify( eventBus ).publish(captor.capture());

        ReservationCreatedEvent createdEvent = (ReservationCreatedEvent) captor.getValue();
        assertThat(createdEvent).isNotNull();
        assertThat(createdEvent.getPaymentInformation()).isSameAs(paymentInformation);
    }

}