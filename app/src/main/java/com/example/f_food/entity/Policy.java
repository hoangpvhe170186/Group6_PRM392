package com.example.f_food.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity(tableName = "Policies")
public class Policy {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "policy_id")
    private int policyId;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "created_at")
    private String createdAt; // Sử dụng String để lưu trữ ngày giờ

    // Constructor không có ID (dùng để tạo mới Policy)
    public Policy(String title, String description, String createdAt) {
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
    }

    // Constructor có ID (dùng để cập nhật hoặc truy vấn Policy)
    public Policy(int policyId, String title, String description, String createdAt) {
        this.policyId = policyId;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
    }

    // Constructor mặc định (Room yêu cầu)
    public Policy() {
    }

    // Getter và Setter
    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    // Phương thức tiện ích để lấy thời gian hiện tại dưới dạng String
    public static String getCurrentTimeAsString() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
}