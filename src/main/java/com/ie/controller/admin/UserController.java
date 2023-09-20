package com.ie.controller.admin;

import com.ie.entity.BUser;
import com.ie.service.admin.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/toRegister")
    public String toRegister(@ModelAttribute("bUser") BUser bUser) {
        return "user/register";
    }

    @RequestMapping("/register")
    public String register(@ModelAttribute("bUser") @Validated BUser bUser, BindingResult rs) {
        if (rs.hasErrors()) {
            return "user/register";
        }
        return userService.register(bUser);
    }

    @RequestMapping("/toLogin")
    public String toLogin(@ModelAttribute("bUser") BUser bUser) {
        return "user/login";
    }

    @RequestMapping("/login")
    public String login(@ModelAttribute("bUser") @Validated BUser bUser, BindingResult rs, HttpSession session, Model model) {
        if (rs.hasErrors()) {
            return "user/login";
        }
        return userService.login(bUser, session, model);
    }

}
