package models;

import eventdriven.commands.SecureReservationCommand;

public class Reservation {
    private final Itinerary itinerary;
    private final SeatSelection seatSelection;


    public Reservation(Itinerary itinerary, SeatSelection seatSelection) {

        this.itinerary = itinerary;
        this.seatSelection = seatSelection;
    }

    public Itinerary getItinerary() {
        return itinerary;
    }

    public SeatSelection getSeatSelection() {
        return seatSelection;
    }
}
