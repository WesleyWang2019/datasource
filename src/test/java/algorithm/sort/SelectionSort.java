package algorithm.sort;

import java.util.Arrays;

/**
 * @author Wesley Wang
 * @date 2020/7/23 15:37
 * 选择排序  n*n   1   不稳定
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = {5, 3, 6, 8, 1, 7, 9, 4, 2};
        sort(arr);
        Arrays.stream(arr).forEach(a -> System.out.print(a + "\t"));
    }

    static int[] sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[minIndex] > arr[j] ? j : minIndex;
            }
            BubbleSort.swap(arr, i, minIndex);
        }
        return arr;
    }
}
