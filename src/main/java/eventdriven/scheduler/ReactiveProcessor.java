package eventdriven.scheduler;

import eventdriven.events.EventBus;
import eventdriven.events.EventDriven;

import javax.inject.Inject;

public class ReactiveProcessor {
    private final EventBus eventBus;

    @Inject
    public ReactiveProcessor(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void wireEventHandlers(EventDriven eventHandler) {
        eventHandler.subscribeTo(eventBus);
    }
}
