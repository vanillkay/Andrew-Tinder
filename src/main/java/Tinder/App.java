package Tinder;

import Tinder.DB.DbProp;
import Tinder.DB.DbSchema;
import Tinder.Servlets.*;
import Tinder.Servlets.Static.StaticServlet;
import Tinder.Servlets.Static.StaticTypes;
import Tinder.Users.UserService;
import Tinder.Utils.CookieFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

//mvn clean compile assembly:single
public class App {
    public static void main(String[] args) throws Exception {
        int PORT = System.getenv("TYPE") != null && System.getenv("TYPE").equals("PROD") ? HerokuEnv.port() : 8080;
        Server server = new Server(PORT);
        new DbSchema(
                DbProp.conn,
                DbProp.user,
                DbProp.pass
        ).migrate();

        ServletContextHandler handler = new ServletContextHandler();
        UserService userService = new UserService();
        handler.addServlet(RedirectServlet.class, "/");
        handler.addServlet(new ServletHolder(new LoginServlet(userService)), "/login");
        handler.addServlet(LogoutServlet.class, "/logout");
        handler.addServlet(new ServletHolder(new RegisterServlet(userService)), "/register");
        handler.addServlet(new ServletHolder(new UsersServlet(userService)), "/users");
        handler.addServlet(new ServletHolder(new LikedServlet(userService)), "/liked");
        handler.addServlet(new ServletHolder(new MessagesServlet(userService)), "/messages/");
        handler.addServlet(new ServletHolder(new StaticServlet(StaticTypes.CSS)), "/css/*");

        handler.addFilter(new FilterHolder(new CookieFilter(userService)), "/users",EnumSet.of(DispatcherType.REQUEST));
        handler.addFilter(new FilterHolder(new CookieFilter(userService)), "/liked",EnumSet.of(DispatcherType.REQUEST));
        handler.addFilter(new FilterHolder(new CookieFilter(userService)), "/messages/",EnumSet.of(DispatcherType.REQUEST));




        server.setHandler(handler);
        server.start();
        server.join();
    }
}
