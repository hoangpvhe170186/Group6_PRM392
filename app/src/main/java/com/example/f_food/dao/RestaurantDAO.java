package com.example.f_food.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.f_food.entity.Restaurant;

import java.util.List;

@Dao
public interface RestaurantDAO {

    @Query("SELECT * FROM Restaurants")
    List<Restaurant> getAllRestaurants();

    @Query("SELECT * FROM Restaurants WHERE restaurant_id = :id")
    Restaurant getRestaurantById(int id);

    @Query("SELECT * FROM Restaurants WHERE user_id = :id")
    Restaurant getRestaurantByUserId(int id);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Restaurant restaurant);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Restaurant> restaurants);



    @Update
    void update(Restaurant restaurant);

    @Delete
    void delete(Restaurant restaurant);

    @Query("DELETE FROM Restaurants WHERE restaurant_id = :id")
    void deleteById(int id);

    @Query("DELETE FROM Restaurants")
    void deleteAll();
}
