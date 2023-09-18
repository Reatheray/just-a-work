package com.ie.service.admin;

import com.ie.entity.Goods;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface GoodsService {
    public String selectAllGoodsByPage(Model model, int currentPage);

    public String deleteAGoods(Integer id);

    public String detail(Model model, Integer id, String act);

    public String toAddGoods(Goods goods, Model model);

    public String addGoods(Goods goods, HttpServletRequest request, String act) throws IllegalStateException, IOException;
    public String updateAGoods(Goods goods);

}
