package Tinder.Servlets;

import Tinder.Templates.TemplateEngine;
import Tinder.Users.User;
import Tinder.Users.UserService;
import Tinder.Utils.CookieHelper;
import Tinder.Utils.MessagesHelper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MessagesServlet extends HttpServlet {
    private final TemplateEngine te = TemplateEngine.get();
    private final UserService userService;
    private final Map<String, Object> data = new HashMap<>();

    public MessagesServlet(UserService userService) {
        this.userService = userService;
        data.put("title", "Chat page");
        data.put("messageTab", true);
        data.put("messages_id","");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");

        try {
            Optional<User> userByID = userService.getUserByID(id);

            if (userByID.isEmpty()) {
                resp.sendRedirect("/liked");
                return;
            }

            Optional<Cookie> cookie = CookieHelper.getCookie(req, "UUID");
            Optional<User> ownerUser = userService.getUserByID(cookie.get().getValue());
            data.put("name", userByID.get().NAME);
            data.put("avatar_uri", userByID.get().AVATAR_URI);
            data.put("own_avatar_uri", ownerUser.get().AVATAR_URI);
            data.put("to_user", userByID.get().ID);
            data.put("messages", MessagesHelper.getMessages(ownerUser.get().ID, userByID.get().ID));
            te.render("chat.ftl", data, resp);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        String message = req.getParameter("message");
        String to_user = req.getParameter("to_user");
        String from_user = CookieHelper.getCookie(req, "UUID").get().getValue();
        try {
            MessagesHelper.senMessage(from_user, to_user, message, LocalDateTime.now());
            resp.sendRedirect("/messages/?id=" + to_user);
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

    }
}
