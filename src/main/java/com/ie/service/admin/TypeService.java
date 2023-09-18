package com.ie.service.admin;

import com.ie.entity.GoodsType;
import org.springframework.ui.Model;

public interface TypeService {
    public String selectAllTypeByPage(Model model, int currentPage);

    public String delete(int id);

    public String addType(GoodsType goodsType);
}
