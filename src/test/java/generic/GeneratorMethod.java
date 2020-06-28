package generic;

/**
 * @author Wesley Wang
 * @date 2020/6/18 14:20
 * 1、public与返回值中间<T>，可以理解为声明此方法为泛型方法
 * 2、只有声明了<T>的方法才是泛型方法，泛型类中的使用了泛型的成员方法并不是泛型方法
 * 3、<T>表明该方法将使用泛型类型T，此时才可以在方法中使用泛型类型T
 * 4、与泛型类的定义一样，此处T可以随便写为任意标识，常见的T、E、K、V等形式的参数常用于表示泛型
 */
public class GeneratorMethod {
    public <T> T genericMethod(Class<T> tClass) throws IllegalAccessException, InstantiationException {
        T instance = tClass.newInstance();
        return instance;
    }
}

class GenericTest<T>{
    private T key;
    public GenericTest(T key){
        this.key = key;
    }
    //虽然使用了泛型，但是这并不是一个泛型方法
    public T getKey(){
        return key;
    }
}
