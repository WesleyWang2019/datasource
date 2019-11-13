package normal.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Wesley Wang
 * @date 2019/6/28 9:58
 */
public class TestJDK {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        HashMap<String,Object> map = new HashMap<>(16);

        map.size();
        map.put("1",1);
        Class<HashMap> mapClass = HashMap.class;
        Method c = mapClass.getDeclaredMethod("capacity");
        c.setAccessible(true);
        System.out.println(c.invoke(map));
        System.out.println(map.size());


    }
}
