package com.tj.restaurant.entity;

import java.util.List;

/**
 * Created by apple on 16/5/25.
 */
public class OrderDetail {
    private int id;
    private int tableNum;
    private int personNum;
    private float account;
    private int foodCount;
    private float realAccount;
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

    public float getAccount() {
        return account;
    }

    public void setAccount(float account) {
        this.account = account;
    }

    public float getRealAccount() {
        return realAccount;
    }

    public void setRealAccount(float realAccount) {
        this.realAccount = realAccount;
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
