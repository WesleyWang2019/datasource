package generic;

import normal.GenericTest;

/**
 * @author Wesley Wang
 * @date 2020/6/17 20:53
 */
public class GenericMain {
    public static void main(String[] args) {
        Generic<Integer> genericInteger = new Generic<>(123);
        Generic<String> genericString = new Generic<>("456tttt");
        System.out.println(genericInteger.getKey());
        System.out.println(genericString.getKey());
        //泛型的类型参数只能是类类型，不能是简单类型
        //不能对确切的泛型类型使用instanceof操作
//        if (ex_num instanceof Generic<String>){
//
//        }
    }
}
