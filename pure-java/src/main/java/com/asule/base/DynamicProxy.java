package com.asule.base;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy implements InvocationHandler {

    private Object targetObj;

    /**
     * @param proxy 代理对象本身
     * @param method 正在调用什么方法
     * @param args 调用方法的参数
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("start");
        Object invoke = method.invoke(targetObj, args);     // 用反射来调用真实对象的方法
        System.out.println("end");
        return invoke;
    }

    public Object getInstance(Object targetObj){
        this.targetObj=targetObj;
        Object proxyInstance = Proxy.newProxyInstance(
                targetObj.getClass().getClassLoader(),              // 用什么类加载器把生成出来的代理类加载到JVM里
                targetObj.getClass().getInterfaces(),               // 代理对象要实现哪些接口
                this);                                              // 代理对象的方法被调用时，统一交给谁来处理
        return proxyInstance;
    }


}
