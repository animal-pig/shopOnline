package service;


import dao.ShoppingCarDao;
import dao.ShoppingRecordDao;
import dao.UserDao;
import dao.UserDetailDao;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reply.Response;

import java.util.List;

@Service
public class UserServiceImplement implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserDetailDao userDetailDao;
    @Autowired
    private ShoppingRecordDao shoppingRecordDao;
    @Autowired
    private ShoppingCarDao shoppingCarDao;

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByNameOrEmail(String nameOrEmail) {
        return userDao.getUserByNameOrEmail(nameOrEmail);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    @Transactional
    public Response deleteUser(int id) {
        //判断此用户是否存在购买记录、评价记录、购物车记录，如果存在，则应该先删除对应的记录，否则后续删除会出错
        try {
            shoppingCarDao.deleteShoppingCarByUser(id);
            shoppingRecordDao.deleteShoppingRecordByUser(id);
            userDetailDao.deleteUserDetail(id);
            userDao.deleteUser(id);
            return new Response(1, "删除成功", null);
        }catch (Exception e) {
            return new Response(0, "删除失败", null);
        }
    }

    @Override
    public boolean updateUser(User user)
    {
        return userDao.updateUser(user);
    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }
}
