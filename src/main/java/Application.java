import models.*;

public class Application {

    //In process execution of the defined flow
    public static void main(String... args ){
        double travelCost = 150.00;
        SeatSelection seatSelection = new SeatSelection( "15F" );

        PaymentConfirmation paymentConfirmation = secureFunds(travelCost, new FundingSource());
        Reservation reservation = purchaseTicket(new Itinerary("PHL", "LAS"), paymentConfirmation, seatSelection);
        EmailConfirmation logEmail = sendEmail(reservation, new EmailAddress("cdavis@thoughtworks.com" ));
    }

    private static PaymentConfirmation secureFunds(double cost, FundingSource fundingSource) {
        return null;
    }

    private static EmailConfirmation sendEmail(Reservation reservation, EmailAddress emailAddress) {
        return null;
    }

    private static Reservation purchaseTicket(Itinerary itinerary, PaymentConfirmation fundingSource, SeatSelection seatSelection) {
        return null;
    }
}
