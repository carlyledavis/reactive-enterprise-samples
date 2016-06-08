package eventdriven.commands;

import eventdriven.events.EventBus;
import models.PaymentInformation;
import models.Reservation;
import reservation.events.ReservationCreatedEvent;

import javax.inject.Inject;

public class SchedulingService {


    private final EventBus eventBus;

    @Inject
    public SchedulingService(EventBus eventBus){
        this.eventBus = eventBus;
    }

    public void handle( SecureReservationCommand command ){
        eventBus.publish( new ReservationCreatedEvent( new PaymentInformation(),
                new Reservation(command.getItinerary(), command.getSeatSelection())));
    }

}
