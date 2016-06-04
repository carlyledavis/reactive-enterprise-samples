import email.EmailCommunicationProvider;
import email.EmailServer;
import email.models.Email;
import models.*;
import payments.PaymentProcessor;
import reservation.FlightInventory;
import reservation.ReservationManager;
import scheduler.ProceduralProcessor;

public class Application {

    //In process execution of the defined flow
    public static void main(String... args ){
        EmailServer server = email -> { };

        double travelCost = 150.00;
        SeatSelection seatSelection = new SeatSelection( "15F" );

        PaymentConfirmation paymentConfirmation = secureFunds(travelCost, new PaymentInformation());
        Reservation reservation = createItinerary(new Itinerary("PHL", "LAS"), paymentConfirmation, seatSelection);
        EmailConfirmation logEmail = sendEmail(reservation, new EmailAddress("fake-email@email.com" ));

        ProceduralProcessor proceduralProcessor = new ProceduralProcessor( new PaymentProcessor(),
                new EmailCommunicationProvider(server),
                new ReservationManager(),
                new FlightInventory() );
        proceduralProcessor.processReservation( null, null, null, null );
    }

    private static PaymentConfirmation secureFunds(double cost, PaymentInformation paymentInformation) {
        return null;
    }

    private static EmailConfirmation sendEmail(Reservation reservation, EmailAddress emailAddress) {
        return null;
    }

    private static Reservation createItinerary(Itinerary itinerary, PaymentConfirmation fundingSource, SeatSelection seatSelection) {
        return null;
    }
}
