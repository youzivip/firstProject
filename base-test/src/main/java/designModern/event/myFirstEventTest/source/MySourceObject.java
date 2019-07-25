package designModern.event.myFirstEventTest;

import java.util.ArrayList;
import java.util.List;

public class MySourceObject {

    List<MyEventListenerInterface> listeners = new ArrayList<>();

    public void register(MyEventListenerInterface listener){
        synchronized ("a"){
            listeners.add(listener);
        }
    }

    public void doListen(Event event){
        for(MyEventListenerInterface listener:listeners){
            listener.fireCusEvent(event);
        }
    }

    public String sendEmail(){
       return "I want to send Email";
    }
}
