package eventdriven.commands;

import eventdriven.events.EventBus;
import models.Reservation;
import reservation.events.CustomerTicketPurchaseInitiatedEvent;

import javax.inject.Inject;

public class SchedulingService {


    private final EventBus eventBus;

    @Inject
    public SchedulingService(EventBus eventBus){
        this.eventBus = eventBus;
    }

    public void handle( SecureReservationCommand command ){
        eventBus.publish( new CustomerTicketPurchaseInitiatedEvent( command.getPaymentInformation(),
                command.getItinerary()));
    }

}
