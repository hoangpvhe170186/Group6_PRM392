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
                new User(1, "Tung Cao Thanh", "tungcthe176530@fpt.edu.vn", "123", "0123456789", "Customer", "2023-01-01 10:00:00", "2023-01-01 10:00:00", false, null),
                new User(2, "Huy Long Ngun", "alice@example.com", "password456", "0987654321", "Restaurant", "2023-01-02 11:00:00", "2023-01-02 11:00:00", false, null),
                new User(3, "Bach dep trai", "bob@example.com", "password789", "0112233445", "Shipper", "2023-01-03 12:00:00", "2023-01-03 12:00:00", false, null),
                new User(4, "Binh hihi", "admin", "1", "0223344556", "Admin", "2023-01-04 13:00:00", "2023-01-04 13:00:00", false, null),
                new User(5, "Minh PQ", "boby@example.com", "1", "0223344556", "Customer", "2023-01-04 13:00:00", "2023-01-04 13:00:00", false, null)
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