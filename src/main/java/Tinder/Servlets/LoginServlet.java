package Tinder.Servlets;

import Tinder.Templates.TemplateEngine;
import Tinder.Users.User;
import Tinder.Users.UserService;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LoginServlet extends HttpServlet {
    private final TemplateEngine te = TemplateEngine.get();
    private final Map<String, Object> data = new HashMap<>();
    private final UserService userService;

    public LoginServlet(UserService service) {
        userService = service;
        clearData();
    }

    private void clearData() {
        data.put("title", "Login");
        data.put("isPasswordError", false);
        data.put("isEmailError", false);
        data.put("errorText", "");
        data.put("email", "");
        data.put("password", "");
    }

    private void renderForm(HttpServletResponse resp) {
        te.render("login.ftl", data, resp);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        clearData();
        renderForm(resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try {
            Optional<User> user = userService.getUserByEmail(email);
            user.ifPresentOrElse(x -> {
                data.put("isEmailError", false);
                if (BCrypt.checkpw(password, x.PASSWORD)) {
                    data.put("isPasswordError", false);
                } else {
                    data.put("isPasswordError", true);
                    data.put("errorText", "Wrong password !");
                }
            }, () -> {
                data.put("isEmailError", true);
                data.put("errorText", "This email is not exist. Please, sign up !");
            });

            if ((Boolean) data.get("isPasswordError") || (Boolean) data.get("isEmailError")) {
                data.put("email", email);
                data.put("password", password);
                renderForm(resp);
                return;
            }
            Cookie c = new Cookie("UUID", user.get().ID);
            userService.updateUserLastLogin(user.get().ID, LocalDate.now());
            c.setMaxAge(60 * 60 * 24);
            resp.addCookie(c);
            resp.sendRedirect("/users");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
