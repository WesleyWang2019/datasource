package normal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Wesley Wang
 * @date 2019/7/2 11:37
 */
public class GenericTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        sort(list);
        sort2(list);
    }

    public static <T extends Comparable<T>> List<T> sort(List<T> list) {
        return Arrays.asList(list.toArray((T[]) new Comparable[list.size()]));
    }

    public static <T extends Comparable<T>> T[] sort2(List<T> list) {
        return list.toArray((T[]) new Comparable[list.size()]);
    }
}
