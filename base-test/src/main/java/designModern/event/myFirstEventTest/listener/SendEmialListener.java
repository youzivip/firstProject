package designModern.event.myFirstEventTest;

public class MyEventListener implements MyEventListenerInterface {
    //事件发生后的回调方法
    public void fireCusEvent(Event e){
        MySourceObject Object = (MySourceObject) e.getSource();
        System.out.println("My name has been changed!");
        System.out.println("I got a new name,named \""+Object.sendEmail()+"\"");
    }

}
