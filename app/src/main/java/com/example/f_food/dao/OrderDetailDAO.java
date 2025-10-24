package com.example.f_food.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.f_food.entity.OrderDetail;

import java.util.List;

@Dao
public interface OrderDetailDAO {

    @Query("SELECT * FROM OrderDetails")
    List<OrderDetail> getAllOrderDetails();

    @Query("SELECT * FROM OrderDetails WHERE order_id = :orderId")
    List<OrderDetail> getOrderDetailsByOrderId(int orderId);

    @Query("SELECT COALESCE(SUM(price * quantity), 0) FROM OrderDetails WHERE order_id = :orderId")
    double getTotalPriceByOrderId(int orderId);

    @Query("SELECT COUNT(*) FROM OrderDetails WHERE order_id = :orderId")
    int getItemCountByOrderId(int orderId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(OrderDetail orderDetail);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<OrderDetail> orderDetails);

    @Update
    void update(OrderDetail orderDetail);

    @Delete
    void delete(OrderDetail orderDetail);

    @Query("DELETE FROM OrderDetails WHERE order_id = :orderId")
    void deleteByOrderId(int orderId);

    @Query("DELETE FROM OrderDetails")
    void deleteAll();
}