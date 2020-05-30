//
//import dao.*;
//
//import entity.*;
//import org.apache.ibatis.io.Resources;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import service.UserServiceImplement;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.List;
//
//public class TestProduct {
//    private InputStream in;
//    private SqlSessionFactory factory ;
//    private SqlSession session;
//
//    private ProductDao productDao;
//    private UserDao userDao;
//    private ShoppingCarDao shoppingCarDao;
//    private UserDetailDao userDetailDao;
//    private ShoppingRecordDao shoppingRecordDao;
//
//    @Autowired
//    private UserServiceImplement userServiceImplement;
//
//    @Before
//    public void init() throws IOException {
//        in= Resources.getResourceAsStream("SqlMapConfig.xml");
//        factory = new SqlSessionFactoryBuilder().build(in);
//        session=factory.openSession();
//        productDao=session.getMapper(ProductDao.class);
//        userDao = session.getMapper(UserDao.class);
//        shoppingCarDao = session.getMapper(ShoppingCarDao.class);
//        userDetailDao = session.getMapper(UserDetailDao.class);
//        shoppingRecordDao = session.getMapper(ShoppingRecordDao.class);
//    }
//
//    @After
//    public void destory() throws IOException {
//        session.commit();
//        session.close();
//        in.close();
//    }
//
////    product测试成功
//    @Test
//    public void testproducts() {
//        int id=4;
//        Product products = new Product();
//        products.setId(4);
//        products.setCounts(13);
//        products.setDescription("撒旦解放了");
//        products.setKeyWord("哦i荣光");
//        products.setName("垃圾二");
//        products.setPrice(45);
//        products.setType(5);
////         插入数据
//        productDao.addProduct(products);
////          根据名字查询数据
//        Product product = productDao.getProductByName("肯德基");
//        System.out.println(product);
////        据id查询
//        Product productId = productDao.getProductById(3);
//        System.out.println(productId.toString());
////            据id删除数据
//        productDao.deleteProduct(5);
////        跟新数据
//        productDao.updateProduct(products);
////        模糊查询数据
//        List<Product> productList = productDao.getProductsByKeyWord("频");
//        System.out.println("----------------------------------");
////        据类型查询
//        List<Product> productList2 = productDao.getProductsByType(2);
//        System.out.println("-----------------------------------");
////        查询所有数据
//        List<Product> productList3 = productDao.getAllProduct();
//        for(Product productss : productList3){
//            System.out.println(productss);
//        }
//    }
//
//    @Test
//    public void testShoppingCar(){
//        ShoppingCar shoppingCar = new ShoppingCar();
//        shoppingCar.setCounts(102);
//        shoppingCar.setProductId(1);
//        shoppingCar.setProductPrice(1223);
//        shoppingCar.setUserId(1);
////        System.out.println(shoppingCarDao.getShoppingCar(1,1)); //查找数据
////        shoppingCarDao.addShoppingCar(shoppingCar);  //插入数据
////        shoppingCarDao.deleteShoppingCar(2,3);  //删除数据
//        shoppingCarDao.updateShoppingCar(shoppingCar); //更新数据
//        List<ShoppingCar> shoppingCarList = shoppingCarDao.getShoppingCars(1);
//        for(ShoppingCar shoppingCar1 :shoppingCarList){
//            System.out.println(shoppingCar1);
//        }
//    }
//
//
////    User完成测试
//    @Test
//    public  void testUserMain(){
//        User user = new User();
//        user.setEmail("users13.email");
//        user.setName("客户s13");
//        user.setNickName("美美s13");
//        user.setRole(0);
//        user.setId(1);
////        userDao.addUser(user);   //添加 客户
////        User user1 = userDao.getUserByNameOrEmail("客户13");  //NameOrEmail
////        User user2 = userDao.getUserById(1); //Id查询
////        System.out.println(user2);
////        List<User> users = userDao.getAllUser();  //查询所有
////        for(User user3:users)
////        {
////            System.out.println(user3);
////        }
////        userDao.updateUser(user); //更新数据
////        userDao.deleteUser(11); //删除数据
//    }
//
////    Userdetail测试成功
//    @Test
//    public void testUserdetail(){
//        UserDetail userDetail = new UserDetail();
//        userDetail.setAddress("33");
//        userDetail.setBirthday("33");
//        userDetail.setId(3);
//        userDetail.setPassword("33");
//        userDetail.setPhoneNumber("33");
//        userDetail.setRegisterTime("33");
//        userDetail.setSex(0);
//        userDetail.setPostNumber("33");
//
////        userDetailDao.addUserDetail(userDetail); //添加客户
//
////        userDetailDao.deleteUserDetail(2); //据id删除
//
////        userDetail = userDetailDao.getUserDetail(8); //据id查询
////        System.out.println(userDetail);
////        userDetailDao.updateUserDetail(userDetail); //更新
//        List<UserDetail> userDetails = userDetailDao.getAllUserDetail();  //查询所有
//        for(UserDetail userDetail1:userDetails){
//            System.out.println(userDetail1);
//        }
//    }
//
////测试成功
//    @Test
//    public void testRecord(){
//        ShoppingRecord shoppingRecord = new ShoppingRecord();
//        shoppingRecord.setCounts(120);
//        shoppingRecord.setOrderStatus(1);
//        shoppingRecord.setProductId(1);
//        shoppingRecord.setProductPrice(11);
//        shoppingRecord.setTime("111111");
//        shoppingRecord.setUserId(3);
//        List<ShoppingRecord> shoppingRecordList ;
//
////        添加订单记录
////        shoppingRecordDao.addShoppingRecord(shoppingRecord);
////        查询具体的订单记录
////        shoppingRecord=shoppingRecordDao.getShoppingRecord(3,1,"111111");
////        System.out.println(shoppingRecord);
//       // 删除某个客户关于某个商品的订单信息
//        //shoppingRecordDao.deleteShoppingRecord();
////        更新信息
////        shoppingRecordDao.updateShoppingRecord(shoppingRecord);
////        获取某个用户的所有订单信息
////        shoppingRecordList = shoppingRecordDao.getShoppingRecords(3);
////        获取所有订单信息
////        shoppingRecordList=shoppingRecordDao.getAllShoppingRecords();
////        根据处理情况获取信息
////        shoppingRecordList = shoppingRecordDao.getShoppingRecordsByOrderStatus(1);
//
//    }
//}