package normal;

/**
 * @author Wesley Wang
 * @date 2019/7/2 17:56
 */
public class GenericTest05 {


    public static void main(String[] args) {
        raw(new Integer(1));
        System.out.println(raw(new Integer(1)));
//        Integer raw = raw(new Integer("1"));

    }
    public static <T> T raw(T t){
        return (T) "1天";
    }
}
