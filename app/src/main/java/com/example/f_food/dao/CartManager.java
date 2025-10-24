package com.example.f_food.dao;

import com.example.f_food.entity.CartItem;
import com.example.f_food.entity.Food;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private List<CartItem> cartItems = new ArrayList<>();

    private CartManager() {
        //init(); // Gọi phương thức tạo dữ liệu mẫu
    }

    public static synchronized CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public List<CartItem> getCartItems() {
        synchronized (cartItems) {
            return new ArrayList<>(cartItems); // Return a copy for thread safety
        }
    }

    public void addToCart(Food food) {
        synchronized (cartItems) {
            for (CartItem item : cartItems) {
                if (item.getProduct().getFoodId() == food.getFoodId()) {
                    item.setQuantity(item.getQuantity() + 1);
                    return;
                }
            }
            cartItems.add(new CartItem(food, 1));
        }
    }

    public String getTotalPrice() {
        double total = 0;
        synchronized (cartItems) {
            for (CartItem item : cartItems) {
                total += item.getProduct().getPrice() * item.getQuantity();
            }
        }
        total = total - total * 0.05; // Giảm 5%
        return String.format("%.2f", total);
    }

    public void updateQuantity(Food food, int newQuantity) {
        synchronized (cartItems) {
            for (CartItem item : cartItems) {
                if (item.getProduct().getFoodId() == food.getFoodId()) {
                    if (newQuantity <= 0) {
                        cartItems.remove(item);
                    } else {
                        item.setQuantity(newQuantity);
                    }
                    return;
                }
            }
        }
    }

    public void removeFromCart(Food food) {
        synchronized (cartItems) {
            cartItems.removeIf(item -> item.getProduct().getFoodId() == food.getFoodId());
        }
    }

    public void removeCartItem() {
        synchronized (cartItems) {
            cartItems.clear();
        }
    }

    /*    // ✅ Thêm dữ liệu mẫu cho giỏ hàng
    private void init() {
        if (cartItems.isEmpty()) { // Chỉ tạo dữ liệu nếu giỏ hàng trống
            Food food1 = new Food(101, "Cheese Burger", "Delicious cheese burger", 389.00, "Burger", "default_image", "In Stock");
            Food food2 = new Food(102, "Chicken Burger", "Crispy chicken burger", 398.00, "Burger", "default_image", "In Stock");
            Food food3 = new Food(103, "Beef Burger", "Juicy beef burger", 389.00, "Burger", "default_image", "In Stock");

            cartItems.add(new CartItem(food1, 1));
            cartItems.add(new CartItem(food2, 1));
            cartItems.add(new CartItem(food3, 1));
        }
    }*/
}