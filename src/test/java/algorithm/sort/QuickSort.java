package algorithm.sort;

import java.util.Arrays;

/**
 * @author Wesley Wang
 * @date 2020/7/24 14:42
 * 快速排序
 */
public class QuickSort {
    static  int count = 0;
    public static void main(String[] args) {
        int[] arr = {5, 3, 6, 8, 1};
        sort(arr, 0, arr.length);
        Arrays.stream(arr).forEach(a -> System.out.print(a + "\t"));
        System.out.println("------" + count);
    }

    static void sort(int[] arr, int left, int right) {
//        System.out.println("left:" + left + "===right:" + right);
        if (left >= right) return;
        int mid = partition(arr, left, right);
        sort(arr, left, mid);
        System.out.println("----------------");
        sort(arr, mid + 1, right);
    }

    private static int partition(int[] arr, int leftBound, int rightBound) {
        int pivot = arr[rightBound - 1];
        int left = leftBound;
        int right = rightBound - 2;
        while (left <= right) {
            while (left <= right && arr[left] <= pivot) left++;
            while (left <= right && arr[right] > pivot) right--;
            if (left < right) BubbleSort.swap(arr, left, right);
        }
        BubbleSort.swap(arr, left, rightBound - 1);
        count++;
        return left;
    }

}
