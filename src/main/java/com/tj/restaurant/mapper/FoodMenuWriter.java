package com.tj.restaurant.mapper;

import com.fasterxml.jackson.databind.ser.std.StdArraySerializers;
import com.tj.restaurant.entity.FoodEntity;
import org.apache.ibatis.annotations.Param;

/**
 * Created by user on 2016/5/11.
 */
public interface FoodMenuWriter {

    void saveNewFood(@Param("food") FoodEntity entity);
    int saveOrder(@Param("order")Integer order);
    int deleteFoodById(@Param("foodId") Integer id);
    int updateFood(@Param("food") FoodEntity entity);
}
