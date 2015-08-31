package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

@WebServlet("")
public class Login extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("uname");
        String password = req.getParameter("pwd");

        try {
            Properties props = new Properties();
            props.load(getClass().getResourceAsStream("/res/dbConfig.properties"));
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(props.getProperty("db.string"), props.getProperty("db.login"), props.getProperty("db.password"));
            ResultSet rs;
            rs = con.createStatement().executeQuery("select * from users where UserID='" + username + "' and UserPwd='" + password + "'");
            if (rs.next()) {
                HttpSession session = req.getSession(true);
                session.setAttribute("currentSessionUser", username);
                resp.sendRedirect("main.jsp");
                return;
            } else {
                req.setAttribute("authText", "Wrong username or password");
            }
        } catch (ClassNotFoundException | SQLException e) {
            req.setAttribute("authText", "Internal error");
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, resp);
    }
}
