package com.ie.service.admin;

import com.ie.controller.admin.MD5Util;
import com.ie.controller.admin.MyUtil;
import com.ie.entity.Goods;
import com.ie.entity.Order;
import com.ie.repository.before.CartRepository;
import com.ie.repository.before.IndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    IndexRepository indexRepository;

    @Override
    public String updateUpwd(HttpSession session, String bpwd) {
        Integer uid = MyUtil.getUser(session).getId();
        cartRepository.updateUpwd(uid, MD5Util.MD5(bpwd));
        return "forward:/user/toLogin";
    }

    @Override
    public String selectCart(Model model, HttpSession session, String act) {
        List<Map<String, Object>> list = cartRepository.selectCart(MyUtil.getUser(session).getId());
        double sum = 0;
        for (Map<String, Object> map : list) {
            sum += (Double) map.get("smallsum");
        }
        model.addAttribute("total", sum);
        model.addAttribute("cartlist", list);
        model.addAttribute("advertisementGoods", indexRepository.selectAdvertisementGoods());
        model.addAttribute("goodsType", indexRepository.selectGoodsType());
        if ("toCount".equals(act)) {
            return "user/count";
        }
        return "user/cart";
    }

    @Override
    public String clearCart(HttpSession session) {
        cartRepository.clear(MyUtil.getUser(session).getId());
        return "forward:/cart/selectCart";
    }

    @Override
    public String deleteCart(HttpSession session, Integer gid) {
        Integer uid = MyUtil.getUser(session).getId();
        cartRepository.deleteAGoods(uid, gid);
        return "forward:/cart/selectCart";
    }

    @Override
    public String putCart(Goods goods, Model model, HttpSession session) {
        Integer uid = MyUtil.getUser(session).getId();
        if (cartRepository.isPutCart(uid, goods.getId()).size() > 0) {
            cartRepository.updateCart(uid, goods.getId(), goods.getBuyNumber());
        } else {
            cartRepository.putCart(uid, goods.getId(), goods.getBuyNumber());
        }
        return "forward:/cart/selectCart";
    }

    @Override
    public String focus(Model model, HttpSession session, Integer gid) {
        Integer uid = MyUtil.getUser(session).getId();
        List<Map<String, Object>> list = cartRepository.isFocus(uid, gid);
        if (list.size() > 0) {
            return "no";
        } else {
            cartRepository.focus(uid, gid);
            return "ok";
        }

    }

    @Override
    public String myFocus(Model model, HttpSession session) {
        model.addAttribute("advertisementGoods", indexRepository.selectAdvertisementGoods());
        model.addAttribute("goodsType", indexRepository.selectGoodsType());
        model.addAttribute("myFocus", cartRepository.myFocus(MyUtil.getUser(session).getId()));
        return "user/myFocus";
    }

    @Override
    public String myOrder(Model model, HttpSession session) {
        Integer uid = MyUtil.getUser(session).getId();
        model.addAttribute("myOrder", cartRepository.myOrder(uid));
        return "user/myOrder";
    }

    @Override
    public String orderDetail(Model model, HttpSession session, Integer oid) {
        model.addAttribute("orderDetail", cartRepository.orderDetail(oid));
        return "user/orderDetail";
    }

    @Override
    public String pay(Order order) {
        cartRepository.pay(order.getId());
        return "ok";
    }

}
