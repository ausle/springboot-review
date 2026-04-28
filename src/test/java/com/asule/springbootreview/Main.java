package com.asule.springbootreview;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class) //指定测试运行器，结合junit来使用
@SpringBootTest //加载完整的spring应用上下文
// 测试类名最好以Test结尾，这个不做强制要求。
public class Main {

    // 测试方法需要是public
    @Test
    public void test1() {
    }
}
