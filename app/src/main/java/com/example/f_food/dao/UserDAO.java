package com.example.f_food.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;

import com.example.f_food.entity.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM Users")
    List<User> getAllUsers();

    @Query("SELECT * FROM Users WHERE UserID = :id")
    User getUserById(int id);
    @Query("SELECT * FROM Users WHERE UserType = 'Customer'")
    List<User> getAllCustomers();

    @Query("SELECT * FROM Users WHERE LOWER(Email) = LOWER(:email)")
    User getUserByEmail(String email);

    @Query("SELECT * FROM Users WHERE Phone = :phone")
    User getUserByPhone(String phone); // Kiểm tra trùng số điện thoại

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<User> users);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("DELETE FROM Users WHERE UserID = :id")
    void deleteById(int id);

    @Query("DELETE FROM Users")
    void deleteAll();
}

