package com.example.f_food.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Address")
public class Address {
    @PrimaryKey(autoGenerate = true)
    private int addressId; // ID địa chỉ, tự động tăng
    private int userId; // ID của người dùng (nếu cần)
    private String address; // Tỉnh/Thành phố, Quận/Huyện, Phường/Xã
    private String detailAddress; // Tên đường, Tòa nhà, Số nhà
    private boolean isDefault; // Đặt làm địa chỉ mặc định
    private String addressType; // Loại địa chỉ (Văn phòng, Nhà riêng, ...)
    private double latitude; // Vĩ độ
    private double longitude; // Kinh độ
    public Address(){

    }

    // Constructor
    public Address(int userId, String address, String detailAddress, boolean isDefault, String addressType, double latitude, double longitude) {
        this.userId = userId;
        this.address = address;
        this.detailAddress = detailAddress;
        this.isDefault = isDefault;
        this.addressType = addressType;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getter & Setter cho addressId
    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    // Getter & Setter cho userId
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // Getter & Setter cho address
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Getter & Setter cho detailAddress
    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    // Getter & Setter cho isDefault
    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    // Getter & Setter cho addressType
    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    // Getter & Setter cho latitude
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    // Getter & Setter cho longitude
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
