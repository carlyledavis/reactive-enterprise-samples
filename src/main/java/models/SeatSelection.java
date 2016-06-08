package models;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

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


    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return reflectionEquals(this, obj );
    }
}
