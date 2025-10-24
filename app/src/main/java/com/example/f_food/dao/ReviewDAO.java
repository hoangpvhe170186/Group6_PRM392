package com.example.f_food.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.f_food.entity.Review;

import java.util.List;

@Dao
public interface ReviewDAO {

    // Lấy tất cả các đánh giá
    @Query("SELECT * FROM review")
    List<Review> getAllReviews();

    // Lấy một đánh giá theo review_id
    @Query("SELECT * FROM review WHERE review_id = :id")
    Review getReviewById(int id);

    // Lấy các đánh giá theo restaurant_id
    @Query("SELECT * FROM review WHERE restaurant_id = :restaurantId")
    List<Review> getReviewsByRestaurantId(int restaurantId);

    // Lấy các đánh giá theo user_id
    @Query("SELECT * FROM review WHERE user_id = :userId")
    List<Review> getReviewsByUserId(int userId);

    // Thêm một đánh giá
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Review review);

    // Thêm nhiều đánh giá
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Review> reviews);

    // Cập nhật một đánh giá
    @Update
    void update(Review review);

    // Xóa một đánh giá
    @Delete
    void delete(Review review);

    // Xóa đánh giá theo review_id
    @Query("DELETE FROM review WHERE review_id = :id")
    void deleteById(int id);

    // Xóa tất cả các đánh giá
    @Query("DELETE FROM review")
    void deleteAll();

    @Query("SELECT * FROM review WHERE restaurant_id = :restaurantId AND food_name = :foodName")
    Review getReviewByRestaurantAndFoodName(int restaurantId, String foodName);
}
