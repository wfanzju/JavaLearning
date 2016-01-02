package amazon;

import java.util.*;

/**
 * Created by fan on 11/28/15.
 */
public class Simulator {

}

class SimulationEventDispatcher {
    private final List<SimulationEventListener<SimulationEvent>> globalListeners =
            new ArrayList<>();
    private final Map<Class<? extends SimulationEvent>,
            List<SimulationEventListener<? extends SimulationEvent>>> listenerMap =
            new HashMap<>();
    private final PriorityQueue<SimulationEvent> eventQueue = new PriorityQueue<>();
    private boolean terminated = false;

    public boolean dispatchOneEvent() {
        if (terminated) {
            return false;
        }
        SimulationEvent event = eventQueue.poll();
        if (event == null) {
            return false;
        }
        for (SimulationEventListener<SimulationEvent> l : globalListeners) {
            l.handle(event);
        }

        for (SimulationEventListener<? extends SimulationEvent> listener :
                listenerMap.getOrDefault(event.getClass(), new ArrayList<>())) {
            ((SimulationEventListener) listener).handle(event);
        }
        return true;
    }
}

interface SimulationEventListener<T> extends EventListener {
    void handle(T event);
}

abstract class SimulationEvent implements Comparable<SimulationEvent>{
    Date eventTime;

    @Override
    public final int compareTo(SimulationEvent other) {
        return eventTime.compareTo(other.eventTime);
    }
}