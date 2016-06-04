package reservation;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FlightInventoryTest {

    private List<Flight> flights;
    private Flight flight;
    private UUID flightId;

    @Before
    public void setUp() throws Exception {
        flights = newArrayList();
        flight = mock(Flight.class);
        flightId = randomUUID();
    }

    @Test
    public void shouldRetrieveTheFlightBasedOnTheFlightIdentifier(){
        when(flight.getId()).thenReturn(flightId);
        flights.add(flight);

        FlightInventory inventory = new FlightInventory(flights);
        assertThat( inventory.getFlight(flightId)).isEqualTo(flight);
    }
}