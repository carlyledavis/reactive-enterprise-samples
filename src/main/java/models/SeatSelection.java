package models;

public class SeatSelection {
    private final String seat;
    private final boolean confirmation;

    public SeatSelection(String seat) {
        this( seat, false );
    }

    public SeatSelection(String seat, boolean confirmation) {
        this.seat = seat;
        this.confirmation = confirmation;
    }

    public String getSeat() {
        return seat;
    }
}
