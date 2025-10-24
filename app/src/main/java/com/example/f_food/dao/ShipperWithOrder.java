package com.example.f_food.dao;

public class ShipperWithOrder {

    private int orderId;
    private int shipperId;
    private String shipperName;
    private String shipperPhone;  // Thêm trường số điện thoại

    // Constructor
    public ShipperWithOrder(int orderId, int shipperId, String shipperName, String shipperPhone) {
        this.orderId = orderId;
        this.shipperId = shipperId;
        this.shipperName = shipperName;
        this.shipperPhone = shipperPhone;  // Khởi tạo số điện thoại
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getShipperId() {
        return shipperId;
    }

    public void setShipperId(int shipperId) {
        this.shipperId = shipperId;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public String getShipperPhone() {
        return shipperPhone;
    }

    public void setShipperPhone(String shipperPhone) {
        this.shipperPhone = shipperPhone;
    }
}
