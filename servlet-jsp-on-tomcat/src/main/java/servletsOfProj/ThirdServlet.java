package servletsOfProj;

import handlers.BeansHandler;
import handlers.RedirectHadler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/third")
public class ThirdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RedirectHadler.setAll(req, 3);

        StringBuilder builder = new StringBuilder("<table>");
        builder.append("<tr>");
        builder.append("<th class=\"first\">Студент</th>");
        builder.append("<th class=\"first\">Нездані предмети</th>");
        builder.append("</tr>");

        BeansHandler.getStudentControllerBean().failedSubject()
                .forEach((key, value) -> {
            builder.append("<tr><td>");
            builder.append(key);
            builder.append("</td><td>");
            builder.append(value);
            builder.append("</td></tr>");
        });

        req.getSession().setAttribute("content", builder.toString());
        getServletContext().getRequestDispatcher("/index.jsp")
                .forward(req, resp);
    }
}
