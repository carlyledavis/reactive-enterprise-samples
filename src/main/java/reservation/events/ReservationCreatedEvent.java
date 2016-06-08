package reservation.events;

import models.Itinerary;
import models.PaymentInformation;
import models.Reservation;

public class ReservationCreatedEvent {
    private PaymentInformation paymentInformation;
    private Reservation reservation;

    public ReservationCreatedEvent(PaymentInformation paymentInformation, Reservation reservation) {
        this.paymentInformation = paymentInformation;
        this.reservation = reservation;
    }

    public PaymentInformation getPaymentInformation() {
        return paymentInformation;
    }

    public Reservation getReservation() {
        return reservation;
    }
}
