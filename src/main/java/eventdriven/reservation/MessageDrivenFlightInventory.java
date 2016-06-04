package eventdriven.reservation;

import eventdriven.events.EventBus;
import eventdriven.events.EventDriven;
import models.Reservation;
import payments.events.PaymentFulfilledEvent;
import reservation.Flight;
import reservation.FlightInventory;

import java.util.List;

public class MessageDrivenFlightInventory extends FlightInventory implements EventDriven{

    public MessageDrivenFlightInventory(List<Flight> flights) {
        super(flights);
    }

    public void subscribeTo(EventBus eventBus) {
        eventBus.register(PaymentFulfilledEvent.class, this );
    }

    public void on(PaymentFulfilledEvent event) {
        Reservation reservation = event.getReservation();
        Flight flight = getFlight(reservation.getItinerary().getFlightIdentifier());
        flight.selectSeat(reservation.getSeatSelection());

    }
}
