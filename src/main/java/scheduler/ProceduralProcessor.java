package scheduler;

import email.EmailCommunicationProvider;
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
        PaymentConfirmation paymentConfirmation = payments.secureFunds(paymentInformation);
        Reservation reservation = reservations.confirmItinerary(draft);
        Flight flight = flightInventory.getFlight(draft.getFlightIdentifier());
        reservation.setConfirmedSeat(flight.selectSeat(seatSelection));

        PurchaseConfirmation purchaseConfirmation = new PurchaseConfirmation(paymentConfirmation, reservation);
        messaging.sendEmail(purchaseConfirmation);

        return purchaseConfirmation;
    }

}
