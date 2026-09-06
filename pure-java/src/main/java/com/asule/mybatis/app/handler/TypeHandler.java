package com.asule.mybatis.app.handler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface TypeHandler<T> {
    public void setParameter(PreparedStatement statement, int i, T value) throws SQLException;
}
