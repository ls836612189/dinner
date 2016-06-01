package com.tj.restaurant.mapper;

import com.tj.restaurant.bean.FoodVO;
import com.tj.restaurant.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/4/28.
 */
@Repository
public interface FoodMenuReader {
    List<FoodEntity> queryFoodList(@Param("foodVo")FoodVO foodVO);
    List<OrderEntity> queryOrder();
    List<FoodEntity> queryFoodListByType(@Param("foodType") Integer foodType);
    OrderDetail queryOrderDetail(@Param("orderId") Integer orderId);
    List<FoodDetail> queryFoodDetail(@Param("orderId") Integer orderId);
    List<FoodType> queryFoodType();
    FoodEntity queryFoodById(Integer id);
}
