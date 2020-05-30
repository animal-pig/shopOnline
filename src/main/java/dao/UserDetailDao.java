package dao;

import entity.UserDetail;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDetailDao {

//    @Select("select * from user_detail where id= #{id}")
    public UserDetail getUserDetail(int id);

//    @Insert("insert into user_detail (id,password,phone_number,sex,birthday,post_number,address,register_time) values " +
//            "(#{id},#{password},#{phone_number},#{sex},#{birthday},#{post_number},#{address},#{register_time})")
    public void addUserDetail(UserDetail userDetail);

//    @Delete("delete from user_detail where id=#{id}")
    public boolean deleteUserDetail(int id);

//    @Update("update user_detail set password={password},phone_number={phone_number},sex=#{sex},birthday=#{birthday}," +
//            "post_number={post_number},address={address},register_time=#{register} where id=#{id}")
    public boolean updateUserDetail(UserDetail userDetail);

//    @Select("select * from user_detail")
    public List<UserDetail> getAllUserDetail();
}
