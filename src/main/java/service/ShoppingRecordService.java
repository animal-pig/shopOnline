package service;

import entity.ShoppingRecord;

import java.util.List;

public interface ShoppingRecordService {
    public ShoppingRecord getShoppingRecord(int userId, int productId, String time);

    public void addShoppingRecord(ShoppingRecord shoppingRecord);

    public boolean deleteShoppingRecord(int userId, int productId);

    public boolean updateShoppingRecord(ShoppingRecord shoppingRecord);

    public List<ShoppingRecord> getShoppingRecordsByOrderStatus(int orderStatus);

    public List<ShoppingRecord> getShoppingRecords(int userId);

    public List<ShoppingRecord> getAllShoppingRecords(int id);

    public boolean getUserProductRecord(int userId, int productId);
}
