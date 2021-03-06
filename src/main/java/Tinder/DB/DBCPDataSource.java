package Tinder.DB;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBCPDataSource {
    private static final BasicDataSource ds = new BasicDataSource();

    static {
        ds.setUrl(DbProp.conn);
        ds.setUsername(DbProp.user);
        ds.setPassword(DbProp.pass);
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    private DBCPDataSource(){ }
}
