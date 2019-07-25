package designModern.event.myFirstEventTest.event;

import designModern.event.myFirstEventTest.listener.MyEventListener;

import java.util.EventListener;
import java.util.EventObject;

public abstract class abstractEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public abstractEvent(Object source) {
        super(source);
    }


    public abstract void doSth();



    public abstract void registerListener();

}
