package com.tj.restaurant.service;

import com.tj.core.utils.InitCommon;
import com.tj.restaurant.bean.FoodVO;
import com.tj.restaurant.entity.*;
import com.tj.restaurant.mapper.FoodMenuReader;
import com.tj.restaurant.mapper.FoodMenuWriter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/4/28.
 */
@Service
public class FoodMenuService {
    private static final Logger LOGGER = Logger.getLogger(FoodMenuService.class);
    @Autowired
    public FoodMenuReader foodReader;
    @Autowired
    public FoodMenuWriter foodWriter;

    public List<FoodEntity> queryFoodList(FoodVO foodVO){
        try{
            return foodReader.queryFoodList(foodVO);
        }catch (Exception e){
            LOGGER.error("======FootMenuReader===查询异常!"+e.getMessage());
        }
        return null;
    }
    public FoodEntity queryFoodById(Integer id){
        return foodReader.queryFoodById(id);
    }
    public void saveFood(FoodEntity entity){
        try {
            foodWriter.save(entity);
        }catch (Exception e){
            LOGGER.error("=======saveFood======插入异常!"+e.getMessage());
        }

    }


    public List<OrderEntity> queryOrder(){

        return foodReader.queryOrder();

    }

    public List<FoodEntity> queryFoodListByType(Integer foodType){
        return foodReader.queryFoodListByType(foodType);
    }
    public OrderDetail queryOrderDetail(Integer orderId){
        OrderDetail detail = foodReader.queryOrderDetail(orderId);
        List<FoodDetail> list = foodReader.queryFoodDetail(orderId);
        detail.setFoodList(list);
        return detail;
    }

    public void saveOrder(Integer order){
        foodWriter.saveOrder(order);
    }

    public List<FoodType> queryFoodType(){
        return foodReader.queryFoodType();
    }
}
