package designModern.event.myFirstEventTest;

public class MyEventListener2 implements MyEventListenerInterface {
    //事件发生后的回调方法
    public void fireCusEvent(Event e){
        MySourceObject Object = (MySourceObject) e.getSource();
        System.out.println("My name has been changed2!");
        System.out.println("I got a new name,named2 \""+Object.sendEmail()+"\"");
    }

}
