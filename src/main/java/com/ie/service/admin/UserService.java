package com.ie.service.admin;

import com.ie.entity.BUser;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {
    public String register(BUser bUser);

    public String login(@ModelAttribute("bUser") @Validated BUser bUser, HttpSession session, Model model);

}
