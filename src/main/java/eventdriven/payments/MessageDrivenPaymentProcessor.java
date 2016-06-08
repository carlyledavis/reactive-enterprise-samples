package eventdriven.payments;

import eventdriven.events.EventBus;
import eventdriven.events.EventDriven;
import models.PaymentConfirmation;
import payments.Bank;
import payments.PaymentProcessor;
import payments.events.PaymentFulfilledEvent;
import reservation.events.CustomerTicketPurchaseInitiatedEvent;

public class MessageDrivenPaymentProcessor extends PaymentProcessor implements EventDriven{

    private EventBus eventBus;

    public MessageDrivenPaymentProcessor(Bank bank) {
        super(bank);
    }

    public void subscribeTo(EventBus eventBus) {
        this.eventBus = eventBus;
        eventBus.register( CustomerTicketPurchaseInitiatedEvent.class, this );
    }

    public void on( CustomerTicketPurchaseInitiatedEvent event ){
        PaymentConfirmation confirmation = secureFunds( event.getPaymentInformation());
        eventBus.publish( new PaymentFulfilledEvent(confirmation, event.getItinerary()));
    }
}
