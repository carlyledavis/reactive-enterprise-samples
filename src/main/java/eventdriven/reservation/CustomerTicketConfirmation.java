package eventdriven.reservation;

import models.Itinerary;

public class CustomerTicketConfirmation {
    private Itinerary itinerary;

    public CustomerTicketConfirmation(Itinerary itinerary) {
        this.itinerary = itinerary;
    }

    public Itinerary getItinerary() {
        return itinerary;
    }
}
