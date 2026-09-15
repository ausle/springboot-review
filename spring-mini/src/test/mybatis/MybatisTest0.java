package mybatis;

import com.asule.springmini.entity.Task;
import com.asule.springmini.mapper.TaskMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
        SqlSession session = sqlSessionFactory.openSession();
        try {
            // 通过sqlSession先获取到Mapper接口对象
            TaskMapper mapper = session.getMapper(TaskMapper.class);
            List<Task> tasks = mapper.getTasks("asule");
            System.out.println(tasks.size());
        } finally {
            session.close();
        }
    }

    @Test
    public void test(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        TaskMapper taskMapper = sqlSession.getMapper(TaskMapper.class);

        // 必须紧挨目标 Mapper 查询：请求第 1 页，每页 5 条。
        PageHelper.startPage(1, 5);
        List<Task> tasks = taskMapper.getTasks("asule");

        PageInfo<Task> pageInfo = new PageInfo<Task>(tasks);
        System.out.println("当前页数据：" + pageInfo.getList());
        System.out.println("总记录数：" + pageInfo.getTotal());
        System.out.println("总页数：" + pageInfo.getPages());
    }


}
