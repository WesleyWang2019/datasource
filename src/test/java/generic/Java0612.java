package generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wesley Wang
 * @date 2020/6/12 13:40
 */
public class Java0612 {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();
        Class classString = stringList.getClass();
        Class classInteger = integerList.getClass();
        if (classString.equals(classInteger))
            System.out.println("class is the same");
    }
}
