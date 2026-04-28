package com.asule.springbootreview;

import com.asule.springbootreview.bean.Users;
import com.asule.springbootreview.mapper.UsersMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class Springboot1MybatisTest {

    @Autowired
    UsersMapper usersMapper;

    @Test
    public void test1() {
//        Users users = usersMapper.selectByPrimaryKey(41);
//        System.out.println(users);

        Users users1 = usersMapper.selectById(41);
        System.out.println(users1);
    }

}
