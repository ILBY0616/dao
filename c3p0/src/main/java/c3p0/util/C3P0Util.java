package c3p0.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class C3P0Util {

    private static final ComboPooledDataSource datasource = new ComboPooledDataSource("mysql");
    private static final ThreadLocal<Connection> local = new ThreadLocal<>();

    public static DataSource getDataSource() {
        return datasource;
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = local.get();
        if (connection != null)
            return connection;
        return datasource.getConnection();
    }

    public static void open() throws SQLException {
        Connection connection = local.get();
        if (connection != null) {
            throw new SQLException("事务已开启！");
        }
        connection = getConnection();
        connection.setAutoCommit(false);
        local.set(connection);
    }

    public static void commit() throws SQLException {
        Connection connection = local.get();
        if (connection == null) {
            throw new SQLException("事务未启动！");
        }
        connection.commit();
        connection.close();
        local.remove();
    }

    public static void rollback() throws SQLException {
        Connection connection = local.get();
        if (connection == null) {
            throw new SQLException("事务未启动！");
        }
        connection.rollback();
        connection.close();
        local.remove();
    }

    public static void close(Connection otherConnection) throws SQLException {
        Connection connection = local.get();
        if (connection == null || connection != otherConnection) {
            otherConnection.close();
        }
    }
}
