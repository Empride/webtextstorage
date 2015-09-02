package model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by User on 01.09.2015.
 */
public class DB {
 /*   private static Properties props = new Properties();

   static  {
        try {
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("/res/dbConfig.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/


    public static Connection open() {
        Connection con = null;

      /* Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        con = DriverManager.getConnection(props.getProperty("db.string"), props.getProperty("db.login"), props.getProperty("db.password"));*/

        try {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource datasource = (DataSource)envContext.lookup("jdbc/AppDB");
            con = datasource.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        return con;
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
