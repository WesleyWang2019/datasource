package generic;

/**
 * @author Wesley Wang
 * @date 2020/6/17 21:02
 * <p>
 * 未传入泛型实参时，与泛型类的定义相同，在声明的时候，需将泛型的声明也一起加到类中
 * 即：class FruitGenerator<T> implements Generator<T>
 * 如果不声明泛型，如： class FruitGenerator implements Generator<T>，编译器会报错
 */
public class FruitGenerator<T> implements Generator<T> {
    @Override
    public T next() {
        return null;
    }
}

/**
 * 传入泛型实参时：
 */
class FruitGenerator2 implements Generator<String> {

    @Override
    public String next() {
        return null;
    }
}

//通配符？
