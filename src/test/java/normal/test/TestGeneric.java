package normal.test;

/**
 * @author Wesley Wang
 * @date 2019/7/1 15:42
 */
public class TestGeneric {
    public static void main(String[] args) {
        Plate<? super Fruit> p = new Plate<Fruit>(new Fruit());
        p.set(new Fruit());
        p.set(new Apple());
        Object object = p.get();


        Plate<?extends Fruit> p2 = new Plate<>(new Apple());
        Fruit fruit = p2.get();


    }
}

//LEVEL 1
class Food {
}

//LEVEL 1
class Fruit extends Food {
}

class Meat extends Food {
}


//LEVEL 2
class Apple extends Fruit {
}

class Banana extends Fruit {
}

class Pork extends Fruit {
}

//LEVEL 3
class Beef extends Meat {
}

//LEVEL 4
class RedApple extends Apple {
}

class GreenApple extends Apple {
}

class Plate<T> {
    private T item;

    public Plate(T t) {
        item = t;
    }

    public void set(T t) {
        item = t;
    }

    public T get() {
        return item;
    }
}
