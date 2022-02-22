package dao;

import context.DBContext;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO {

    private static final String INSERT_USERS_SQL = "INSERT INTO products (name, image, price, title, description) VALUES (?, ?, ?, ?, ?, );";
    private static final String SELECT_FROM_PRODUCTS = "SELECT*FROM products";
    private static final String SELECT_PRODUCTS_BY_CID = "SELECT*FROM products WHERE cId=?";
    private static final String SELECT_PRODUCTS_BY_ID = "SELECT*FROM products WHERE id=?";


    private static final String SELECT_ALL_CLOTHES = "SELECT*FROM products WHERE cId = 1";
    private static final String SELECT_ALL_SHOES = "SELECT*FROM products WHERE cId = 2";

//    @Override
//    public void insertProduct(Product product) {
//        DBContext dbContext = new DBContext();
//        System.out.println(INSERT_USERS_SQL);
//
//        try (Connection connection = dbContext.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
//            preparedStatement.setString(1, product.getName());
//            preparedStatement.setString(2, product.getImage());
//            preparedStatement.setFloat(3, product.getPrice());
//            preparedStatement.setString(4, product.getTitle());
//            preparedStatement.setString(5, product.getDescription());
//
//            System.out.println(preparedStatement);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            printSQLException(e);
//        }
//    }


    @Override
    public List<Product> getAllProduct() {
        List<Product> products = new ArrayList<>();
        DBContext dbContext = new DBContext();
        try (Connection connection = dbContext.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_PRODUCTS);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String image = rs.getString("image");
                float price = rs.getFloat("price");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int cId = rs.getInt("cId");
                Product product = new Product(id, name, image, price, title, description, cId);
                products.add(product);

            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
    }

    @Override
    public List<Product> getProductByCId(String id) {
        List<Product> products = new ArrayList<>();
        DBContext dbContext = new DBContext();
        try (Connection connection = dbContext.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCTS_BY_CID);) {
            System.out.println(preparedStatement);
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String image = rs.getString("image");
                float price = rs.getFloat("price");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int cId = rs.getInt("cId");
                Product product = new Product(name, image, price, title, description, cId);
                products.add(product);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
    }

    @Override
    public Product searchProductById(String id) {     // hàm tìm kiếm sản phẩm theo id.
        Product product = null;
        DBContext dbContext = new DBContext();
        try (Connection connection = dbContext.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCTS_BY_ID);) {
            System.out.println(preparedStatement);
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String image = rs.getString("image");
                float price = rs.getFloat("price");
                String title = rs.getString("title");
                String description = rs.getString("description");
                int cId = rs.getInt("cId");
                product = new Product(name, image, price, title, description, cId);

            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return product;
    }

    private void printSQLException(java.sql.SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof java.sql.SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((java.sql.SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((java.sql.SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
