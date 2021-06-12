package Tinder.Servlets;

import Tinder.Templates.TemplateEngine;
import Tinder.Users.UserService;
import Tinder.Utils.CookieHelper;
import Tinder.Utils.CountDateFromNow;
import Tinder.Utils.FormatDateTimeMethodModel;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LikedServlet extends HttpServlet {
    private final TemplateEngine te = TemplateEngine.get();
    private final UserService userService;
    private final Map<String, Object> data = new HashMap<>();

    public LikedServlet(UserService userService) {
        this.userService = userService;
        data.put("title", "Liked page");
        data.put("likedTab", true);
        data.put("formatDateTime", new FormatDateTimeMethodModel());
        data.put("daysFromLastLogin", new CountDateFromNow());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        try {
            Optional<Cookie> cookie = CookieHelper.getCookie(req, "UUID");
            data.put("users", userService.getLikedUsersByID(cookie.get().getValue()));
            te.render("liked.ftl", data, resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
