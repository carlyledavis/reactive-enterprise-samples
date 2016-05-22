package scheduler;

import messaging.MessagingProvider;
import models.*;
import payments.PaymentProcessor;
import reservation.Flight;
import reservation.FlightInventory;
import reservation.ReservationManager;

import javax.inject.Inject;

public class SynchronousProcessor {

    PaymentProcessor payments;
    MessagingProvider messaging;
    ReservationManager reservations;
    FlightInventory flightInventory;

    @Inject
    public SynchronousProcessor( PaymentProcessor payments,
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
                                    double travelCost,
                                    SeatSelection seatSelection,
                                    FundingSource fundingSource,
                                    EmailAddress emailAddress ){
        PaymentConfirmation paymentConfirmation = payments.secureFunds(travelCost, fundingSource);
        Reservation reservation = reservations.purchaseTicket(draft, paymentConfirmation, seatSelection);
        Flight flight = flightInventory.getFlight(draft.getFlightIdentifier());
        SeatSelection confirmedSeat = flight.reserveSeat(seatSelection);
        EmailConfirmation logEmail = messaging.sendEmailConfirmation(reservation, emailAddress);
    }

}
