package model;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Created by User on 01.09.2015.
 */
public class DB {
    private static Properties props = new Properties();

   static  {
        try {
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("/res/dbConfig.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Connection open() throws SQLException, ClassNotFoundException {
        Connection connection;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        connection = DriverManager.getConnection(props.getProperty("db.string"), props.getProperty("db.login"), props.getProperty("db.password"));
        return connection;
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        if (resultSet != null)
            try {
                resultSet.close();
            } catch (SQLException ignored) {
            }

        if (statement != null)
            try {
            statement.close();
        } catch (SQLException ignored) {
        }

        if (connection != null)
            try {
            connection.close();
        } catch (SQLException ignored) {
        }
    }
}
