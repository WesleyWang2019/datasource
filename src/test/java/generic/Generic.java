package generic;

/**
 * @author Wesley Wang
 * @date 2020/6/17 20:52
 */
public class Generic<T> {
    private T key;

    public Generic(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }
}
