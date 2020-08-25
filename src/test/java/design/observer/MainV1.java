package design.observer;

import basic.ObjectCreate;
import io.netty.channel.EventLoopGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wesley Wang
 * @date 2020/7/13 16:12
 */
public class MainV1 {

}

class Baby {
    boolean cry = false;
    List<Observer> observer = new ArrayList<>();

    {
        observer.add(new Mum());
        observer.add(new Brother());
    }

    public boolean isCry() {
        return cry;
    }

    public void wakeUp() {
        System.out.println("cry cry cry..............");
        cry = true;
        for (Observer o : observer) {

        }
    }
}

abstract class Event<T> {
    abstract T getSource();
}

//事件类 fire Event
class WakeUpEvent extends Event<Child> {
    long timestamp;
    String loc;
    Child source;

    public WakeUpEvent(long timestamp, String loc, Child source) {
        this.timestamp = timestamp;
        this.loc = loc;
        this.source = source;
    }

    @Override
    Child getSource() {
        return source;
    }
}

interface Observer {
    void actionOnWakeUp(WakeUpEvent event);
}


class Mum implements Observer {
    @Override
    public void actionOnWakeUp(WakeUpEvent event) {
        feed();
    }

    public void feed() {
        System.out.println("mum feeding......");
    }
}

class Brother implements Observer {
    @Override
    public void actionOnWakeUp(WakeUpEvent event) {
        feed();
    }

    public void feed() {
        System.out.println("brother feeding......");
    }
}



