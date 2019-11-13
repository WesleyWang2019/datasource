package normal.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Wesley Wang
 * @date 2019/7/2 17:56
 */
public class GenericTest02 {
    public static <T extends Comparable<T>> List<T> sort(List<T> list) {
        return Arrays.asList(list.toArray((T[]) new Comparable[list.size()]));
    }

    public static <T extends Comparable<T>> T[] sort2(List<T> list) {
        return list.toArray((T[]) new Comparable[list.size()]);
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        System.out.println(sort(list).getClass());
        System.out.println(sort2(list).getClass());

    }
}
