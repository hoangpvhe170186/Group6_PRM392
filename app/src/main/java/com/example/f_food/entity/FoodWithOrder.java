package com.example.f_food.entity;

import androidx.room.ColumnInfo;

public class FoodWithOrder {
    @ColumnInfo(name = "foodName")
    public String foodName;

    @ColumnInfo(name = "image")
    public String image;

    @ColumnInfo(name = "quantity")
    public int quantity;

    @ColumnInfo(name = "foodId")
    public int foodId;
}