package dao;

import entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

//    @Select("select * from user_main where id = #{id}")
    public User getUserById(int id);

//    @Select("select * from user_main where email = #{nameOrEmail} or name=#{nameOrEmail}")
    public User getUserByNameOrEmail(String nameOrEmail);

//    @Insert("insert into user_main (name,email,nick_name,role) values (#{name},#{email},#{nick-name},#{role})")
    public void addUser(User user);

//    @Delete("delete from user_main where id = #{id}")
    public boolean deleteUser(int id);

//    @Update("update user_main set name = #{name},email=#{email},nick_name=#{nick_name} where id=#{id}")
    public boolean updateUser(User user);

//    @Select("select * from user_main")
    public List<User> getAllUser();
}
