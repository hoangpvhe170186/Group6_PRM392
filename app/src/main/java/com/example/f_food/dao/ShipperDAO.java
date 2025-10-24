package com.example.f_food.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.f_food.entity.Shipper;

import java.util.List;

@Dao
public interface ShipperDAO {

    @Query("SELECT * FROM Shippers")
    List<Shipper> getAllShippers();

    @Query("SELECT * FROM Shippers WHERE ShipperID = :id")
    Shipper getShipperById(int id);
    @Query("SELECT * FROM Shippers WHERE UserID = :userId")
    Shipper getShipperByUserId(int userId);

    @Query("SELECT * FROM Shippers WHERE Status = :status")
    List<Shipper> getShippersByStatus(String status);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Shipper shipper);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Shipper> shippers);

    @Update
    void update(Shipper shipper);

    @Delete
    void delete(Shipper shipper);

    @Query("DELETE FROM Shippers WHERE ShipperID = :id")
    void deleteById(int id);

    @Query("DELETE FROM Shippers")
    void deleteAll();

    @Query("SELECT Users.FullName FROM Users INNER JOIN Shippers ON Shippers.UserID = Users.UserID WHERE Shippers.UserID = :userId")
    String getShipperNameByUserId(int userId);
}
