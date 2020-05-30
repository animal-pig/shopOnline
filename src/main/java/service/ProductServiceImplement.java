package service;

import dao.ProductDao;
import dao.ShoppingCarDao;
import dao.ShoppingRecordDao;
import entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reply.Response;

import java.util.List;

@Service
public class ProductServiceImplement implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ShoppingRecordDao shoppingRecordDao;
    @Autowired
    private ShoppingCarDao shoppingCarDao;

    @Override
    public Product getProductById(int id) {
        return productDao.getProductById(id);
    }

    @Override
    public Product getProductByName(String name) {
        return productDao.getProductByName(name);
    }

    @Override
    public void addProduct(Product product) {
        productDao.addProduct(product);
    }

    @Override
    @Transactional
    public Response deleteProduct(int id) {
        try {
            shoppingCarDao.deleteShoppingCarByProduct(id);
            shoppingRecordDao.deleteShoppingRecordByProductId(id);
            productDao.deleteProduct(id);
            return new Response(1, "删除商品成功", null);
        }catch (Exception e){
            return new Response(0,"删除商品失败",null);
        }
    }

    @Override
    public boolean updateProduct(Product product) {
        return productDao.updateProduct(product);
    }

    @Override
    public List<Product> getProductsByKeyWord(String searchKeyWord) {
        return productDao.getProductsByKeyWord(searchKeyWord);
    }

    @Override
    public List<Product> getProductsByType(int type) {
        return productDao.getProductsByType(type);
    }

    @Override
    public List<Product> getAllProduct() {
        return productDao.getAllProduct();
    }

    public List<Product> getProductByUserId(int userId){
        return productDao.getProductByUserId(userId);
    }

//    根据商品id查找商家id
    public int getSJid(int productId){
        return productDao.getSJid(productId);
    }
}
