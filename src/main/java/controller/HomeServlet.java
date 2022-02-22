package controller;

import dao.CategoryDAO;
import dao.ProductDAO;
import model.Category;
import model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(value = "/home-page")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cateId = request.getParameter("id");

        if(cateId == null) {
            cateId=" ";
        }

        try {
            switch (cateId) {
                case "1":
                    showProductByCId(request, response);
                    break;
                case "2":
                    showProductByCId(request, response);
                    break;
                default:
                    showAllProduct(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private void showAllProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        ProductDAO productDAO = new ProductDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Product> listProduct = productDAO.getAllProduct();
        List<Category> listCategory = categoryDAO.getAllCategory();

        request.setAttribute("listProduct", listProduct);
        request.setAttribute("listCategory", listCategory);
        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        dispatcher.forward(request, response);
    }

    private void showProductByCId(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        String cateID = request.getParameter("id");

        CategoryDAO categoryDAO = new CategoryDAO();
        ProductDAO productDAO = new ProductDAO();
        List<Product> productsById = productDAO.getProductByCId(cateID);
        List<Category> listCategory = categoryDAO.getAllCategory();

        request.setAttribute("listProduct", productsById);
        request.setAttribute("listCategory", listCategory);
        request.getRequestDispatcher("home.jsp").forward(request, response);

    }

    }



