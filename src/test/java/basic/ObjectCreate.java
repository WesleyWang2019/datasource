package basic;

/**
 * @author Wesley Wang
 * @date 2020/7/8 19:55
 */
public class ObjectCreate {
    int m = 8;

    public static void main(String[] args) {
        ObjectCreate o = new ObjectCreate();
        //汇编  分配内存->初始化->建立关联
        //1、new               堆内存分配内存   m=0
        //2、invokespecial  <T.init>   执行完   m=8
        //3、astore           o指向这个生成的对象
    }
}

