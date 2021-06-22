import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import repository.DemoApplication;

import java.io.File;

public class Main {

    public static void main(String[] args) throws LifecycleException {
        DemoApplication.main(args);

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8081);
        Context ctx = tomcat.addWebapp("", new File("src/main/webapp/").getAbsolutePath());

        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
                new File("target/classes").getAbsolutePath(),"/"));

        ctx.setResources(resources);

        tomcat.enableNaming();
        tomcat.getConnector();

        tomcat.start();
        tomcat.getServer().await();

        System.out.println("Server stared!");
    }
}
