package com.asule.mybatis.app;

import java.util.List;

public interface UserMapper {

    @Select("select * from user where username = #{name} and age= #{age} and first_name = #{name}")
    public List<User> getUser(@Param("name") String name, @Param("age") Integer age);

    public User getUserById(Integer id);

}
