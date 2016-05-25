package events;

public interface EventBus {
    void register(Class clazz, EventDriven eventDriven);
    void publish(Object event);
}
