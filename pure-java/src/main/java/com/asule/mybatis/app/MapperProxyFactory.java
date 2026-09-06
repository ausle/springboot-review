package com.asule.mybatis.app;

import com.asule.mybatis.app.handler.IntegerTypeHandler;
import com.asule.mybatis.app.handler.StringTypeHandler;
import com.asule.mybatis.app.handler.TypeHandler;

import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapperProxyFactory {

    private static Map<Class, TypeHandler> typeHandlerMap = new HashMap<>();

    static {
        typeHandlerMap.put(String.class, new StringTypeHandler());
        typeHandlerMap.put(Integer.class, new IntegerTypeHandler());
        try {
            // 加载数据库驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static <T> T getMapper(Class<T> target){
        Object o = Proxy.newProxyInstance(
                target.getClassLoader(),
                new Class[]{target},  // 创建实现了这些接口的代理对象
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        String url = "jdbc:mysql://localhost:3306/sql_study?useSSL=false&serverTimezone=Asia/Shanghai";
                        String username = "root";
                        String password = "qwerty";

                        // 加载数据库驱动，创建数据库连接
                        Connection connection = DriverManager.getConnection(url, username, password);

                        Select annotation = method.getAnnotation(Select.class);
                        String sql = annotation.value();

                        Map<String, Object> paramValueMapping = new HashMap<>();
                        Parameter[] parameters = method.getParameters();
                        for (int i = 0; i < parameters.length; i++) {
                            Parameter parameter = parameters[i];
                            // 拿到的是@Param注解中的值
                            String name = parameter.getAnnotation(Param.class).value();
                            // 记录 注解中的值和方法参数值的映射
                            paramValueMapping.put(name, args[i]);
                        }

                        ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();
                        GenericTokenParser tokenParser = new GenericTokenParser("#{", "}", tokenHandler);
                        // select * from user where username = #{name} and = #{age} and first_name = #{name}  #{}部分转换为?
                        String parse = tokenParser.parse(sql);
                        // 会抽取出#{}中的变量名
                        List<ParameterMapping> parameterMappings = tokenHandler.getParameterMappings();

                        PreparedStatement statement = connection.prepareStatement(parse);
                        for (int i = 0; i < parameterMappings.size(); i++) {
                            String content = parameterMappings.get(i).getContent();
                            //获取#{}中的变量名对应的值
                            Object value = paramValueMapping.get(content);
                            // 拿到值的类型，选择不同的处理器去执行
                            Class valueClass = value.getClass();
                            TypeHandler typeHandler = typeHandlerMap.get(valueClass);
                            typeHandler.setParameter(statement, i + 1, value);
                        }
                        statement.execute();
                        ResultSet resultSet = statement.getResultSet();

                        Class resultType = null;
                        Type genericReturnType = method.getGenericReturnType();
                        if (genericReturnType instanceof Class) {
                            // 不是泛型
                            resultType = (Class) genericReturnType;
                        } else if (genericReturnType instanceof ParameterizedType) {
                            // 是泛型
                            Type[] actualTypeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();
                            // 拿到泛型中的类型
                            resultType = (Class) actualTypeArguments[0];
                        }

                        // 提取出查询出的列名
                        ResultSetMetaData metaData = resultSet.getMetaData();
                        List<String> columnList = new ArrayList<>();
                        for (int i = 0; i < metaData.getColumnCount(); i++) {
                            columnList.add(metaData.getColumnName(i + 1));
                        }

                        List<User> users = new ArrayList<>();
                        while (resultSet.next()) {
                            Object instance = resultType.newInstance();

                            for (int i = 0; i <columnList.size(); i++) {
                                String column = columnList.get(i);


                            }

                            User user = new User();
                            user.setId(resultSet.getInt("id"));
                            user.setAge(resultSet.getInt("age"));
                            user.setUsername(resultSet.getString("username"));
                            user.setFirstname(resultSet.getString("first_name"));
                            users.add(user);
                        }

                        System.out.println(users);

                        return users;
                    }
                });
        return (T)o;
    }


}
