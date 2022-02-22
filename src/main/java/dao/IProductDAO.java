package dao;

import model.Product;

import java.util.List;

public interface IProductDAO {

//    void insertProduct(Product product);

    List<Product> getAllProduct();

    List<Product> getProductByCId(String id);

    Product searchProductById(String id);



//    List<Product> getAllClothes();
//
//    List<Product> getAllShoes();

}
