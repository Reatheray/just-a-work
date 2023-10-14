package com.ie.controller.admin;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.servlet.http.HttpSession;

import com.ie.entity.BUser;

public class MyUtil {
    /**
     * 将实际的文件名重命名
     */
    public static String getNewFileName(String oldFileName) {
        int lastIndex = oldFileName.lastIndexOf(".");
        String fileType = oldFileName.substring(lastIndex);
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDDHHmmssSSS");
        String time = sdf.format(now);
        String newFileName = time + fileType;
        return newFileName;
    }

    /**
     * 获得用户信息
     */
    public static BUser getUser(HttpSession session) {
        BUser bUser = (BUser) session.getAttribute("bUser");
        return bUser;
    }

    public static String getNowTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }


//    MultipartFile myfile = goods.getFileName();
//    //如果选择了上传文件，将文件上传到指定的目录images
//        if (!myfile.isEmpty()) {
//        //上传文件路径（生产环境）
//        //String path = request.getServletContext().getRealPath("/images/");
//        //获得上传文件原名
//        //上传文件路径（开发环境）
//        String path = "C:\\eBusiness\\src\\main\\resources\\static\\images";
//        //获得上传文件原名
//        String fileName = myfile.getOriginalFilename();
//        //对文件重命名
//        String fileNewName = MyUtil.getNewFileName(fileName);
//        File filePath = new File(path + File.separator + fileNewName);
//        //如果文件目录不存在，创建目录
//        if (!filePath.getParentFile().exists()) {
//            filePath.getParentFile().mkdirs();
//        }
//        //将上传文件保存到一个目标文件中
//        myfile.transferTo(filePath);
//        //将重命名后的图片名存到goods对象中，添加时使用
//        goods.setGpicture(fileNewName);
//    }
//        if ("add".equals(act)) {
//        int n = goodsRepository.addGoods(goods);
//        if (n > 0)//成功
//            return "redirect:/goods/selectAllGoodsByPage?currentPage=1";
//        //失败
//        return "admin/addGoods";
//    } else {//修改
//        int n = goodsRepository.updateGoods(goods);
//        if (n > 0)//成功
//            return "redirect:/goods/selectAllGoodsByPage?currentPage=1";
//        //失败
//        return "admin/UpdateAGoods";
//    }


}
