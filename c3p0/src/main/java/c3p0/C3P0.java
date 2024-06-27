package c3p0;

import c3p0.dao.UserDao;
import c3p0.dao.impl.UserDaoImpl;
import c3p0.pojo.User;

import java.sql.SQLException;

public class C3P0 {

    public static void main(String[] args) throws SQLException {

        UserDao userDao = new UserDaoImpl();

        Boolean zhaoLiu = userDao.addUser(new User(4, "赵六", "654321", "消费者", "正常"));
        Boolean qinQi = userDao.addUser(new User(5, "钱七", "123456", "消费者", "正常"));
        if (zhaoLiu && qinQi) {
            System.out.println("添加成功");
        }

        for (int i = 1; i <= 5; i++) {
            User user = userDao.findUser(i);
            System.out.println(user.toString());
        }

        Boolean five = userDao.deleteUser(5);
        if (five) {
            System.out.println("删除成功");
        }

        for (int i = 1; i <= 4; i++) {
            User user = userDao.findUser(i);
            System.out.println(user.toString());
        }

        Boolean six = userDao.updateUser(new User(4, "赵六", "123456", "消费者", "正常"));
        if (six) {
            System.out.println("更新成功");
        }

        for (User user : userDao.findAllUser()) {
            System.out.println(user);
        }

        Boolean four = userDao.deleteUser(4);
        if (four) {
            System.out.println("删除成功");
        }

        for (int i = 1; i <= 3; i++) {
            User user = userDao.findUser(i);
            System.out.println(user.toString());
        }
    }

}
