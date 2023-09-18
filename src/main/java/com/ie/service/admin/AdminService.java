package com.ie.service.admin;

import com.ie.entity.AUser;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface AdminService {
    public String login(AUser aUser, HttpSession session, Model model);
}
