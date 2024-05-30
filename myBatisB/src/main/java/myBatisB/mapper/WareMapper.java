package myBatisB.mapper;

import myBatisB.entity.Ware;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WareMapper {
    int addWare(Ware ware);

    int deleteWareById(int id);

    int updateWareById(Ware ware);

    List<Ware> findWareByCategory(String category);

    List<Ware> findAllWare();
}
