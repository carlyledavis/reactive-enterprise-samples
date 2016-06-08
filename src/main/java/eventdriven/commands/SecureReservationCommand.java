package eventdriven.commands;

import models.Itinerary;
import models.PaymentInformation;
import models.SeatSelection;

import java.util.UUID;

public class SecureReservationCommand extends Command {
    private UUID reservationId;
    private PaymentInformation paymentInformation;
    private Itinerary itinerary;
    private SeatSelection seatSelection;

    public SecureReservationCommand(PaymentInformation paymentInformation) {
        super();
        this.paymentInformation = paymentInformation;
    }

    public UUID getReservationId() {
        return reservationId;
    }

    public PaymentInformation getPaymentInformation() {
        return paymentInformation;
    }

    public Itinerary getItinerary() {
        return itinerary;
    }

    public SeatSelection getSeatSelection() {
        return seatSelection;
    }
}
