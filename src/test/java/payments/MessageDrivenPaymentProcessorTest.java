package payments;

import eventdriven.events.EventBus;
import eventdriven.events.SimpleEventBus;
import eventdriven.payments.MessageDrivenPaymentProcessor;
import models.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import payments.events.PaymentFulfilledEvent;
import reservation.events.CustomerTicketPurchaseInitiatedEvent;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class MessageDrivenPaymentProcessorTest {

    private PaymentInformation paymentInformation;
    private Itinerary itinerary;
    private SeatSelection seatSelection;
    private PaymentConfirmation paymentConfirmation;
    private EventBus eventBus;

    @Before
    public void setUp() throws Exception {
        paymentInformation = mock(PaymentInformation.class);
        itinerary = mock(Itinerary.class);
        seatSelection = mock(SeatSelection.class);
        paymentConfirmation = mock(PaymentConfirmation.class);
        eventBus = spy(new SimpleEventBus());
    }

    @Test
    public void shouldHandleReservationConfirmation(){
        Bank bank = mock(Bank.class);
        when(bank.secureFunds(any())).thenReturn(paymentConfirmation);

        Reservation reservation = new Reservation(itinerary, new SeatSelection( "15F", true ));

        MessageDrivenPaymentProcessor processor = new MessageDrivenPaymentProcessor(bank);
        processor.subscribeTo(eventBus);
        processor.on(new CustomerTicketPurchaseInitiatedEvent(paymentInformation, itinerary));

        verify(bank).secureFunds(paymentInformation);
        ArgumentCaptor<PaymentFulfilledEvent> captor = ArgumentCaptor.forClass(PaymentFulfilledEvent.class);

        verify(eventBus).publish(captor.capture());

        assertThat(  captor.getValue()).isEqualToComparingFieldByField(
                new PaymentFulfilledEvent(paymentConfirmation, itinerary));
    }


}