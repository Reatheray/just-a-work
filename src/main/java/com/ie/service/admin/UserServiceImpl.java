package com.ie.service.admin;

import com.ie.controller.admin.MD5Util;
import com.ie.entity.BUser;
import com.ie.repository.before.IndexRepository;
import com.ie.repository.before.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public String register(BUser bUser) {
        bUser.setBpwd(MD5Util.MD5(bUser.getBpwd()));
        if (userRepository.register(bUser) > 0) {
            return "user/login";
        }
        return "user/register";

    }

    @Override
    public String login(@ModelAttribute("bUser") @Validated BUser bUser, HttpSession session, Model model) {
        bUser.setBpwd(MD5Util.MD5(bUser.getBpwd()));
        String rand = (String) session.getAttribute("rand");
        if (!rand.equalsIgnoreCase(bUser.getCode())) {
            model.addAttribute("errorMessage", "验证码错误");
            return "user/login";
        }
        List<BUser> list = userRepository.login(bUser);
        if (list.size() > 0) {
            session.setAttribute("bUser", list.get(0));
            return "redirect:/";
        }
        model.addAttribute("errorMessage", "用户名或密码错误");
        return "user/login";
    }


}
