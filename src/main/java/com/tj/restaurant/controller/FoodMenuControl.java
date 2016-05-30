package com.tj.restaurant.controller;

import com.tj.core.utils.CoreConstants;
import com.tj.core.utils.DateUtils;
import com.tj.restaurant.annotation.AutoPage;
import com.tj.restaurant.bean.FoodVO;
import com.tj.restaurant.bean.Response;
import com.tj.restaurant.entity.FoodEntity;
import com.tj.restaurant.entity.OrderDetail;
import com.tj.restaurant.entity.OrderEntity;
import com.tj.restaurant.service.FoodMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Administrator on 2016/4/28.
 */
@Controller
public class FoodMenuControl {
    @Autowired
    public FoodMenuService foodService;


    @RequestMapping("/food/foodList")
    @AutoPage
    public String getFootList(FoodVO queryBean, Model model){
        List<FoodEntity> footList = foodService.queryFoodList(queryBean);
        model.addAttribute("queryBean",queryBean);
        model.addAttribute("pageData",footList);
        return "/food_list";
    }

    @RequestMapping("/food/saveNewFood")
    public String saveNewFood(@RequestParam("foodName") String foodName, @RequestParam("foodType") String foodType,
                              @RequestParam("foodPrice") String foodPrice, @RequestParam MultipartFile foodImg,
                              HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(foodImg != null){
            // 原始附件的名称
            String originalName = foodImg.getOriginalFilename();
            // 获取文件的后缀名
            String ext = originalName.substring(originalName.lastIndexOf("."));
            // 生成日期名称
            String fileName = DateUtils.dateToYmdhmsString();
            String dictPath = CoreConstants.FOODIMGPATH;
            dictPath = dictPath.replaceAll("/", File.separator);
            File file = new File(dictPath);
            if(!file.exists() || !file.isDirectory()){
                file.canWrite();
                file.mkdirs();
            }
            FileOutputStream fout = new FileOutputStream(dictPath+fileName);
            fout.write(foodImg.getBytes());
            fout.flush();
            fout.close();

            FoodEntity foodEntity = new FoodEntity();
            foodEntity.setFoodName(foodName);
            foodEntity.setFoodPrice(Float.parseFloat(foodPrice));
            foodEntity.setFoodType(Integer.parseInt(foodType));
            foodEntity.setImgUrl(dictPath+fileName);

            foodService.saveFood(foodEntity);
        }
        return "/food_list";
    }
    /*@RequestMapping("/food/foodList")
    @ResponseBody
    public Object getddd(FoodVO queryBean){
        List<FoodEntity> footList = foodService.queryFoodList(queryBean);
        return footList;
    }*/

    @RequestMapping("/phone/order/orderList")
    @ResponseBody
    public Response<List<OrderEntity>> queryOrderList(){
        try {
            List<OrderEntity> list = foodService.queryOrder();
            return new Response<List<OrderEntity>>(true,list);

        }catch (Exception e){
            return new Response<List<OrderEntity>>(false,e.getMessage());
        }

    }
    @RequestMapping("/phone/food/foodListByType")
    @ResponseBody
    public Response<List<FoodEntity>> queryFoodListByType(@RequestParam("foodType") Integer foodType){
        try {
            List<FoodEntity> list = foodService.queryFoodListByType(foodType);
            return new Response<List<FoodEntity>>(true,list);

        }catch (Exception e){
            return new Response<List<FoodEntity>>(false,e.getMessage());
        }

    }

    @RequestMapping("/phone/order/orderDetail")
    @ResponseBody
    public Response<OrderDetail> queryOrderDetail(@RequestParam("orderId") Integer orderId){
        try {
            OrderDetail detail= foodService.queryOrderDetail(orderId);
            return new Response<OrderDetail>(true,detail);

        }catch (Exception e){
            return new Response<OrderDetail>(false,e.getMessage());
        }

    }


    @RequestMapping("/phone/order/saveorderDetail")
    @ResponseBody
    public Response<OrderDetail> saveOrderDetail(@RequestParam("orderId") Integer orderId){
        try {
            foodService.saveOrder(orderId);

            return new Response<OrderDetail>(true,"cddasfd");
        }catch (Exception e){
            return new Response<OrderDetail>(false,e.getMessage());
        }

    }

//    public Response<OrderDetail> saveOrderDetail(FoodVO orderId){
//
//    }


}
