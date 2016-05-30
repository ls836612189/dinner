package com.tj.restaurant.mapper;

import com.tj.restaurant.entity.FoodEntity;
import org.apache.ibatis.annotations.Param;

/**
 * Created by user on 2016/5/11.
 */
public interface FoodMenuWriter {

    void save(FoodEntity entity);
    int saveOrder(@Param("order")Integer order);
}
