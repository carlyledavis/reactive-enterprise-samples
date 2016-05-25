package events;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;

public class SimpleEventBus implements EventBus {

    private Map<Class, List<EventDriven>> listeners = new HashMap();

    public void register(Class clazz, EventDriven eventDriven ) {
        if( !listeners.containsKey(clazz)) {
            listeners.put(clazz, newArrayList());
        }

        listeners.get(clazz).add( eventDriven );
    }

    @Override
    public void publish(Object event) {
        if( listeners.containsKey(event.getClass())){
            listeners.get(event.getClass()).parallelStream().forEach(x -> {
                execute(event, x );
            });
        }
    }

    //Quick and ugly, should change this to annotation.
    private void execute(Object e, EventDriven handler){
        try {
            Method method = handler.getClass().getDeclaredMethod( "on", e.getClass());
            method.invoke(handler, e);
        } catch (Exception e1) {}

    }
}
