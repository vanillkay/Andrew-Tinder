package Tinder.Servlets;

import Tinder.Utils.CookieHelper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CookieHelper.getCookie(req, "UUID").ifPresent(c -> {
            c.setMaxAge(0);
            resp.addCookie(c);
        });
        resp.sendRedirect("/login");
    }
}
