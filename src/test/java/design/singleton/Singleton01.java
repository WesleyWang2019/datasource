package design.singleton;

import java.beans.PropertyVetoException;

/**
 * @author Wesley Wang
 * @date 2020/7/10 16:53
 */
public class Singleton01 {

    private Singleton01() {
    }

    private static class Single {
        private final static Singleton01 single = new Singleton01();
    }

    public static Singleton01 getInstance() {
        return Single.single;
    }
}
