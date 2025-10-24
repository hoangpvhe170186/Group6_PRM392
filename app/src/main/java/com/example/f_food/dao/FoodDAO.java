package com.example.f_food.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.f_food.entity.Food;

import java.util.List;

@Dao
public interface FoodDAO {

    @Query("SELECT * FROM Foods")
    List<Food> getAllFoods();

    @Query("SELECT * FROM Foods WHERE food_id = :id")
    Food getFoodById(int id);

    @Query("SELECT * FROM Foods WHERE restaurant_id = :restaurantId")
    List<Food> getFoodsByRestaurantId(int restaurantId);

    @Query("SELECT * FROM Foods WHERE category_id = :categoryId")
    List<Food> getFoodsByCategoryId(int categoryId);

    @Query("SELECT * FROM Foods WHERE name LIKE '%' || :name || '%'")
    List<Food> getFoodsByName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Food food);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Food> foods);

    @Update
    void update(Food food);

    @Delete
    void delete(Food food);
    @Query("UPDATE Foods SET name = :name, price = :price, description = :description, category_id = :categoryId, stock_status = :stockStatus WHERE food_id = :foodId")
    void updateFoodInfo(int foodId, String name, double price, String description, int categoryId, String stockStatus);
    @Query("DELETE FROM Foods WHERE food_id = :id")
    void deleteById(int id);

    @Query("DELETE FROM Foods")
    void deleteAll();
}