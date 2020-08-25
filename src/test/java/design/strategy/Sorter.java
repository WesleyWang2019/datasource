package design.strategy;

/**
 * @author Wesley Wang
 * @date 2020/7/10 10:12
 */
public class Sorter<T> {

    public T[] sort(T[] arr,Comparator<T> comparator) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minPos = i;
            for (int j = i; j < arr.length - 1; j++) {
                minPos = comparator.compare(arr[j] , arr[minPos]);
            }
            swap(arr, i, minPos);
        }
        return arr;
    }

    private void swap(T[] arr, int i, int j) {
        if (i != j) {
            T temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

    }
}
