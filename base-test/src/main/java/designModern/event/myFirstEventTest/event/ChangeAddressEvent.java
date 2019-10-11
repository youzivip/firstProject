package designModern.event.myFirstEventTest.event;

import designModern.event.myFirstEventTest.listener.UpdateAddressListener;
import designModern.event.myFirstEventTest.source.MySourceObject;



public class ChangeAddressEvent extends MyEvent {


    public ChangeAddressEvent(Object source) {
        super(source);
    }

    @Override
    public void doSth() {
        System.out.println("我是一个需要改地址的事件，啦啦啦~~~~");
        registerListener();
     }

    @Override
    public void registerListener() {
        ((MySourceObject)source).register(new UpdateAddressListener());
    }


}
