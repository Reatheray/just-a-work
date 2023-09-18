package com.ie.repository.admin;

import java.util.List;

import com.ie.entity.Goods;
import com.ie.entity.GoodsType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository {
    int selectAll();

    List<GoodsType> selectAllTypeByPage(@Param("startIndex") int startIndex, @Param("perPageSize") int perPageSize);

    int deleteType(int id);

    List<Goods> selectGoods(int goodstype_id);

    int addType(GoodsType goodsType);
}
