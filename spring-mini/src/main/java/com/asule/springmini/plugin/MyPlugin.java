package com.asule.springmini.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;

/*
    声明拦截哪个方法。这里表明拦截的是StatementHandler的prepare方法。还需传入该方法的参数类型。
 */
@Intercepts({
        @Signature(type = StatementHandler.class,
                method = "prepare",args = {Connection.class,Integer.class}
        )
})
public class MyPlugin implements Interceptor {


    //当执行动态代理对象的任何方法时，都会判断该方法是否是要拦截的方法。是的话，会调用intercept。
    //我们可以在拦截方法执行前做一些事情。
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("intercept");
        return invocation.proceed() ;//执行原方法
    }


    //创建对应的代理对象
    //如果传进来的对象是我们要拦截的类型，包括要拦截的方法（获取该对象类型的所有接口方法与我们的拦截方法进行比对），会去创建动态代理对象。否则，原对象返回。
    @Override
    public Object plugin(Object target) {
        Object wrap = Plugin.wrap(target, this);
        return wrap;
    }


    @Override
    public void setProperties(Properties properties) {
        System.out.println("配置:"+properties);
    }
}
