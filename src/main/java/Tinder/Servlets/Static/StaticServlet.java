package Tinder.Servlets.Static;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StaticServlet extends HttpServlet {
    private final StaticTypes type;

    public StaticServlet(StaticTypes type) {
        this.type = type;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        InputStream in = getClass().getResourceAsStream(req.getRequestURI());
        try (OutputStream os = resp.getOutputStream()) {
            assert in != null;
            os.write(in.readAllBytes());
        }
    }
}
