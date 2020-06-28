package normal;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wesley Wang
 * @date 2019/7/2 11:53
 */
public class TypeErasureTest {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        list.toArray();
        System.out.println(list.getClass().equals(list2.getClass()));
    }
}
