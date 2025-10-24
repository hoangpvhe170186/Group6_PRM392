package com.example.f_food.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.f_food.entity.Policy;

import java.util.List;

@Dao
public interface PolicyDAO {
    @Query("SELECT * FROM Policies")
    List<Policy> getAllPolicies();

    @Query("SELECT * FROM Policies WHERE policy_id = :id")
    Policy getPolicyById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Policy policy);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Policy> policies);

    @Update
    void update(Policy policy);

    @Delete
    void delete(Policy policy);

    @Query("DELETE FROM Policies WHERE policy_id = :id")
    void deleteById(int id);
}
