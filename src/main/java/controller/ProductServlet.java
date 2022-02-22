package controller;

import dao.ProductDAO;
import model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/product")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = " ";
        }
        switch (action) {
            case "buy":
                showProductById(request, response);
                break;
        }
    }

    private void showProductById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pId = request.getParameter("pId");
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.searchProductById(pId);

        request.setAttribute("product", product);
        request.getRequestDispatcher("/web_page/detail.jsp").forward(request, response);
    }
}
