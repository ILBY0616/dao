package myBatisC;

import myBatisC.mapper.UserMapper;
import myBatisC.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class MyBatisC {
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
        if (userMapper.deleteAllUser() > 0) {
            System.out.println("所有用户删除成功！");
        } else {
            System.out.println("删除所有用户失败！");
        }
        sqlSession.commit();
        if (userMapper.addUser(new User(1, "张三", "123456", "管理员", "正常")) > 0) {
            System.out.println("用户 张三 添加成功！");
        } else {
            System.out.println("用户 张三 添加失败！");
        }
        sqlSession.commit();
        if (userMapper.addUser(new User(2, "李四", "123456", "商家", "正常")) > 0) {
            System.out.println("用户 李四 添加成功！");
        } else {
            System.out.println("用户 李四 添加失败！");
        }
        sqlSession.commit();
        if (userMapper.addUser(new User(3, "王五", "123456", "消费者", "正常")) > 0) {
            System.out.println("用户 王五 添加成功！");
        } else {
            System.out.println("用户 王五 添加失败！");
        }
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
                    User addUser = new User(4, "赵六", "123456", "消费者", "正常");
                    if (userMapper.addUser(addUser) > 0) {
                        System.out.println("用户添加成功！");
                    } else {
                        System.out.println("用户添加失败！");
                    }
                    sqlSession.commit();
                    break;
                case 2:
                    if (userMapper.deleteUserById(3) > 0) {
                        System.out.println("用户删除成功！");
                    } else {
                        System.out.println("用户删除失败！");
                    }
                    sqlSession.commit();
                    break;
                case 3:
                    User updateUser = new User(3, "王五", "654321", "消费者", "正常");
                    if (userMapper.updateUser(updateUser) > 0) {
                        System.out.println("用户更新成功！");
                    } else {
                        System.out.println("用户更新失败！");
                    }
                    sqlSession.commit();
                    break;
                case 4:
                    List<User> userList = userMapper.findAllUser();
                    if (!userList.isEmpty()) {
                        for (User user : userList) {
                            System.out.println(user.toString());
                        }
                        System.out.println("用户查询成功！");
                    } else {
                        System.out.println("用户查询失败！");
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
