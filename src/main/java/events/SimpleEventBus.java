package events;

import java.util.concurrent.Callable;

public class SimpleEventBus implements EventBus {
    public void register(Class clazz, Callable callable) { }
}
