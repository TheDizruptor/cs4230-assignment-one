package edu.dizruptor.dao;

import java.sql.SQLException;
import javax.sql.DataSource;
import com.mysql.cj.jdbc.MysqlDataSource;

public class DatabaseConnection {
    private static final String MYSQL_URL_KEY = "jdbc:mysql://localhost:3306/cs4230contacts";
    private static final String MYSQL_USER_KEY = "root";
    private static final String MYSQL_PASSWORD_KEY = "password";
    private static final String MYSQL_DATABASE_KEY = "cs4230contacts";

    private static DataSource dataSource;

    private DatabaseConnection() {
    }

    public static DataSource getDataSource() {
        if(null == dataSource) {
            MysqlDataSource source = new MysqlDataSource();
//            source.setDatabaseName(System.getenv(MYSQL_DATABASE_KEY));
            source.setDatabaseName("cs4230contacts");
//            source.setUrl(getMySQLUrl());
            source.setUrl("jdbc:mysql://localhost:3306/cs4230contacts");
//            source.setUser(System.getenv(MYSQL_USER_KEY));
            source.setUser("root");
//            source.setPassword(System.getenv(MYSQL_PASSWORD_KEY));
            source.setPassword("password");
            dataSource = source;
            try {
                dataSource.getConnection().isValid(5);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error connecting to database");
            }
        }
        return dataSource;
    }

    private static String getMySQLUrl() {
        String url = System.getenv(MYSQL_URL_KEY);
        String schema = System.getenv(MYSQL_DATABASE_KEY);
        if(url.startsWith("jdbc:mysql")){
            return url;
        } else {
            return String.format("%s%s/%s", "jdbc:mysql://", url, schema);
        }
    }

}
