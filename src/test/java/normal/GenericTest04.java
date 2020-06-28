package normal;



/**
 * @author Wesley Wang
 * @date 2019/7/2 17:56
 */
public class GenericTest04 {

    public static void main(String[] args) {
        System.out.println(raw(new String("1")));
    }
    public static <T> T raw(T t){
        return (T) new Integer("1");
    }
}
