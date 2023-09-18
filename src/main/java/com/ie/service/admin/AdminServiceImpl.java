package com.ie.service.admin;

import com.ie.entity.AUser;
import com.ie.repository.admin.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public String login(AUser aUser, HttpSession session, Model model) {
        List<AUser> list = adminRepository.login(aUser);
        if (list.size() > 0) {
            session.setAttribute("auser", aUser);
            return "forward:/goods/selectAllGoodsByPage?currentPage=1";
        } else {
            model.addAttribute("errorMessage", "用户名或密码错误");
            return "admin/login";
        }
    }

}
