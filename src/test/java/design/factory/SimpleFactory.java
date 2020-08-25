package design.factory;

/**
 * @author Wesley Wang
 * @date 2020/7/10 14:06
 */
public class SimpleFactory {
    public Car createCar() {

        return new Car();

    }

    public Plan createPlan() {

        return new Plan();

    }
}
