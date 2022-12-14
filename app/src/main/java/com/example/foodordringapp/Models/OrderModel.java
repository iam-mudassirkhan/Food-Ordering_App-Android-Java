package com.example.foodordringapp.Models;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OrderModel {

    public OrderModel(){

    }

    int orderImage ;
    String orderName, orderNumber, orderPrice;
    public OrderModel(int orderImage, String orderName, String orderNumber, String orderPrice) {
        this.orderImage = orderImage;
        this.orderName = orderName;
        this.orderNumber = orderNumber;
        this.orderPrice = orderPrice;
    }

    public int getOrderImage() {
        return orderImage;
    }

    public void setOrderImage(int orderImage) {
        this.orderImage = orderImage;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }
}
