package designModern.event.myFirstEventTest.event;

import java.util.EventObject;

public class SendEmailEvent extends EventObject {


    public SendEmailEvent(Object source) {
        super(source);
     }


}
