package com.example.f_food.repository;

import android.content.Context;

import com.example.f_food.dao.RestaurantRoomDatabase;
import com.example.f_food.dao.UserDAO;
import com.example.f_food.entity.User;

import java.util.Arrays;
import java.util.List;

public class UserRepository {
    private UserDAO userDAO;
    private RestaurantRoomDatabase restaurantRoomDatabase;

    public UserRepository(Context context) {
        RestaurantRoomDatabase db = RestaurantRoomDatabase.getInstance(context);
        userDAO = db.userDAO();

        if (userDAO.getAllUsers().isEmpty()) {
            insertSampleData();
        }
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public List<User> getAllCustomer() {
        return userDAO.getAllCustomers();
    }

    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    public void deleteById(int id) {
        userDAO.deleteById(id);
    }

    public void deleteAll() {
        userDAO.deleteAll();
    }

    public void insert(User user) {
        userDAO.insert(user);
    }

    public void insertAll(List<User> listUser) {
        userDAO.insertAll(listUser);
    }

    public void update(User user) {
        userDAO.update(user);
    }

    private void insertSampleData() {
        List<User> sampleUsers = Arrays.asList(
                new User(1, "hoang", "haha@gmail.com", "123", "0123456789", "Customer", "2025-10-20 10:00:00", "2025-10-20 10:00:00", false, null),
                new User(2, "quang", "kaka@gmail.com", "password456", "0987654321", "Restaurant", "2025-10-20 10:00:00", "2025-10-20 10:00:00", false, null),
                new User(3, "thang", "keke@gmail.com", "password789", "0112233445", "Shipper", "2025-10-20 10:00:00", "2025-10-20 10:00:00", false, null),
                new User(4, "thinh", "admin", "1", "0223344556", "Admin", "2025-10-20 10:00:00", "2025-10-20 10:00:00", false, null),
                new User(5, "trung", "meme@gmail.com", "1", "0223344556", "Customer", "2025-10-20 10:00:00", "2025-10-20 10:00:00", false, null)
        );

        for (User user : sampleUsers) {
            userDAO.insert(user);
        }
    }

    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    public User getUserByPhone(String phone) {
        return userDAO.getUserByPhone(phone);
    }
}