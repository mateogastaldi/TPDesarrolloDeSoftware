package tp;

import java.util.ArrayList;

public abstract class EventManager{
    private ArrayList<EventListener> listeners;

    public EventManager(){
        this.listeners = new ArrayList<>();
    }
    public void addEventListener(EventListener listener){
        this.listeners.add(listener);
    }
    public void notifyListeners(Pedido p){
        for (EventListener l : listeners) {
            l.update(p);
        }
    }

}
