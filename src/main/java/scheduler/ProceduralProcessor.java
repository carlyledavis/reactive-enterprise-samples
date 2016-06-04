package scheduler;

import messaging.EmailCommunicationProvider;
import models.*;
import payments.PaymentProcessor;
import payments.PurchaseConfirmation;
import reservation.Flight;
import reservation.FlightInventory;
import reservation.ReservationManager;

import javax.inject.Inject;

public class ProceduralProcessor {

    PaymentProcessor payments;
    EmailCommunicationProvider messaging;
    ReservationManager reservations;
    FlightInventory flightInventory;

    @Inject
    public ProceduralProcessor(PaymentProcessor payments,
                               EmailCommunicationProvider messaging,
                               ReservationManager reservations,
                               FlightInventory flightInventory ){
        this.payments = payments;
        this.messaging = messaging;
        this.reservations = reservations;
        this.flightInventory = flightInventory;
    }

    //In process execution of the defined flow
    public PurchaseConfirmation processReservation(Itinerary draft,
                                                   SeatSelection seatSelection,
                                                   PaymentInformation paymentInformation,
                                                   EmailAddress emailAddress ){
        PaymentConfirmation paymentConfirmation = payments.secureFunds(draft, paymentInformation);
        Reservation reservation = reservations.createItinerary(draft, paymentConfirmation, seatSelection);
        Flight flight = flightInventory.getFlight(draft.getFlightIdentifier());
        SeatSelection confirmedSeat = flight.selectSeat(seatSelection);

        PurchaseConfirmation purchaseConfirmation = new PurchaseConfirmation(paymentConfirmation, reservation, flight, confirmedSeat);
        messaging.sendEmail(purchaseConfirmation);

        return purchaseConfirmation;
    }

}
