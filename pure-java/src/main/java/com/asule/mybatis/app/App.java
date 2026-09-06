package com.asule.mybatis.app;

public class App {
    public static void main(String[] args) {
        UserMapper mapper =
                MapperProxyFactory.getMapper(UserMapper.class);

        mapper.getUser("asule",22);

    }
}
