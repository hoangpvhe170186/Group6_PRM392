package com.example.f_food.dao;

public class AddressWithUser {
    // Thêm ID để nhận diện địa chỉ
    public String username;
    public String address;
    public String detailAddress;
    public boolean isDefault;
    public String addressType;

    // Constructor
    public AddressWithUser(String username, String address, String detailAddress, boolean isDefault, String addressType) {

        this.username = username;
        this.address = address;
        this.detailAddress = detailAddress;
        this.isDefault = isDefault;
        this.addressType = addressType;
    }
}
