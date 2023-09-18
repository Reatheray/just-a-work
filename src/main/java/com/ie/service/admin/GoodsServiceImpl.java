package com.ie.service.admin;

import com.ie.entity.Goods;
import com.ie.repository.admin.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public String selectAllGoodsByPage(Model model, int currentPage) {
        int totalCount = goodsRepository.selectAllGoods();
        int pageSize = 5;
        int totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
        List<Goods> typeByPage = goodsRepository.selectAllGoodsByPage((currentPage - 1) * pageSize, pageSize);
        model.addAttribute("allGoods", typeByPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("currentPage", currentPage);
        return "admin/adminGoods";
    }

    @Override
    public String deleteAGoods(Integer id) {
        if (goodsRepository.selectCartGoods(id).size() > 0 || goodsRepository.selectFocusGoods(id).size() > 0 || goodsRepository.selectOrderGoods(id).size() > 0) {
            return "no";
        } else {
            goodsRepository.deleteAGoods(id);
            return "/goods/selectAllGoodsByPage?currentPage=1";
        }
    }

    @Override
    public String detail(Model model, Integer id, String act) {
        model.addAttribute("goods", goodsRepository.selectAGoods(id));
        if ("detail".equals(act)) {
            return "admin/detail";
        } else {
            model.addAttribute("goodsType", goodsRepository.selectAllGoodsType());
            return "admin/updateAGoods";
        }

    }

    @Override
    public String toAddGoods(Goods goods, Model model) {
        model.addAttribute("goodsType", goodsRepository.selectAllGoodsType());
        return "admin/addGoods";
    }

    @Override
    public String addGoods(Goods goods, HttpServletRequest request, String act) throws IllegalStateException, IOException {
        goodsRepository.addGoods(goods);
        return "forward:/goods/selectAllGoodsByPage?currentPage=1";
    }

    @Override
    public String updateAGoods(Goods goods) {
        goodsRepository.updateGoods(goods);
        return "forward:/goods/selectAllGoodsByPage?currentPage=1";
    }


}
