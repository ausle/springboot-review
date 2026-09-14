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

import static org.junit.Assert.assertTrue;

public class MybatisTest1 {

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
    public void testNativeLimitPageQuery() {
        int pageNum = 2;   // 要查询第 2 页，页码从 1 开始
        int pageSize = 5;  // 每页 5 条
        int offset = (pageNum - 1) * pageSize;

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            TaskMapper taskMapper = sqlSession.getMapper(TaskMapper.class);

            // 1. 查询总记录数，供前端计算总页数。
            int total = taskMapper.countTasksByUserId("asule");

            // 2. 不调用 PageHelper，直接把 offset 和 pageSize 传给 Mapper 的 LIMIT。
            List<Task> tasks = taskMapper.queryTasksByPage("asule", offset, pageSize);

            int totalPages = (total + pageSize - 1) / pageSize;
            System.out.println("当前页：" + pageNum);
            System.out.println("每页条数：" + pageSize);
            System.out.println("总记录数：" + total);
            System.out.println("总页数：" + totalPages);
            System.out.println("当前页数据：" + tasks);

            assertTrue(tasks.size() <= pageSize);
        }
    }

}
