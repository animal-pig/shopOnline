package dao;


import entity.ShoppingCar;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCarDao {

//        @Select("select * form shopping_car where user_id =  userId, product_id = productId")
        ShoppingCar getShoppingCar(@Param("userId") int userId, @Param("productId") int productId);

//        @Insert("insert into shopping_car (user_id,product_id,product_price,counts) values (#{userId},#{productId},#{productPrice},#{counts}")
        void addShoppingCar(ShoppingCar shoppingCar);

//        @Delete("delete from shopping_car where user_Id = #{userId},product_id=productId")
        boolean deleteShoppingCar(@Param("userId") int userId, @Param("productId") int productId);

//        @Update("update shopping_car set product_price=#{productPrice},counts=#{counts} where user_Id = #{userId},product_id=productId")
        boolean updateShoppingCar(ShoppingCar shoppingCar);

//        @Select("select * from shopping_car where user_Id = #{userId}")
        List<ShoppingCar> getShoppingCars(int userId);

    boolean deleteShoppingCarByUser(int userId);

    boolean deleteShoppingCarByProduct(int productId);


}