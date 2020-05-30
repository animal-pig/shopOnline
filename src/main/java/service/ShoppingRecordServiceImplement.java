package service;

import dao.ShoppingRecordDao;
import entity.ShoppingRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingRecordServiceImplement implements ShoppingRecordService {
    @Autowired
    private ShoppingRecordDao shoppingRecordDao;
    @Override
    public ShoppingRecord getShoppingRecord(int userId, int productId, String time) {
        return shoppingRecordDao.getShoppingRecord(userId,productId,time);
    }

    @Override
    public void addShoppingRecord(ShoppingRecord shoppingRecord) {
        shoppingRecordDao.addShoppingRecord(shoppingRecord);
    }

    @Override
    public boolean deleteShoppingRecord(int userId, int productId) {
        return shoppingRecordDao.deleteShoppingRecord(userId,productId);
    }

    @Override
    public boolean updateShoppingRecord(ShoppingRecord shoppingRecord) {
        return shoppingRecordDao.updateShoppingRecord(shoppingRecord);
    }

    @Override
    public List<ShoppingRecord> getShoppingRecordsByOrderStatus(int orderStatus) {
        return shoppingRecordDao.getShoppingRecordsByOrderStatus(orderStatus);
    }

    @Override
    public List<ShoppingRecord> getShoppingRecords(int userId) {
        return shoppingRecordDao.getShoppingRecords(userId);
    }

    @Override
    public List<ShoppingRecord> getAllShoppingRecords(int shangjiaId) {
        return shoppingRecordDao.getAllShoppingRecords(shangjiaId);
    }

    @Override
    public boolean getUserProductRecord(int userId,int productId) {
        return shoppingRecordDao.getUserProductRecord(userId,productId);
    }
}
