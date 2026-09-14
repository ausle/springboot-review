package com.asule.springmini;

import java.sql.*;

public class JdbcQueryDemo {

    // 数据库连接地址
    private static final String URL =
            "jdbc:mysql://192.168.52.200:3306/big_market_01" +
                    "?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8";

    // 数据库用户名和密码
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Jszc@2026";

    public static void main(String[] args) {
        String sql = " select user_id, topic, message_id, message" +
                "        from big_market_01.task where message_id = ?";
        try (
                // 1. 获取数据库连接
                Connection connection = DriverManager.getConnection(
                        URL, USERNAME, PASSWORD
                );

                // 2. 创建预编译 SQL
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            // 给 SQL 中的 ? 赋值
            statement.setString(1,"07902748804");

            // 3. 执行查询
            ResultSet resultSet = statement.executeQuery();

            // 4. 遍历查询结果
            while (resultSet.next()) {
                String topic = resultSet.getString("topic");
                String name = resultSet.getString("message");

                System.out.println(topic+"  "+name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
