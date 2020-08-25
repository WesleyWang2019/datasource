package jvm;

import sun.misc.Launcher;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author Wesley Wang
 * @date 2020/8/13 15:02
 */
public class ClassLoadTest {
    public static void main(String[] args) {
        System.out.println("bootStrapClassLoader的加载路径---------------------");
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL urL : urLs) {
            System.out.println(urL);
        }
        URLClassLoader systemClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        System.out.println("extClassLoader的加载路径-------------------------");
        URL[] urLs1 = systemClassLoader.getURLs();
        for (URL url : urLs1) {
            System.out.println(url);
        }



    }
}
