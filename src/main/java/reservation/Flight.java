package reservation;

import eventdriven.reservation.CustomerTicketConfirmation;
import models.Itinerary;
import models.SeatSelection;

import java.util.UUID;

public interface Flight {
    SeatSelection selectSeat(SeatSelection seatSelection);
    UUID getId();
    String getSource();
    String getDestination();

    CustomerTicketConfirmation purchaseTicket(Itinerary itinerary);
}
