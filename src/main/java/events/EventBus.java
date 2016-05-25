package events;

import java.util.concurrent.Callable;

public interface EventBus {
    void register(Class clazz, Callable callable );
}
