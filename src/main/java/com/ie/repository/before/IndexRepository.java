package com.ie.repository.before;

import java.util.List;

import com.ie.entity.Goods;
import com.ie.entity.GoodsType;
import org.springframework.stereotype.Repository;

@Repository
public interface IndexRepository {
    public List<Goods> selectAdvertisementGoods();

    public List<GoodsType> selectGoodsType();

    public List<Goods> selectRecommendGoods(Integer tid);

    public List<Goods> selectLastedGoods(Integer tid);

    public Goods selectAGoods(Integer id);

    public List<Goods> search(String mykey);
}
