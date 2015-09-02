package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by User on 02.09.2015.
 */
public class UserDAO {

    public boolean authUser(String username, String password) {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = DB.open();
            statement = connection.createStatement();
            rs = statement.executeQuery("select * from users where Username='" + username + "' and UserPwd='" + password + "'");
            if (rs.next()) {
                statement.close();
                return true;
            } else {
                statement.close();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(connection, statement, rs);
        }
        return false;
    }

    public boolean createUser(String username, String password) {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = DB.open();
            statement = connection.createStatement();
            rs = statement.executeQuery("select * from users where Username='" + username + "';");
            if (rs.next()) {
                statement.close();
                return false;
            } else {
                statement.executeUpdate("INSERT INTO users (Username,UserPwd) VALUES ('" + username + "', '" + password + "') ;");
                statement.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(connection, statement, rs);
        }
        return false;
    }

}
