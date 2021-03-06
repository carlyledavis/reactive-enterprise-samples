package eventdriven.reservation;

import eventdriven.events.EventBus;
import eventdriven.events.EventDriven;
import eventdriven.events.ItineraryConfirmedEvent;
import models.Reservation;
import payments.events.PaymentFulfilledEvent;
import reservation.Airline;
import reservation.ReservationManager;

public class MessageDrivenReservationManager extends ReservationManager implements EventDriven{

    private EventBus eventBus;

    public MessageDrivenReservationManager(Airline airline) {
        super(airline);
    }

    public void subscribeTo(EventBus eventBus) {
        this.eventBus = eventBus;
        eventBus.register(PaymentFulfilledEvent.class, this );
    }

    public void on(PaymentFulfilledEvent event){
        eventBus.publish( new ItineraryConfirmedEvent(confirmItinerary( event.getItinerary())));
    }
}
