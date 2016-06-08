package com.tj.restaurant.mapper;

import com.fasterxml.jackson.databind.ser.std.StdArraySerializers;
import com.tj.restaurant.entity.FoodDetail;
import com.tj.restaurant.entity.FoodEntity;
import com.tj.restaurant.entity.OrderDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by user on 2016/5/11.
 */
public interface FoodMenuWriter {

    void saveNewFood(@Param("food") FoodEntity entity);
    int saveOrder(@Param("order")Integer order);
    int deleteFoodById(@Param("foodId") Integer id);
    int updateFood(@Param("food") FoodEntity entity);
    int saveCommitOrder(@Param("order")OrderDetail orderDetail);
    void saveCommitFoodList(@Param("detail")List<FoodDetail> list, @Param("orderId") int orderId);
    void removeOldOrder(@Param("orderId") int orderId);
}
