package servlets;

import model.DAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("")
public class Login extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("authText", "");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("uname");
        String password = req.getParameter("pwd");
        req.setAttribute("authText", "");
        if (username.equals(""))
            req.setAttribute("authText", "Enter username");
        else if (password.equals(""))
            req.setAttribute("authText", "Enter password");
        else if (req.getParameterMap().containsKey("login")) {
            if (new DAO().authUser(username, password)) {
                HttpSession session = req.getSession(true);
                session.setAttribute("currentSessionUser", username);
                resp.sendRedirect("main.jsp");
                return;
            } else {
                req.setAttribute("authText", "Wrong username or password");
            }
        } else if (req.getParameterMap().containsKey("register")) {
            boolean hasRegistered=new DAO().createUser(username,password);
            if (hasRegistered)
                req.setAttribute("authText", "Successfully registered");
            else
                req.setAttribute("authText", "Username is already in use");
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
        dispatcher.forward(req, resp);
    }
}
