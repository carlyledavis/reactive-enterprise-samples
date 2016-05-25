package payments;

import events.EventBus;
import events.EventDriven;

public class MessageDrivenPaymentProcessor extends PaymentProcessor implements EventDriven{
    public void subscribeTo(EventBus eventBus) {
    }
}
