package myBatisC.mapper;

import myBatisC.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("insert into user (uid, name, password, role, status) values (#{uid}, #{name}, #{password}, #{role}, #{status})")
    int addUser(User user);

    @Delete("delete from user where uid = #{uid}")
    int deleteUserById(int uid);

    @Delete("delete from user")
    int deleteAllUser();

    @Update("update user set name = #{name}, password = #{password}, role = #{role} where uid = #{uid}")
    int updateUser(User user);

    @Select("select * from user")
    List<User> findAllUser();
}
