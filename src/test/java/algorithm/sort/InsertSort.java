package algorithm.sort;

import java.util.Arrays;

/**
 * @author Wesley Wang
 * @date 2020/7/24 16:56
 * n*n
 * 插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {5, 3, 6, 8, 1, 7, 9, 4, 2};
        sort(arr);
        Arrays.stream(arr).forEach(a -> System.out.print(a + "\t"));
    }

    static int[] sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            //从第二个位置开始排
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1])
                    BubbleSort.swap(arr, j-1, j);
            }
        }
        return arr;
    }
}
