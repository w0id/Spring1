package ru.geekbrains.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ProductServlet", value = "/product")
public class ProductServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(FirstServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("User agent: {}", req.getHeader("User-agent"));
        resp.setHeader("Content-Type", "text/html; charset=utf-8");
        resp.getWriter().printf("<h1>Список товаров</h1>");
        resp.getWriter().printf("<table cellspacing=\"0\" border=\"1\" cellpadding=\"5\" width=\"300\"><th>ID</th><th>Наименование</th><th>Цена</th>");
        for (int i = 0; i < 9; i++) {
            Product currentProduct = new Product("Товар");
            resp.getWriter().printf("<tr>");
            resp.getWriter().printf("<td>%s</td><td>%s</td><td>%s р.</td>", currentProduct.getId(), currentProduct.getTitle(), currentProduct.getCost());
            resp.getWriter().printf("</tr>");
        }
        resp.getWriter().printf("</table>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
