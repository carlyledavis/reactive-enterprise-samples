package scheduler;

import messaging.MessagingProvider;
import models.*;
import payments.PaymentProcessor;
import reservation.Flight;
import reservation.FlightInventory;
import reservation.ReservationManager;

import javax.inject.Inject;

public class ProceduralProcessor {

    PaymentProcessor payments;
    MessagingProvider messaging;
    ReservationManager reservations;
    FlightInventory flightInventory;

    @Inject
    public ProceduralProcessor(PaymentProcessor payments,
                               MessagingProvider messaging,
                               ReservationManager reservations,
                               FlightInventory flightInventory ){
        this.payments = payments;
        this.messaging = messaging;
        this.reservations = reservations;
        this.flightInventory = flightInventory;
    }

    //In process execution of the defined flow
    public void processReservation( Itinerary draft,
                                    SeatSelection seatSelection,
                                    PaymentInformation paymentInformation,
                                    EmailAddress emailAddress ){
        PaymentConfirmation paymentConfirmation = payments.secureFunds(draft, paymentInformation);
        Reservation reservation = reservations.createItinerary(draft, paymentConfirmation, seatSelection);
        Flight flight = flightInventory.getFlight(draft.getFlightIdentifier());
        SeatSelection confirmedSeat = flight.selectSeat(seatSelection);
        EmailConfirmation logEmail = messaging.sendEmailConfirmation(reservation, emailAddress);
    }

}
