package servletsOfProj;

import handlers.BeansHandler;
import handlers.RedirectHadler;
import repository.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sixth")
public class SixthServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse
            resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse
            resp) throws ServletException, IOException {
        RedirectHadler.setAll(req, 6);

        StringBuilder builder = new StringBuilder("<form action=\"#\"" +
                " method=\"post\">");
        builder.append("<p class=\"before_inp\">Введіть id студента," +
                " діапазон оцінок якого, ви хочет побачити :</p>");
        builder.append(" <input class=\"input_t\" type=\"number\" " +
                "name=\"id\" required>");
        builder.append(" <input class=\"input_b\" type=\"submit\" " +
                "value=\"Знайти\">");
        builder.append("</form>");

        if (req.getParameter("id") != null) {
            int id = Integer.parseInt(req.getParameter("id"));
            Student student = BeansHandler.getStudentControllerBean()
                    .getById(Integer.valueOf(id).longValue());

            if (student != null) {

                builder.append("<br><p>").append(student.getFirstName())
                        .append(" ")
                        .append(student.getLastName()).append("</p>")
                        .append("<table>")
                        .append("<th class=\"first\">Діапазон оцінки</th>")
                        .append("<th class=\"first\">Назва предмету</th>")
                        .append("</tr>");

                BeansHandler.getStudentControllerBean()
                        .graduationOfStudentMarks(student)
                        .forEach((key, value) -> {
                    builder.append("<tr><td class=\"first\">");
                    builder.append(key);
                    builder.append("</td>\n<td>");
                    builder.append(value);
                    builder.append("</td></tr>");
                });

                builder.append("</table");
            }else{
                builder.append("User with index ").append(id)
                        .append(" not found!");
            }
        }

        req.getSession().setAttribute("content", builder.toString());
        getServletContext().getRequestDispatcher("/index.jsp")
                .forward(req, resp);
    }
}
