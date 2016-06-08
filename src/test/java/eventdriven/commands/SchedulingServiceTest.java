package eventdriven.commands;

import eventdriven.events.EventBus;
import models.Itinerary;
import models.PaymentInformation;
import models.SeatSelection;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import reservation.events.CustomerTicketPurchaseInitiatedEvent;

import static java.util.UUID.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SchedulingServiceTest {

    private PaymentInformation paymentInformation;

    @Test
    public void shouldPublishAReservationCreatedEventWhenCommandIsExecutedSuccessfully(){
        EventBus eventBus = mock(EventBus.class);
        SchedulingService service = new SchedulingService(eventBus);
        SeatSelection seatSelection = new SeatSelection("15F");

        paymentInformation = new PaymentInformation();
        service.handle( new SecureReservationCommand(new Itinerary(randomUUID(), seatSelection), paymentInformation));

        ArgumentCaptor captor = ArgumentCaptor.forClass(Object.class);
        verify( eventBus ).publish(captor.capture());

        CustomerTicketPurchaseInitiatedEvent createdEvent = (CustomerTicketPurchaseInitiatedEvent) captor.getValue();
        assertThat(createdEvent).isNotNull();
        assertThat(createdEvent.getPaymentInformation())
                .isEqualToComparingFieldByField(paymentInformation);
    }

}