package design.strategy;

import lombok.ToString;

/**
 * @author Wesley Wang
 * @date 2020/7/10 10:59
 */
@ToString
public class Cat {
    int weight, height;

    public Cat(int weight, int height) {
        this.weight = weight;
        this.height = height;
    }

    public int CompareTo(Cat cat) {
        if (this.weight < cat.weight) return -1;
        else if (this.weight > cat.weight) return 1;
        else return 0;
    }

}
