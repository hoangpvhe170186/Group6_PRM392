package com.example.f_food.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.f_food.entity.Payment;

import java.util.List;

@Dao
public interface PaymentDAO {

    @Query("SELECT * FROM Payments")
    List<Payment> getAllPayments();

    @Query("SELECT * FROM Payments WHERE payment_id = :id")
    Payment getPaymentById(int id);

    @Query("SELECT * FROM Payments WHERE order_id = :orderId")
    List<Payment> getPaymentsByOrderId(int orderId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Payment payment);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Payment> payments);

    @Update
    void update(Payment payment);

    @Delete
    void delete(Payment payment);

    @Query("DELETE FROM Payments WHERE payment_id = :id")
    void deleteById(int id);

    @Query("DELETE FROM Payments")
    void deleteAll();
}
