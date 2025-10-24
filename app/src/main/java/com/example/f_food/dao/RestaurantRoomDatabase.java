package com.example.f_food.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.f_food.entity.Address;
import com.example.f_food.entity.Category;
import com.example.f_food.entity.Food;
import com.example.f_food.entity.Order;
import com.example.f_food.entity.OrderDetail;
import com.example.f_food.entity.Payment;
import com.example.f_food.entity.Policy;
import com.example.f_food.entity.Restaurant;
import com.example.f_food.entity.Review;
import com.example.f_food.entity.Shipper;
import com.example.f_food.entity.User;

@Database(entities = {Restaurant.class, Food.class, User.class, Policy.class, Order.class, OrderDetail.class, Review.class, Category.class, Address.class, Shipper.class, Payment.class}, version = 30, exportSchema = false)

public abstract class RestaurantRoomDatabase extends RoomDatabase {

    public abstract RestaurantDAO restaurantDAO();

    public abstract FoodDAO foodDAO();
    public  abstract PolicyDAO policyDAO();

    public abstract UserDAO userDAO();

    public abstract OrderDAO orderDAO();
    public abstract PaymentDAO paymentDAO();

    public abstract CategoryDAO categoryDAO();
    public abstract OrderDetailDAO orderDetailDAO();

   public abstract ReviewDAO reviewDAO();

    public abstract AddressDAO addressDAO();
    public abstract ShipperDAO shipperDAO();
    private static volatile RestaurantRoomDatabase INSTANCE;

    public static RestaurantRoomDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (RestaurantRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    RestaurantRoomDatabase.class, "restaurant_database")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}