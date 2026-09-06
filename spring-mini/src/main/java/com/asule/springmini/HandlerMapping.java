package com.asule.springmini;

import cn.hutool.core.annotation.AnnotationUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HandlerMapping {

    public Map<String,InvocationHandler> urlMapping(Set<Class<?>> classSet){
        // 初始化一个 Map 集合，用于存放映射关系
        HashMap<String, InvocationHandler> HandlerHashMap = new HashMap<>();
        // 遍历 Controller 集合（也就是所有带@Controller注解的类）
        for (Class<?> aClass : classSet) {
            //获取类上@RequestMapping注解的值
            String classReqPath = AnnotationUtil.
                    getAnnotationValue(aClass, com.asule.springmini.RequestMapping.class);
            System.out.println("类的请求路径:" + classReqPath);

            // 获取这个 class 类中的所有方法
            Method[] methods = aClass.getDeclaredMethods();
            System.out.println("类中方法数量为：" + methods.length);

            // 如果这个类中方法数量不为空
            if (methods.length != 0) {
                // 开始遍历这个类中的所有方法
                for (Method method : methods) {
                    // 判断每个方法上是否带有@RequestMapping注解
                    boolean flag = method.isAnnotationPresent(com.asule.springmini.RequestMapping.class);
                    // 如果当前方法上带有这个注解
                    if (flag){
                        // 获取方法上@RequestMapping注解的值
                        String methodReqPath = AnnotationUtil.
                                getAnnotationValue(method, com.asule.springmini.RequestMapping.class);
                        // 判断得到的值是否为空，不为空则获取对应的值
                        String reqPath = methodReqPath == null ||
                                methodReqPath.equals("") ? "" : methodReqPath;
                        System.out.println("方法上的请求路径:" + reqPath);
                        // 将得到的值封装成 InvocationHandler 对象
                        try {
                            // 放入一个当前类的实例对象，用于执行后面的类方法
                            InvocationHandler invocationHandler = new
                                    InvocationHandler(aClass.newInstance(), method);
                            // 使用 类的请求路径 + 方法的请求路径 作为Key
                            HandlerHashMap.put(classReqPath + reqPath,
                                    invocationHandler);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        // 将存放映射关系的Map集合返回
        return HandlerHashMap;
    }
}

