package algorithm.sort;

/**
 * @author Wesley Wang
 * @date 2020/8/19 13:45
 */

import java.util.Arrays;

/**
 * 基数排序
 */
public class CountSort {

    public static void main(String[] args) {
        int[] arr = {1, 4, 7, 8, 3, 6, 9, 4};
        sort(arr);
//        Arrays.stream(arr).forEach(a -> System.out.print(a + "\t"));
    }

    static void sort(int[] arr) {

        //计算各位置的数量
        int[] count = new int[10];
        for (int i = 0; i < arr.length; i++) {
            //此位置计数加1
            count[arr[i]]++;
        }
        Arrays.stream(count).forEach(a -> System.out.print(a + "\t"));
        System.out.println("\n---------------");
        //将计数数组变为一个增量数组
        for (int i = 1; i < count.length; i++) {
            count[i] = count[i] + count[i - 1];
        }
        Arrays.stream(count).forEach(a -> System.out.print(a + "\t"));
        //新增一个数组用于存放排序后的数组
        int[] arrSort = new int[arr.length];
        for (int i = arr.length; i > 0; i--) {
            //将数据填充进去
            arrSort[--count[arr[i - 1]]] = arr[i - 1];
        }
//        //对原数组进行遍历

    }


}
