package com.ie.repository.admin;

import com.ie.entity.Goods;
import com.ie.entity.GoodsType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GoodsRepository {
    int addGoods(Goods goods);

    int updateGoods(Goods goods);

    Goods selectAGoods(Integer id);

    int selectAllGoods();

    List<GoodsType> selectAllGoodsType();

    List<Goods> selectAllGoodsByPage(@Param("startIndex") int startIndex, @Param("perPageSize") int perPageSize);

    int deleteAGoods(Integer id);

    List<Map<String, Object>> selectFocusGoods(Integer id);

    List<Map<String, Object>> selectCartGoods(Integer id);

    List<Map<String, Object>> selectOrderGoods(Integer id);
}
