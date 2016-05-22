package messaging;

import events.EventBus;
import events.EventDriven;

public class MessageDrivenMessagingProvider extends MessagingProvider implements EventDriven {
    @Override
    public void subscribeTo(EventBus eventBus) {

    }
}
