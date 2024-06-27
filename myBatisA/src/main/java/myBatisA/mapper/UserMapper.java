package myBatisA.mapper;

import myBatisA.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int addUser(User user);

    int deleteUserById(int uid);

    int deleteAllUser();

    int updateUser(User user);

    List<User> findAllUser();
}
