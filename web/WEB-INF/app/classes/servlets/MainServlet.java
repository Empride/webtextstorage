package servlets;


import model.TextDataDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;


@WebServlet("/main")
public class MainServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/main.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String textField = req.getParameter("textField");
        HttpSession session = req.getSession();

        Map.Entry entry = req.getParameterMap().entrySet().iterator().next();
        String[] entryValue = (String[]) entry.getValue();
        if (req.getParameterMap().containsKey("logout")) {
            session.setAttribute("currentSessionUser",null);
            resp.sendRedirect("");
            return;
        } else if (entryValue[0].equals("Edit")) {
            session.setAttribute("TextIdToEdit", entry.getKey());
            resp.sendRedirect("edit.jsp");
            return;
        } else if (entryValue[0].equals("Delete")) {
            new TextDataDAO().deleteTextNode((String) entry.getKey());
        } else if (!textField.isEmpty()) {
            if (req.getParameterMap().containsKey("submit")) {
                String username = (String) session.getAttribute("currentSessionUser");
                new TextDataDAO().addTextNode(textField, username);
            } else if (req.getParameterMap().containsKey("save")) {
                String textId = (String) session.getAttribute("TextIdToEdit");
                new TextDataDAO().updateTextNode(textField, textId);
            }
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/main.jsp");
        dispatcher.forward(req, resp);
    }


}
