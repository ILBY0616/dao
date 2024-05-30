package myBatisA.mapper;

import myBatisA.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    Boolean addUser(User user);

    Boolean deleteUserById(int uid);

    Boolean deleteAllUser();

    Boolean updateUser(User user);

    User findUserById(int uid);

    List<User> findAllUser();
}
