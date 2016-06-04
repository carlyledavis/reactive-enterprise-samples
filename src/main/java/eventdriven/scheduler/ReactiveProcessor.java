package eventdriven.scheduler;

import eventdriven.commands.Command;
import eventdriven.commands.CommandBus;
import events.EventBus;
import eventdriven.messaging.MessageDrivenEmailCommunicationProvider;
import eventdriven.payments.MessageDrivenPaymentProcessor;
import eventdriven.reservation.MessageDrivenFlightInventory;
import eventdriven.reservation.MessageDrivenReservationManager;

import javax.inject.Inject;

public class ReactiveProcessor {

    private final CommandBus commandBus;
    private final EventBus eventBus;

    @Inject
    public ReactiveProcessor(CommandBus commandBus, EventBus eventBus ){
        this.commandBus = commandBus;
        this.eventBus = eventBus;
        wireEventHandlers( eventBus );
    }

    private void wireEventHandlers(EventBus eventBus) {
        new MessageDrivenEmailCommunicationProvider(eventBus).subscribeTo(eventBus);
        new MessageDrivenReservationManager().subscribeTo(eventBus);
        new MessageDrivenFlightInventory().subscribeTo(eventBus);
        new MessageDrivenPaymentProcessor().subscribeTo(eventBus);
    }

    public void handle( Command command ){
        commandBus.execute( command );
    }
}
