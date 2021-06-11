package Tinder.Utils;

import Tinder.Users.UserService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CookieFilter implements Filter {

    private final UserService userService;
    public CookieFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    private boolean isHttp(ServletRequest rq, ServletResponse rs) {
        return rq instanceof HttpServletRequest
                && rs instanceof HttpServletResponse;
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (isHttp(servletRequest, servletResponse)) {
            HttpServletRequest rq = (HttpServletRequest) servletRequest;
            HttpServletResponse rs = (HttpServletResponse) servletResponse;
            if (rq.getRequestURI().equals("/login") || rq.getRequestURI().equals("/register")){
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
            Optional<Cookie> cookie = CookieHelper.getCookie(rq, "UUID");
            try {
                if (cookie.isPresent() && userService.getUserByID(cookie.get().getValue()).isPresent()) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    rs.sendRedirect("/login");
                }
            } catch (SQLException throwables) {
                throw new ServletException(throwables.getMessage());
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}

