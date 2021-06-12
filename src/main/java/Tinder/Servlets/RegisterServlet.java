package Tinder.Servlets;

import Tinder.Templates.TemplateEngine;
import Tinder.Users.User;
import Tinder.Users.UserService;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class RegisterServlet extends HttpServlet {
    private final TemplateEngine te = TemplateEngine.get();
    private final Map<String, Object> data = new HashMap<>();
    private final UserService userService;


    public RegisterServlet(UserService userService) {
        this.userService = userService;
        clearData();
    }

    private void clearData() {
        data.put("title", "Registration");
        data.put("isPasswordError", false);
        data.put("isEmailError", false);
        data.put("errorText", "");
        data.put("email", "");
        data.put("password", "");
        data.put("avatar_uri", "");
        data.put("name", "");
        data.put("job", "");
    }

    private void renderForm(HttpServletResponse resp) {
        te.render("register.ftl", data, resp);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        clearData();
        renderForm(resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String avatar_uri = req.getParameter("avatar_uri");
        String name = req.getParameter("name");
        String job = req.getParameter("job");
        try {
            Optional<User> candidate = userService.getUserByEmail(email);

            candidate.ifPresentOrElse(x -> {
                data.put("isEmailError", true);
                data.put("isPasswordError", false);
                data.put("errorText", "This email is exist. Pick new or sign in !");
            }, () -> {
                data.put("isEmailError", false);
                if (password.length() < 5) {
                    data.put("isPasswordError", true);
                    data.put("errorText", "Password length must be more than 5 symbols !");
                } else {
                    data.put("isPasswordError", false);
                }
            });

            if ((Boolean) data.get("isEmailError") || (Boolean) data.get("isPasswordError")) {
                data.put("email", email);
                data.put("password", password);
                data.put("avatar_uri", avatar_uri);
                data.put("name", name);
                data.put("job", job);
                renderForm(resp);
                return;
            }
            User user = new User(
                    UUID.randomUUID().toString(),
                    name,
                    avatar_uri,
                    email,
                    BCrypt.hashpw(password, BCrypt.gensalt(12)), job, LocalDate.now());
            userService.addUser(user);
            Cookie c = new Cookie("UUID", user.ID);
            c.setMaxAge(60 * 60 * 24);
            resp.addCookie(c);
            resp.sendRedirect("/users");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
