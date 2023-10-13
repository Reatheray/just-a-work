package com.ie.controller.admin;

import com.ie.entity.Goods;
import com.ie.entity.Order;
import com.ie.service.admin.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @RequestMapping("/userInfo")
    public String userinfo() {
        return "user/userInfo";
    }

    @RequestMapping("/updateUpwd")
    public String updateUpwd(HttpSession session, String bpwd) {
        return cartService.updateUpwd(session, bpwd);
    }

    @RequestMapping("/selectCart")
    public String selectCart(Model model, HttpSession session, String act) {
        return cartService.selectCart(model, session, act);
    }

    @RequestMapping("/clearCart")
    public String clearCart(HttpSession session) {
        return cartService.clearCart(session);
    }

    @RequestMapping("/deleteCart")
    public String deleteCart(HttpSession session, Integer gid) {
        return cartService.deleteCart(session, gid);
    }

    @RequestMapping("/putCart")
    public String putCart(Goods goods, Model model, HttpSession session) {
        return cartService.putCart(goods, model, session);
    }

    @RequestMapping("/focus")
    @ResponseBody
    public String focus(@RequestBody Goods goods, Model model, HttpSession session) {
        return cartService.focus(model, session, goods.getId());
    }

    @RequestMapping("/myFocus")
    public String myFocus(Model model, HttpSession session) {
        return cartService.myFocus(model, session);
    }

    @RequestMapping("/myOrder")
    public String myOrder(Model model, HttpSession session) {
        return cartService.myOrder(model, session);
    }

    @RequestMapping("/orderDetail")
    public String orderDetail(Model model, HttpSession session, Integer id) {
        return cartService.orderDetail(model, session, id);
    }

    @RequestMapping("/pay")
    @ResponseBody
    public String pay(@RequestBody Order order) {
        return cartService.pay(order);
    }
}
