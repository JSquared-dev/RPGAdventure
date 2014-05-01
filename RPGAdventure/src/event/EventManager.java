package event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EventManager {

    @SuppressWarnings("rawtypes")
    private Map<Class,Collection> eventHandlers = new HashMap<Class,Collection>();
    
    public EventManager() {
        // TODO Auto-generated constructor stub
    }

    public <T> void listen(Class<? extends Event<T>> event, T handler) {
        if (eventHandlers.containsKey(event)) {
            @SuppressWarnings("unchecked")
            Collection<T> listeners = eventHandlers.get(event);
            listeners.add(handler);
        }
        else {
            Collection<T> listeners = new ArrayList<T>();
            listeners.add(handler);
            eventHandlers.put(event, listeners);
        }
        
    }
    
    public <T> int notify(Event<T> event) {
        if (eventHandlers.containsKey(event.getClass())) {
            int i = 0;
            @SuppressWarnings("unchecked")
            Collection<T> handlerList = eventHandlers.get(event.getClass());
            Iterator<T> iter = handlerList.iterator();
            while (iter.hasNext()) {
                event.notify(iter.next());
                i++;
            }
            return i;
        }
        else {
            return 0;
        }
    }
}
