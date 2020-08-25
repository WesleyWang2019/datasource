package design.strategy;

import java.util.Arrays;

/**
 * @author Wesley Wang
 * @date 2020/7/10 10:12
 */
public class Main {
    public static void main(String[] args) {
        Sorter<Cat> sorter = new Sorter();
//        int[] arr = new int[]{4, 5, 2, 1, 6, 9};
        Cat[] arr = new Cat[]{new Cat(1, 2), new Cat(2, 3)};
        System.out.println(Arrays.toString(sorter.sort(arr, new CatComparator())));
        try {
            Class<?> aClass = Class.forName("123");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
