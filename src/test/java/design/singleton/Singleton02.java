package design.singleton;

import design.factory.Car;

/**
 * @author Wesley Wang
 * @date 2020/7/10 16:56
 * 枚举类没有构造方法，可以防止序列化
 * 因为单例类的修饰是abstract的，所以没法实例化
 */
public class Singleton02 {
    private Singleton02() {

    }

    static enum Singleton {
        INSTANCE;
        private Singleton02 singleton02;

        private Singleton() {
            singleton02 = new Singleton02();
        }

        public Singleton02 getInstance() {
            return singleton02;
        }
    }

    public static Singleton02 getIn() {
        return Singleton.INSTANCE.getInstance();
    }

}
