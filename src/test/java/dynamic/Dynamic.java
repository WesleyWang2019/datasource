package dynamic;

/**
 * @author Wesley Wang
 * @date 2020/6/22 19:59
 */
public class Dynamic {
    // 成员变量
    //编译看左边，运行看左边
    //成员方法
    //编译看左边，运行看右边
    //静态方法
    //编译看左边，运行看左边
    //静态和类相关，算不上重写，
    //只有非静态的成员方法，编译看左边，运行看右边
    //多态后不能使用子类特有的属性和方法

    //1) 发生继承，且一个父类有多个子类。
    //
    //2) 在每一个子类中对继承自父类的同一个方法实现了重写。
    //
    //3) 发生多态，即: 父类对象变量引用了不同的子类对象。
    // 向上转型  在多态中需要将子类的引用赋给父类对象
    //
    //4) 父类的对象变量调用了重写的方法，即: 发生了动态绑定。从而实现了多的价值。

    //多态有编译时多态 和运行时多态。
    //第一个是通过方法重载实现；第二个是通过方法覆盖实现（子类覆盖父类方法）。
    public static void main(String[] args) {
        Wine wine = new JNC();
        System.out.println(wine.getName());
        //定义父类数组
        Wine[] wines = new Wine[2];
        //定义两个子类
        JNC jnc = new JNC();
        JGJ jgj = new JGJ();

        //父类引用子类对象
        wines[0] = jnc;
        wines[1] = jgj;

        for(int i = 0 ; i < 2 ; i++){
            System.out.println(wines[i].toString() + "--" + wines[i].drink());
        }
        System.out.println("-------------------------------");

    }
}

 class Wine {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Wine(){
    }

    public String drink(){
        return "喝的是 " + getName();
    }

    /**
     * 重写toString()
     */
    public String toString(){
        return null;
    }
}

 class JNC extends Wine{
    public JNC(){
        setName("JNC");
    }

    /**
     * 重写父类方法，实现多态
     */
    public String drink(){
        return "喝的是 " + getName();
    }

    /**
     * 重写toString()
     */
    public String toString(){
        return "Wine : " + getName();
    }
}

 class JGJ extends Wine{
    public JGJ(){
        setName("JGJ");
    }

    /**
     * 重写父类方法，实现多态
     */
    public String drink(){
        return "喝的是 " + getName();
    }

    /**
     * 重写toString()
     */
    public String toString(){
        return "Wine : " + getName();
    }
}


