import com.asule.springmini.entity.Task;
import com.asule.springmini.mapper.TaskMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest0 {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setup() throws IOException {
        //从配置文件中，构建SqlSessionFactory
        String resource = "mybatis.xml";
        // 对配置文件进行加载，加载成了字节输入流。
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 什么是builder模式？
        // 什么时候使用builder模式，创建对象时，对象内部至少有四个以上的属性。
        // 好处：降低耦合?
        // 对配置文件进行了解析，最主要的职责是，构建SqlSessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);   //实际使用按单例的方式。
    }


    @Test
    public void testSelect1() throws IOException {
        // 默认的执行器类型是：ExecutorType.SIMPLE
        // openSession执行的逻辑是什么？
        SqlSession session = sqlSessionFactory.openSession();
        try {
            Task task = session.selectOne("com.asule.springmini.mapper.TaskMapper.queryTaskByMsg","07902748804");
            System.out.println(task);
        } finally {
            session.close();
        }
    }


    @Test
    public void testSelect2()  {
        // 默认的执行器类型是：ExecutorType.SIMPLE
        // openSession执行的逻辑是什么？
        SqlSession session = sqlSessionFactory.openSession();
        try {
            // 通过sqlSession先获取到Mapper接口对象
            TaskMapper mapper = session.getMapper(TaskMapper.class);
            Task task = mapper.queryTaskByMsg("07902748804");
            List<Task> tasks = mapper.getTasks("asule");
            System.out.println(task);
            System.out.println(tasks.size());
        } finally {
            session.close();
        }
    }
}
