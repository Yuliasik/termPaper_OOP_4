package handlers;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

public abstract class RedirectHadler {
    private static final List<String> listOfRedirect = Arrays.asList("/first", "/second", "/third", "/fourth", "/fifth", "/sixth");
    private static final List<String> listOfTitles = Arrays.asList(
            "Список всіх студентів з найменуваннями предметів та відповідною підсумковою оцінкою",
            "Рейтинг всіх студентів за середнім балом",
            "Перелік предметів, які студенти не здали",
            "Середнє арифметичне підсумкових оцінок з певного предмету",
            "Середнє арифметичне підсумкових оцінок певного студента",
            "Групування предметів за кількістю балів, для певного студента");

    public static void setAll(HttpServletRequest req, int index){
        setRedirects(req);
        setTitles(req);
        setTitle(req, index);
    }

    public static void setRedirects(HttpServletRequest req) {
        req.getSession().setAttribute("redirectings", listOfRedirect);
    }

    public static void setTitles(HttpServletRequest req) {
        req.getSession().setAttribute("titles", listOfTitles);
    }

    public static void setTitle(HttpServletRequest req, int index){
        req.getSession().setAttribute("title", listOfTitles.get(index - 1));
    }
}