package algorithm.sort;

import java.util.Arrays;

/**
 * @author Wesley Wang
 * @date 2020/7/24 14:42
 * 冒泡排序
 * 最坏n*n 最好 n（后续） 稳定的
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {5, 3, 6, 8, 1, 7, 9, 4, 2};
        int[] sort = sort(arr);
        Arrays.stream(sort).forEach(a -> System.out.print(a + "\t"));
    }

    static int[] sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
        return arr;
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
