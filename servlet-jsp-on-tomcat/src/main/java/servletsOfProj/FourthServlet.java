package servletsOfProj;

import handlers.BeansHandler;
import handlers.RedirectHadler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/fourth")
public class FourthServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse
            resp) throws ServletException, IOException {
        RedirectHadler.setAll(req, 4);

        StringBuilder builder = new StringBuilder("<form action=\"#\"" +
                " method=\"post\">");
        builder.append("<p class=\"before_inp\">Введіть предмет оцінки" +
                " з якого ви хочете знайти:</p>");
        builder.append(" <input class=\"input_t\" type=\"text\" " +
                "name=\"subject\" required>");
        builder.append(" <input class=\"input_b\" type=\"submit\" " +
                "value=\"Знайти\">");
        builder.append("</form>");

        if (req.getParameter("subject") != null) {
            String subject = req.getParameter("subject");
            double average = BeansHandler.getStudentControllerBean()
                    .findAverageBySubject(subject);
            builder.append("<br>").append(subject).append(" : ")
                    .append(average);
        }

        req.getSession().setAttribute("content", builder.toString());
        getServletContext().getRequestDispatcher("/index.jsp")
                .forward(req, resp);
    }
}
