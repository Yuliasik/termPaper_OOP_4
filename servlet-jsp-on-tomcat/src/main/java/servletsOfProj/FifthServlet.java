package servletsOfProj;

import handlers.BeansHandler;
import handlers.RedirectHadler;
import org.springframework.stereotype.Controller;
import repository.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/fifth")
@Controller
public class FifthServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse
            resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse
            resp) throws ServletException, IOException {
        RedirectHadler.setAll(req, 5);

        StringBuilder builder = new StringBuilder("<form action=\"#\"" +
                " method=\"post\">");
        builder.append("<p class=\"before_inp\">Введіть id студента, " +
                "середнє арифметичне якого ви хочете знайти:</p>");
        builder.append(" <input class=\"input_t\" type=\"number\" " +
                "name=\"id\" required>");
        builder.append(" <input class=\"input_b\" type=\"submit\" " +
                "value=\"Знайти\">");
        builder.append("</form>");

        if (req.getParameter("id") != null) {
            int id = Integer.parseInt(req.getParameter("id"));
            Student student = BeansHandler.getStudentControllerBean()
                    .getById(Integer.valueOf(id).longValue());

            builder.append("<br><br>");
            if (student != null) {
                double average = BeansHandler.getStudentControllerBean()
                        .findAverageOneStudent(student)
                        .values().stream().findFirst().orElse(0.0);
                builder.append(student.getFirstName())
                        .append(" ").append(student.getLastName())
                        .append(" : ").append(average);
            } else {
                builder.append("User with index ").append(id)
                        .append(" not found!");
            }
        }
        req.getSession().setAttribute("content", builder.toString());
        getServletContext().getRequestDispatcher("/index.jsp")
                .forward(req, resp);
    }
}
