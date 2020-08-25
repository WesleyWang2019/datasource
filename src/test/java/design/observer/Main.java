package design.observer;

/**
 * @author Wesley Wang
 * @date 2020/7/13 16:02
 */
public class Main {
    public static void main(String[] args) {
        Child child = new Child();
        child.wakeUp();
    }
}

class Dad {
    public void feed() {
        System.out.println("feeding......");
    }
}

class Child {
    boolean cry = false;
    Dad dad = new Dad();

    public boolean isCry() {
        return cry;
    }

    public void wakeUp() {
        System.out.println("cry cry cry..............");
        cry = true;
        dad.feed();
    }
}
