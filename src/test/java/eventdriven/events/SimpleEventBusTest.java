package eventdriven.events;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class SimpleEventBusTest {

    @Test
    public void shouldFireCalLbackWhenObjectOfRegisteredTypeisPublished(){
        SimpleEventBus bus = new SimpleEventBus();
        final boolean[] wasCalled = {false};

        bus.register(Object.class, new EventDriven() {
            @Override
            public void subscribeTo(EventBus eventBus) {

            }

            public void on( Object object ){
                wasCalled[0] = true;
            }
        });

        bus.publish( new Object());
        assertThat(wasCalled[0]).isTrue();
    }

    @Test
    public void shouldNotCallHandlerWhenExceptionTypeIsNotHandled(){
        SimpleEventBus bus = new SimpleEventBus();
        final boolean[] wasCalled = {false};

        bus.register(Object.class, new EventDriven() {
            @Override
            public void subscribeTo(EventBus eventBus) {

            }

            public void on( Object object ){
                wasCalled[0] = true;
            }
        });

        bus.publish("");
        assertThat(wasCalled[0]).isFalse();
    }

}