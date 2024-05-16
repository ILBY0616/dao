package c3p0.dao;

import c3p0.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    Boolean addUser(User user) throws SQLException;

    Boolean deleteUser(int uid) throws SQLException;

    Boolean updateUser(User user) throws SQLException;

    User findUser(int uid) throws SQLException;

    List<User> findAllUser() throws SQLException;
}
