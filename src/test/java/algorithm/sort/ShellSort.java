package algorithm.sort;

import java.util.Arrays;

/**
 * @author Wesley Wang
 * @date 2020/7/24 17:15
 * 希尔排序基于插入排序 不稳定
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {5, 3, 6, 8, 1, 7, 9, 4, 2};
        sort(arr);
        Arrays.stream(arr).forEach(a -> System.out.print(a + "\t"));
    }

    static int[] sort(int[] arr) {
        int h = 1;
        while (h <= arr.length / 3) {
            h = h * 3 + 1;
        }
        for (int gap = h; gap > 0; gap = (gap - 1) / 3) {
            for (int i = gap; i < arr.length; i++) {
                //从第二个位置开始排，将所有此间隔位置上的数据重新排
                for (int j = i; j > gap - 1; j -= gap) {
                    if (arr[j] < arr[j - gap])
                        BubbleSort.swap(arr, j - gap, j);
                }
            }
        }
        return arr;
    }
}
