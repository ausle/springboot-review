package com.asule.base;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class App {

    public static void main(String[] args) {
//        Person person = new Student();
//        StudentProxy studentProxy = new StudentProxy(person);
//        studentProxy.doProxyWork();

        Person person1 = new Student();
        DynamicProxy dynamicProxy = new DynamicProxy();
        Person instance = (Person) dynamicProxy.getInstance(person1);
        instance.doWork();
    }


    public void ex(){
        Person person = new Student();
        // 生成代理对象
        Object proxyInstance = Proxy.newProxyInstance(
            person.getClass().getClassLoader(),         // 用什么类加载器把生成出来的代理类加载到JVM里
            person.getClass().getInterfaces(),          // // 代理对象要实现哪些接口
            new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("start");
                    Object invoke = method.invoke(person, args);
                    System.out.println("end");
                    return invoke;
                }
            });
    }

}
