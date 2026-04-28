package com.asule.springbootreview;

import com.asule.springbootreview.bean.Student;
import com.asule.springbootreview.service.Test2Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class SpringbootRedisApplicationTests1 {

    private static final Logger log = LoggerFactory.getLogger(SpringbootRedisApplicationTests1.class);

    @Test
    void contextLoads() {
    }

    @Autowired
    Test2Service testService;
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void test1() {
        //key和value都会被序列化，默认使用的是JDK的序列化器。
        redisTemplate.opsForValue().set("name1","阿苏勒");
        String name = (String) redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }

    @Test
    void test2() {
        /*
            为RedisTemplate的value配置JSON序列化器，会进行自动的序列化和反序列化。
            但Redis存储的值中存在一些class信息，占用空间，在自动反序列化时会根据这些class信息反序列化。
                {
                    "@class": "com.asule.springbootreview.bean.Student",
                    "name": "达可蒙",
                    "age": 33,
                    "classList": [
                        "java.util.ArrayList",
                            [
                                "数学",
                                "语文"
                            ]
                        ]
                }
            如何去掉这些class信息？
            Redis提供了StringRedisTemplate，key和value的序列化都是按照字符串方式来处理。
         */
        List<String> classs=new ArrayList<>();
        classs.add("数学");
        classs.add("语文");
        Student student = new Student();
        student.setName("达可蒙");
        student.setAge(33);
        student.setClassList(classs);
        redisTemplate.opsForValue().set("student-name",student);
        Student student1 = (Student) redisTemplate.opsForValue().get("student-name");
        System.out.println(student1.toString());
    }

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    private final ObjectMapper mapper =new ObjectMapper();

    /*
        使用StringRedisTemplate，内部使用的是String序列化器，key和value都是String类型。
        当需要存储Java对象时，就需要我们手动的去处理。
     */
    @Test
    void test3() throws JsonProcessingException {
        List<String> classs=new ArrayList<>();
        classs.add("数学");
        classs.add("语文");
        Student student = new Student();
        student.setName("小奇说车");
        student.setAge(33);
        student.setClassList(classs);

        String value = mapper.writeValueAsString(student);
        stringRedisTemplate.opsForValue().set("student-string-name",value);

        String studentVal = stringRedisTemplate.opsForValue().get("student-string-name");

        Student student1 = mapper.readValue(studentVal, Student.class);
        log.info(student1.toString());
    }


    public String getArticle(){
        String key="user:100";
        Object o = redisTemplate.opsForValue().get(key);
        if (o!=null){
            synchronized (this){
                o = redisTemplate.opsForValue().get(key);
                if (o!=null){
                    return (String) o;
                }
                //查数据库
                //
                redisTemplate.opsForValue().set(key,"数据库查询结果", Duration.ofMillis(30));
                return "数据库查询结果";
            }
        }else{
            return (String) o;
        }
    }


}
