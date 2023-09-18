package com.ie.controller.admin;

import com.ie.entity.Goods;
import com.ie.service.admin.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/selectAllGoodsByPage")
    public String selectAllGoodsByPage(Model model, int currentPage) {
        return goodsService.selectAllGoodsByPage(model, currentPage);

    }

    @RequestMapping("/detail")
    public String detail(Model model, Integer id, String act) {
        return goodsService.detail(model, id, act);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Integer id) {
        return goodsService.deleteAGoods(id);
    }

    @RequestMapping("toAddGoods")
    public String toAddGoods(@ModelAttribute("goods") Goods goods, Model model) {
        return goodsService.toAddGoods(goods, model);
    }

    @RequestMapping("/addGoods")
    public String addGoods(@ModelAttribute("goods") Goods goods, HttpServletRequest request, String act) throws IllegalStateException, IOException {
        return goodsService.addGoods(goods, request, act);

    }


    @RequestMapping("/updateAGoods")
    public String updateGoods(Goods goods) {
        return goodsService.updateAGoods(goods);
    }

}
