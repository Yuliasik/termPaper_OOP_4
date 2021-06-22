package handlers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repository.config.Config;
import repository.controller.StudentController;

public abstract class BeansHandler {
    static ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);

    public static StudentController getStudentControllerBean(){
        return applicationContext.getBean(StudentController.class);
    }

}
