package eventdriven.reservation;

import models.SeatSelection;

public class SeatSelectionConfirmedEvent {
    private final SeatSelection seatSelection;

    public SeatSelectionConfirmedEvent(SeatSelection seatSelection) {
        this.seatSelection = seatSelection;
    }
}
