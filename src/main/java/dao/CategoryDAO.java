package dao;

import context.DBContext;
import model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements ICategoryDAO{

    private static final String SELECT_ALL_CATEGORY = "SELECT*FROM category";

    @Override
    public List<Category> getAllCategory() {
        List<Category> categories = new ArrayList<>();
        DBContext dbContext = new DBContext();
        try (Connection connection = dbContext.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORY);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("cId");
                String name = rs.getString("cName");
                Category category = new Category(id, name);
                categories.add(category);

            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return categories;
    }

    // hàm này có chức năng gì?
    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
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
