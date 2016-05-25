package messaging;

import events.EventBus;
import events.EventDriven;

public class MessageDrivenMessagingProvider extends MessagingProvider implements EventDriven {
    public void subscribeTo(EventBus eventBus) { }
}
