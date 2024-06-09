package myBatisB;

import myBatisB.entity.Ware;
import myBatisB.mapper.WareMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Scanner;

public class MyBatisB {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        WareMapper wareMapper = sqlSession.getMapper(WareMapper.class);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("# 商品系统 #");
            System.out.println("# 选择操作 #");
            System.out.println("1 增加商品");
            System.out.println("2 删除商品按编号");
            System.out.println("3 修改商品按编号");
            System.out.println("4 查询商品按类别");
            System.out.println("5 查询商品按所有");
            System.out.println("6 退出系统");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addWare(scanner, wareMapper);
                    sqlSession.commit();
                    break;
                case 2:
                    deleteWareById(scanner, wareMapper);
                    sqlSession.commit();
                    break;
                case 3:
                    updateWareById(scanner, wareMapper);
                    sqlSession.commit();
                    break;
                case 4:
                    findWareByCategory(scanner, wareMapper);
                    break;
                case 5:
                    findAllWare(wareMapper);
                    break;
                case 6:
                    sqlSession.close();
                    System.out.println("退出系统");
                    return;
                default:
                    System.out.println("无效选择");
            }
            try {
                InetAddress inetAddress = InetAddress.getLocalHost();
                System.out.println("主机地址：" + inetAddress.getHostAddress() + "主机名称：" + inetAddress.getHostName());
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void addWare(Scanner scanner, WareMapper wareMapper) {
        System.out.println("请输入添加商品信息:");
        Ware ware = inputInfo(scanner);
        int row = wareMapper.addWare(ware);
        if (row > 0) {
            System.out.println("商品增加成功");
        } else {
            System.out.println("商品增加失败");
        }

    }

    private static void deleteWareById(Scanner scanner, WareMapper wareMapper) {
        System.out.println("请输入删除商品ID:");
        int id = scanner.nextInt();
        scanner.nextLine();
        int row = wareMapper.deleteWareById(id);
        if (row > 0) {
            System.out.println("商品删除成功");
        } else {
            System.out.println("商品删除失败");
        }
    }

    private static void updateWareById(Scanner scanner, WareMapper wareMapper) {
        System.out.println("请输入修改商品信息:");
        Ware ware = inputInfo(scanner);
        int row = wareMapper.updateWareById(ware);
        if (row > 0) {
            System.out.println("商品修改成功");
        } else {
            System.out.println("商品修改失败");
        }
    }

    private static void findWareByCategory(Scanner scanner, WareMapper wareMapper) {
        System.out.println("请输入查询商品类别:");
        String category = scanner.nextLine();
        List<Ware> wareList = wareMapper.findWareByCategory(category);
        if (!wareList.isEmpty()) {
            System.out.println("商品查询成功");
            for (Ware ware : wareList) {
                System.out.println(ware);
            }
        } else {
            System.out.println("商品查询失败");
        }
    }

    private static void findAllWare(WareMapper wareMapper) {
        List<Ware> wareList = wareMapper.findAllWare();
        if (!wareList.isEmpty()) {
            System.out.println("商品查询成功");
            for (Ware ware : wareList) {
                System.out.println(ware);
            }
        } else {
            System.out.println("商品查询失败");
        }
    }

    private static Ware inputInfo(Scanner scanner) {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("名称: ");
        String name = scanner.nextLine();

        System.out.print("品牌: ");
        String brand = scanner.nextLine();

        System.out.print("类别: ");
        String category = scanner.nextLine();

        System.out.print("价格: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("库存: ");
        int stock = scanner.nextInt();
        scanner.nextLine();

        System.out.print("图片地址: ");
        String picAddress = scanner.nextLine();

        return new Ware(id, name, brand, category, price, stock, picAddress);
    }

}
