package eventdriven.reservation;

import eventdriven.events.EventBus;
import eventdriven.events.EventDriven;
import eventdriven.events.TicketPurchasedEvent;
import models.Itinerary;
import models.PaymentConfirmation;
import models.Reservation;
import models.SeatSelection;
import payments.events.PaymentFulfilledEvent;
import reservation.Flight;
import reservation.FlightInventory;
import reservation.events.ReservationFulfilledEvent;

import java.util.List;

public class MessageDrivenFlightInventory extends FlightInventory implements EventDriven {

    private EventBus eventBus;
    private boolean ticketIssued = false;
    private boolean seatSelected = false;
    private boolean notMessaged = true;

    public MessageDrivenFlightInventory(List<Flight> flights) {
        super(flights);
    }

    public void subscribeTo(EventBus eventBus) {
        this.eventBus = eventBus;
        eventBus.register(PaymentFulfilledEvent.class, this);
        eventBus.register(TicketPurchasedEvent.class, this);
    }

    public void on(PaymentFulfilledEvent event) {

        CustomerTicketConfirmation ticketConfirmation = purchaseTicket(event.getItinerary());
        ticketIssued = true;

        eventBus.publish(  new TicketPurchasedEvent(event.getPaymentConfirmation(), ticketConfirmation.getItinerary()));

        updateReservationState(event.getPaymentConfirmation(),
                new Reservation(event.getItinerary(), null));
    }

    public void on( TicketPurchasedEvent event ){
        Itinerary itinerary = event.getItinerary();
        Flight flight = getFlight(itinerary.getFlightIdentifier());

        SeatSelection confirmedSeatSelection = flight.selectSeat(itinerary.getSeatSelection());
        seatSelected = true;

        eventBus.publish(new SeatSelectionConfirmedEvent(
                confirmedSeatSelection));

        updateReservationState(event.getPaymentConfirmation(),
                new Reservation(event.getItinerary(), confirmedSeatSelection));
    }

    private void updateReservationState(PaymentConfirmation paymentCofirmation, Reservation reservation) {
        if( ticketIssued && seatSelected && notMessaged ){
            notMessaged = false;
            eventBus.publish(new ReservationFulfilledEvent(paymentCofirmation,reservation));
        }
    }

}
