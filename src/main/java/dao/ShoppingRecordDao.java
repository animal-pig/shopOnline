package dao;

import entity.Product;
import entity.ShoppingRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingRecordDao {

    ShoppingRecord getShoppingRecord(@Param("userId") int userId, @Param("productId") int productId, @Param("time") String time);

    void addShoppingRecord(ShoppingRecord shoppingRecord);

    boolean deleteShoppingRecord(@Param("userId") int userId, @Param("productId") int productId);

    boolean updateShoppingRecord(ShoppingRecord shoppingRecord);

    List<ShoppingRecord> getShoppingRecords(int userId);

    List<ShoppingRecord> getAllShoppingRecords(int productId);

    List<ShoppingRecord> getShoppingRecordsByOrderStatus(int orderStatus);

    boolean getUserProductRecord(@Param("userId") int userId, @Param("productId") int productId);

    List<ShoppingRecord> getShoppingRecordByProductId(int productId);

    boolean deleteShoppingRecordByUser(int userId);

    boolean deleteShoppingRecordByProductId(int productId);
}
