package service;

import entity.Product;
import reply.Response;

import java.util.List;

public interface ProductService {
    public Product getProductById(int id);

    public Product getProductByName(String name);

    public void addProduct(Product product);

    Response deleteProduct(int id);

    public boolean updateProduct(Product product);

    public List<Product> getProductsByKeyWord(String searchKeyWord);

    public List<Product> getProductsByType(int type);

    public List<Product> getAllProduct();

    public List<Product> getProductByUserId(int userId);

    public int getSJid(int productId);
}
