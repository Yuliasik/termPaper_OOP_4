package servletsOfProj;

import handlers.BeansHandler;
import handlers.RedirectHadler;
import repository.model.Student;
import repository.model.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/first")
public class FirstServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RedirectHadler.setAll(req, 1);

        List<Student> students = BeansHandler.getStudentControllerBean()
                .toSortListAllStudent();

        StringBuilder builder = new StringBuilder("<table>");
        builder.append("<tr>");
        builder.append("<th class=\"first\">Студент</th>");
        builder.append("<th class=\"first\">Предмет</th>");
        builder.append("<th class=\"first\">Оцінка</th>");
        builder.append("</tr>");

        for (Student student: students) {
            builder.append("<tr><td rowspan=\"")
                    .append(student.getSubjects().size())
                    .append("\" class=\"first\">");
            builder.append(student.getLastName())
                    .append(" ").append(student.getFirstName());
            List<Subject> subjects = student.getSubjects();
            Subject first = subjects.get(0);
            builder.append("</td><td>").append(first.getSubjectName())
                    .append("</td>");
            builder.append("<td>").append(first.getMark())
                    .append("</td></tr>");
            for (int i = 1; i < subjects.size(); i++) {
                builder.append("<tr><td>").append(subjects.get(i)
                        .getSubjectName()).append("</td>");
                builder.append("<td>").append(subjects.get(i).getMark())
                        .append("</td></tr>");
            }
        }
        builder.append("</table>");

        req.getSession().setAttribute("content", builder.toString());
        getServletContext().getRequestDispatcher("/index.jsp")
                .forward(req, resp);
    }
}