package scheduler;

import messaging.MessagingProvider;
import models.*;
import payments.PaymentProcessor;
import reservation.ReservationManager;

public class SynchronousProcessor {

    PaymentProcessor payments;
    MessagingProvider messaging;
    ReservationManager reservations;
    
    //In process execution of the defined flow
    public void processReservation( double travelCost, SeatSelection seatSelection, FundingSource fundingSource){
        PaymentConfirmation paymentConfirmation = payments.secureFunds(travelCost, fundingSource);
        Reservation reservation = reservations.purchaseTicket(new Itinerary("PHL", "LAS"), paymentConfirmation, seatSelection);
        EmailConfirmation logEmail = messaging.sendEmail(reservation, new EmailAddress("cdavis@thoughtworks.com" ));
    }

}
