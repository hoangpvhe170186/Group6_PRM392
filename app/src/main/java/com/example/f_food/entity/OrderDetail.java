package com.example.f_food.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "OrderDetails",
        foreignKeys = {
                @ForeignKey(entity = Order.class, parentColumns = "order_id", childColumns = "order_id", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Food.class, parentColumns = "food_id", childColumns = "food_id", onDelete = ForeignKey.CASCADE)
        },
        primaryKeys = {"order_id", "food_id"})
public class OrderDetail {

    @ColumnInfo(name = "order_id")
    private int orderId;

    @ColumnInfo(name = "food_id")
    private int foodId;

    @ColumnInfo(name = "quantity")
    private int quantity;

    @ColumnInfo(name = "price")
    private double price;

    public OrderDetail() {
    }

    public OrderDetail(int orderId, int foodId, int quantity, double price) {
        this.orderId = orderId;
        this.foodId = foodId;
        this.quantity = quantity;
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}