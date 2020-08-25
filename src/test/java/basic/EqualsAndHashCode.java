package basic;

/**
 * @author Wesley Wang
 * @date 2020/6/29 11:27
 */
public class EqualsAndHashCode {
    //如果不被重写（原生）的hashCode和equals是什么样的？
    //
    //        1  . 不被重写（原生）的hashCode值是根据内存地址换算出来的一个值。
    //
    //        2 .  不被重写（原生）的equals方法是严格判断一个对象是否相等的方法（object1 == object2）。

//我们先来看一下Object.hashCode的通用约定（摘自《Effective Java》第45页）
//
// 在一个应用程序执行期间，如果一个对象的equals方法做比较所用到的信息没有被修改的话，那么，对该对象调用hashCode方法多次，它必须始终如一地返回 同一个整数。在同一个应用程序的多次执行过程中，这个整数可以不同，即这个应用程序这次执行返回的整数与下一次执行返回的整数可以不一致。
//    如果两个对象根据equals(Object)方法是相等的，那么调用这两个对象中任一个对象的hashCode方法必须产生同样的整数结果。

// 如果两个对象根据equals(Object)方法是不相等的，那么调用这两个对象中任一个对象的hashCode方法，不要求必须产生不同的整数结果。然而，程序员应该意识到这样的事实，对于不相等的对象产生截然不同的整数结果，有可能提高散列表（hash table）的性能。

//     如果只重写了equals方法而没有重写hashCode方法的话，则会违反约定的第二条：相等的对象必须具有相等的散列码（hashCode）
//     同时对于HashSet和HashMap这些基于散列值（hash）实现的类.
}
