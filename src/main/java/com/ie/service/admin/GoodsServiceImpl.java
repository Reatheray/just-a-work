package com.ie.service.admin;

import com.ie.controller.admin.MyUtil;
import com.ie.entity.Goods;
import com.ie.repository.admin.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
        MultipartFile myfile = goods.getFileName();
        //如果选择了上传文件，将文件上传到指定的目录images
        if (!myfile.isEmpty()) {
            //上传文件路径（生产环境）
            String path = request.getServletContext().getRealPath("/images/");
            //上传文件路径（开发环境）
            String realPath = "E:\\Project\\JAVAEE\\Day3\\src\\main\\resources\\static\\images";
            //获得上传文件原名
            String fileName = myfile.getOriginalFilename();
            //对文件重命名
            String fileNewName = MyUtil.getNewFileName(fileName);
            File filePath = new File(path + File.separator + fileNewName);
            File realFilePath = new File(realPath + File.separator + fileNewName);
            //如果文件目录不存在，创建目录
            if (!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
            }
            //判断实例路径是否存在
            if (!realFilePath.getParentFile().exists()) {
                realFilePath.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文件中
            myfile.transferTo(filePath);
            //复制文件到实例路径中
            Files.copy(filePath.toPath(), realFilePath.toPath());
            //将重命名后的图片名存到goods对象中，添加时使用
            goods.setGpicture(fileNewName);
        }
        int n = goodsRepository.addGoods(goods);
        if (n > 0)//成功
            return "redirect:/goods/selectAllGoodsByPage?currentPage=1";
        //失败
        return "admin/addGoods";


//        goodsRepository.addGoods(goods);
//        return "forward:/goods/selectAllGoodsByPage?currentPage=1";
    }

    @Override
    public String updateAGoods(Goods goods, HttpServletRequest request) throws IOException {
        MultipartFile myfile = goods.getFileName();
        //如果选择了上传文件，将文件上传到指定的目录images
        if (!myfile.isEmpty()) {
            //上传文件路径（生产环境）
            String path = request.getServletContext().getRealPath("/images/");
            //上传文件路径（开发环境）
            String realPath = "E:\\Project\\JAVAEE\\Day3\\src\\main\\resources\\static\\images";
            //获得上传文件原名
            String fileName = myfile.getOriginalFilename();
            //对文件重命名
            String fileNewName = MyUtil.getNewFileName(fileName);
            File filePath = new File(path + File.separator + fileNewName);
            File realFilePath = new File(realPath + File.separator + fileNewName);
            //如果文件目录不存在，创建目录
            if (!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
            }
            //判断实例路径是否存在
            if (!realFilePath.getParentFile().exists()) {
                realFilePath.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文件中
            myfile.transferTo(filePath);
            //复制文件到实例路径中
            Files.copy(filePath.toPath(), realFilePath.toPath());

            //将重命名后的图片名存到goods对象中，添加时使用
            goods.setGpicture(fileNewName);
        }
        int n = goodsRepository.updateGoods(goods);
        if (n > 0)//成功
            return "redirect:/goods/selectAllGoodsByPage?currentPage=1";
        //失败
        return "admin/UpdateAGoods";


//        goodsRepository.updateGoods(goods);
//        return "forward:/goods/selectAllGoodsByPage?currentPage=1";
    }


}
