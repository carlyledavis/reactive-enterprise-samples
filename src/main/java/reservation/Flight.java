package reservation;

import models.SeatSelection;

import java.util.UUID;

public interface Flight {
    SeatSelection selectSeat(SeatSelection seatSelection);
    UUID getId();
}
