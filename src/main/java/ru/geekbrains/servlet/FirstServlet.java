package ru.geekbrains.servlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "FirstServlet", urlPatterns = "/first_servlet/*")
public class FirstServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(FirstServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        logger.info("User agent: {}", req.getHeader("User-agent"));
        Map<String, String[]> parameterMap = req.getParameterMap();
        if (parameterMap.isEmpty()) {
            getServletContext().getRequestDispatcher("/header.html").include(req, resp);
            resp.getWriter().printf("<h1>New GET request</h1>");
            getServletContext().getRequestDispatcher("/footer.html").include(req, resp);
        } else {
            List<String> params = new ArrayList<>();
            parameterMap.entrySet().forEach(i -> {
                 params.add(i.getKey() + " = " + Arrays.toString(i.getValue()));
            });
            if ((req.getPathInfo() == null) || !req.getPathInfo().equals("/test"))
                resp.sendRedirect(req.getContextPath() + req.getServletPath() + "/test?" + req.getQueryString());
            resp.setHeader("Content-Type", "text/html; charset=utf-8");
            Cookie cookie = new Cookie("user", "someUserName");
            resp.addCookie(cookie);
            resp.getWriter().printf("<h1>New GET request with %s parameters: %s %s</h1>", parameterMap.keySet().stream().count(), params, req.getPathInfo());
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        logger.info("New POST request");
        if ((req.getPathInfo() == null) || req.getPathInfo().equals("/users")) {
            req.setAttribute("newAttr", "attrValue");
            getServletContext().getRequestDispatcher("/error.html")
                    .forward(req, resp);
        } else
            resp.getWriter().printf("<h1>New POST request with body %s</h1>",readAllLines(req.getReader()));
    }

    public String readAllLines(BufferedReader reader) throws IOException {
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line);
            content.append(System.lineSeparator());
        }
        return content.toString();
    }
}