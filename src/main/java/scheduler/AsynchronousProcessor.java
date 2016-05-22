package scheduler;

import commands.Command;
import commands.CommandBus;
import events.EventBus;
import messaging.MessageDrivenMessagingProvider;
import payments.MessageDrivenPaymentProcessor;
import reservation.MessageDrivenFlightInventory;
import reservation.MessageDrivenReservationManager;

import javax.inject.Inject;

public class AsynchronousProcessor {

    private final CommandBus commandBus;
    private final EventBus eventBus;

    @Inject
    public AsynchronousProcessor(CommandBus commandBus, EventBus eventBus ){
        this.commandBus = commandBus;
        this.eventBus = eventBus;
        wireEventHandlers( eventBus );
    }

    private void wireEventHandlers(EventBus eventBus) {
        new MessageDrivenMessagingProvider().subscribeTo(eventBus);
        new MessageDrivenReservationManager().subscribeTo(eventBus);
        new MessageDrivenFlightInventory().subscribeTo(eventBus);
        new MessageDrivenPaymentProcessor().subscribeTo(eventBus);
    }

    public void handle( Command command ){
        commandBus.execute( command );
    }
}
