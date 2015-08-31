package servlets;



import model.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by User on 28.08.2015.
 */
@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    Model model = Model.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameterMap().entrySet().iterator().hasNext()) {
            Map.Entry entry = req.getParameterMap().entrySet().iterator().next();
            String[] entryValue = (String[]) entry.getValue();
            if (entryValue[0].equals("Edit")) {

            } else if (entryValue[0].equals("Delete")) {
                model.deleteLine(Integer.valueOf((String) entry.getKey()));
            }
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/main");
        dispatcher.forward(req, resp);

    }

}
