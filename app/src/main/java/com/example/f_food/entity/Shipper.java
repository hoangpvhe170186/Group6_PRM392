package com.example.f_food.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Shippers")
public class Shipper {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ShipperID")
    private int shipperId;

    @ColumnInfo(name = "UserID")
    private int userId;

    @ColumnInfo(name = "Status")
    private String status;

    public Shipper() {
    }

    public Shipper(int shipperId, int userId, String status) {
        this.shipperId = shipperId;
        this.userId = userId;
        this.status = status;
    }

    public int getShipperId() {
        return shipperId;
    }

    public void setShipperId(int shipperId) {
        this.shipperId = shipperId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
