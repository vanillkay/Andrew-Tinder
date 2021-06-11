package Tinder.Servlets;

import Tinder.Templates.TemplateEngine;
import Tinder.Users.User;
import Tinder.Users.UserService;
import Tinder.Utils.CookieHelper;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;


public class UsersServlet extends HttpServlet {
    private final UserService userService;
    private final TemplateEngine te = TemplateEngine.get();
    private final Map<String, Object> data = new HashMap<>();

    public UsersServlet(UserService userService) {
        this.userService = userService;
        data.put("title", "Like page");
        data.put("userTab", true);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Optional<Cookie> cookie = CookieHelper.getCookie(req, "UUID");
            Optional<User> user = userService.getUserForLike(cookie.get().getValue());
            user.ifPresentOrElse(x -> {
                data.put("user_name", user.get().NAME);
                data.put("user_avatar", user.get().AVATAR_URI);
                data.put("user_id", user.get().ID);
            }, () -> {

            });
            te.render("users.ftl", data, resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Optional<Cookie> cookie = CookieHelper.getCookie(req, "UUID");
        if (cookie.isPresent()) {
            String id = req.getParameter("user_id");
            String likeStr = req.getParameter("like");
            data.remove("user_name");
            data.remove("user_avatar");
            data.remove("user_id");
            try {
                userService.setLike(cookie.get().getValue(), id, Boolean.parseBoolean(likeStr));
                resp.sendRedirect("/users");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
