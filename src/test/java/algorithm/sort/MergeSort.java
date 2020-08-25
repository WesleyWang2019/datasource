package algorithm.sort;

import java.util.Arrays;

/**
 * @author Wesley Wang
 * @date 2020/7/24 17:15
 * 归并排序 对于一个二分后有序数组排序 稳定
 * 时间复杂度 O*LOG2 N
 * 空间复杂度 O(N)
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {1, 4, 7, 8, 3, 6, 9};
        Arrays.stream(sort(arr, 0, arr.length)).forEach(a -> System.out.print(a + "\t"));
    }

    static int[] sort(int[] arr, int left, int right) {
        if (left == right) return arr;
        int mid = left + (right - left) >> 1;
        return merge(arr, left, right - 1, mid);

    }

    private static int[] merge(int[] arr, int left, int right, int mid) {
        int[] temp = new int[right + 1 - left];
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            if (arr[i] >= arr[j]) {
                temp[k++] = arr[j++];
            } else {
                temp[k++] = arr[i++];
            }
        }
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        return temp;
    }
}
