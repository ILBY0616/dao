package myBatis;

import myBatis.mapper.UserMapper;
import myBatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class MyBatis {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        initiateDate(userMapper, sqlSession);
        menu(userMapper, sqlSession);
        sqlSession.close();
    }

    public static void initiateDate(UserMapper userMapper, SqlSession sqlSession) {
        userMapper.deleteAllUser();
        sqlSession.commit();
        userMapper.addUser(new User(1, "张三", "123456", "管理员", "正常"));
        userMapper.addUser(new User(2, "李四", "123456", "商家", "正常"));
        userMapper.addUser(new User(3, "王五", "123456", "消费者", "正常"));
        sqlSession.commit();
    }

    public static void menu(UserMapper userMapper, SqlSession sqlSession) {
        boolean run = true;
        System.out.print("1.添加 ");
        System.out.print("2.删除 ");
        System.out.print("3.修改 ");
        System.out.println("4.查询");
        while (run) {
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    User addUser = new User(3, "王五", "123456", "消费者", "正常");
                    userMapper.addUser(addUser);
                    sqlSession.commit();
                    break;
                case 2:
                    userMapper.deleteUserById(3);
                    sqlSession.commit();
                    break;
                case 3:
                    User updateUser = new User(3, "王五", "654321", "消费者", "正常");
                    userMapper.updateUser(updateUser);
                    sqlSession.commit();
                    break;
                case 4:
                    List<User> userList = userMapper.findAllUser();
                    for (User user : userList) {
                        System.out.println(user.toString());
                    }
                    sqlSession.commit();
                    break;
                default:
                    run = false;
                    break;
            }
        }
    }
}

