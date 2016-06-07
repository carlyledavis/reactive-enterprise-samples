package eventdriven.scheduler;

import eventdriven.commands.Command;
import eventdriven.commands.CommandBus;
import eventdriven.email.MessageDrivenEmailCommunicationProvider;
import eventdriven.events.EventBus;
import eventdriven.events.EventDriven;
import eventdriven.payments.MessageDrivenPaymentProcessor;
import eventdriven.reservation.MessageDrivenFlightInventory;
import eventdriven.reservation.MessageDrivenReservationManager;
import models.Itinerary;
import models.Reservation;
import models.SeatSelection;
import reservation.Airline;

import javax.inject.Inject;
import java.util.Arrays;

import static com.google.common.collect.Lists.newArrayList;

public class ReactiveProcessor {

    private final CommandBus commandBus;
    private final EventBus eventBus;

    @Inject
    public ReactiveProcessor(CommandBus commandBus, EventBus eventBus) {
        this.commandBus = commandBus;
        this.eventBus = eventBus;
        wireEventHandlers(eventBus);
    }

    private void wireEventHandlers(EventBus eventBus) {
        Arrays.asList(new MessageDrivenEmailCommunicationProvider(email -> { }),
                new MessageDrivenFlightInventory(newArrayList()),
                new MessageDrivenReservationManager((itinerary, seatSelection) -> null),
                new MessageDrivenPaymentProcessor())
                    .forEach(x->x.subscribeTo(eventBus));
    }

    public void handle(Command command) {
        commandBus.execute(command);
    }
}
