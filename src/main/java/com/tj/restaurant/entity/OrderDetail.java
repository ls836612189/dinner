package com.tj.restaurant.entity;

import java.util.List;

/**
 * Created by apple on 16/5/25.
 */
public class OrderDetail {
    private int id;
    private int tableNum;
    private int personNum;
    private int account;
    private int foodCount;
    private List<FoodDetail> foodList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTableNum() {
        return tableNum;
    }

    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }

    public int getPersonNum() {
        return personNum;
    }

    public void setPersonNum(int personNum) {
        this.personNum = personNum;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public int getFoodCount() {
        return foodCount;
    }

    public void setFoodCount(int foodCount) {
        this.foodCount = foodCount;
    }

    public List<FoodDetail> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<FoodDetail> foodList) {
        this.foodList = foodList;
    }
}
