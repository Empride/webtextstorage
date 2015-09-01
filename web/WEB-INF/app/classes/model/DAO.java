package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by User on 28.08.2015.
 */
public class DAO {

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
        } catch (ClassNotFoundException | SQLException e) {
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
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            DB.close(connection, statement, rs);
        }
        return false;
    }


    public Map<Integer, String> getTextData(String username) {
        Map<Integer, String> modelData;
        modelData = new TreeMap<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = DB.open();
            statement = connection.createStatement();
            rs = statement.executeQuery("select * from TextData where Username='" + username + "'");
            while (rs.next()) {
                if (!rs.getBoolean("DeleteMark")) {
                    modelData.put(rs.getInt("TextID"), rs.getString("Text"));
                }
            }
            statement.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DB.close(connection, statement, rs);
        }
        return modelData;
    }

    public String getTextNode(String id) {
        String text = "";
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = DB.open();
            statement = connection.createStatement();
            rs = statement.executeQuery("select * from TextData where TextID='" + id + "'");
            if (rs.next()) {
                {
                    text = rs.getString("Text");
                }
            }
            statement.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DB.close(connection, statement, rs);
        }
        return text;
    }

    public void addTextNode(String s, String username) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DB.open();
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO TextData (Username,Text,DeleteMark) VALUES ('" + username + "', '" + s + "','" + 0 + "'); ");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DB.close(connection, statement, null);
        }
    }

    public void deleteTextNode(String textId) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DB.open();
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE TextData SET DeleteMark=1 WHERE TextID='" + textId + "';");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DB.close(connection, statement, null);
        }
    }

    public void updateTextNode(String textField, String textId) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DB.open();
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE TextData SET Text='" + textField + "' WHERE TextID='" + textId + "';");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DB.close(connection, statement, null);
        }
    }
}
