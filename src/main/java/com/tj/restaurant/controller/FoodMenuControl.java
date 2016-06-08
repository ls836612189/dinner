package com.tj.restaurant.controller;

import com.tj.core.utils.CoreConstants;
import com.tj.core.utils.DateUtils;
import com.tj.restaurant.annotation.AutoPage;
import com.tj.restaurant.bean.FoodVO;
import com.tj.restaurant.bean.Response;
import com.tj.restaurant.entity.FoodEntity;
import com.tj.restaurant.entity.FoodType;
import com.tj.restaurant.entity.OrderDetail;
import com.tj.restaurant.entity.OrderEntity;
import com.tj.restaurant.service.FoodMenuService;
import com.tj.sys.response.BaseResponse;
import com.tj.sys.response.EnumResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<FoodType> foodTypes = foodService.queryFoodType();
        model.addAttribute("foodType",foodTypes);
        model.addAttribute("queryBean",queryBean);
        model.addAttribute("pageData",footList);
        return "/food_list";
    }
    @RequestMapping("/food/addNewFood")
    public String addNewFood(Model model){
        List<FoodType> foodTypes = foodService.queryFoodType();
        model.addAttribute("foodType",foodTypes);
        return "food_add";
    }
    @RequestMapping("/food/editFood")
    public String modifyFood(Integer id,Model model){
        FoodEntity entity = foodService.queryFoodById(id);
        List<FoodType> foodTypes = foodService.queryFoodType();
        model.addAttribute("foodType",foodTypes);
        model.addAttribute("bean",entity);
        return "food_modify";
    }
    @RequestMapping("/food/foodDelete")
    @ResponseBody
    public Object deleteFood(Integer id){
        try{
            int count = foodService.deleteFoodById(id);
            if(count !=  1){
                return new BaseResponse(EnumResponse.OPTION_FAILURE);
            }
        }catch (Exception e){
            return new BaseResponse(EnumResponse.OPTION_FAILURE);
        }
        return new BaseResponse(EnumResponse.OPTION_SUCCESS);
    }
    @RequestMapping("/food/saveOrUpdateFood")
    @ResponseBody
    public Object saveNewFood(@RequestParam(value = "foodName",required = true) String foodName, @RequestParam(value = "foodType",required = true) String foodType,
                              @RequestParam(value = "foodPrice",required = true) String foodPrice,@RequestParam(value = "effect",required = false) String effect,
                              @RequestParam(value = "detail",required = false) String detail,@RequestParam(value = "foodImg",required = false) MultipartFile foodImg,
                              @RequestParam(value = "id",required = false)String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        FoodEntity foodEntity = new FoodEntity();
//        if(id != null && id != ""){
//            try{
//                foodEntity = foodService.queryFoodById(Integer.valueOf(id));
//            }catch (Exception e){
//                return new BaseResponse(EnumResponse.OPTION_FAILURE);
//            }
//        }else{
//            foodEntity =
//        }
        Map<String,String> map = new HashMap<String,String>();

        if(foodImg != null && !foodImg.isEmpty()){
            // 原始附件的名称
            String originalName = foodImg.getOriginalFilename();
            // 获取文件的后缀名
            String ext = originalName.substring(originalName.lastIndexOf("."));
            // 生成日期名称
            String fileName = DateUtils.dateToYmdhmsString()+ext;
           // String dictPath = request.getSession().getServletContext().getInitParameter("UPLOAD_IMAGE_PATH");
            String dictPath = "/home/images/";
//            String dictPath = CoreConstants.FOODIMGPATH;
//            dictPath = dictPath.replaceAll("//",File.separator);
            File file = new File(dictPath);
            if(!file.exists() || !file.isDirectory()){
                file.canWrite();
                file.mkdirs();
            }
            FileOutputStream fout = new FileOutputStream(dictPath+fileName);
            fout.write(foodImg.getBytes());
            fout.flush();
            fout.close();
            foodEntity.setImgUrl("/foodImg/"+fileName);

        }

        foodEntity.setFoodName(foodName);
        foodEntity.setFoodPrice(Float.parseFloat(foodPrice));
        foodEntity.setFoodType(Integer.parseInt(foodType));
        foodEntity.setEffect(effect);
        foodEntity.setDetail(detail);
        try{
            if(id != null && id != ""){
                foodEntity.setId(Integer.valueOf(id));
                foodService.updateFood(foodEntity);
            }else{
                foodService.saveFood(foodEntity);
            }
        }catch(Exception e){
            e.printStackTrace();
            return new BaseResponse(EnumResponse.OPTION_FAILURE);
        }
        return new BaseResponse(EnumResponse.OPTION_SUCCESS);
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
    @RequestMapping("/phone/order/commitOrder")
    @ResponseBody
    public Response commitOrder(OrderDetail orderDetail){
        if(orderDetail.getId() != 0){
            foodService.removeOldOrder(orderDetail.getId());
        }
        // 新订单
        int i = foodService.saveCommitOrder(orderDetail);
        if(i ==1){
            int orderId = orderDetail.getId();
            foodService.saveCommitFoodList(orderDetail.getFoodList(),orderId);
        }
        return new Response(true,"成功");
    }


}
