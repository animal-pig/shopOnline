package dao;

import entity.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao {
//    @Select("select * from products where id=#{id}")
    public Product getProductById(int id);

//    @Select("select * from products where name=#{name}")
    public Product getProductByName(String name);

//    @Insert("insert into products (name,description,key_Word,price,counts,type) values(#{name},#{description},#{key_word},#{price},#{counts},#{type})")
    public void addProduct(Product product);

//    @Delete("delete from products where id=#{id}")
    public boolean deleteProduct(int id);

//    @Update("update products set name=#{name},description=#{description},key_word=#{key-Word},price=#{price},counts=#{counts},type=#{type} where id = #{id}")
    public boolean updateProduct(Product product);

//    @Select("select * from products where key_word like CONCAT('%',#{searchKeyWord},'%')")
    public List<Product> getProductsByKeyWord(String searchKeyWord);

//    @Select("select * from products where type=#{type}")
    public List<Product> getProductsByType(int type);

//    @Select("select * from products ")
    public List<Product> getAllProduct();

    public List<Product> getProductByUserId(int userId);

//    根据商品id查找商家id
    public int getSJid(int productId);


}
