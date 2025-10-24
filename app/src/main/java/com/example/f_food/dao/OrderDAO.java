package com.example.f_food.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.f_food.entity.Order;

import java.util.List;

@Dao
public interface OrderDAO {

    @Query("SELECT * FROM Orders")
    List<Order> getAllOrders();

    @Query("SELECT * FROM Orders WHERE order_id = :id")
    Order getOrderById(int id);

    @Query("SELECT COALESCE(SUM(f.price * od.quantity), 0) FROM OrderDetails od " +
            "INNER JOIN Foods f ON od.food_id = f.food_id " +
            "WHERE od.order_id = :orderId")
    double getTotalPriceByOrderId(int orderId);

    @Query("SELECT * FROM `Orders` ORDER BY order_id DESC LIMIT 1")
    Order getLastInsertedOrder();

    @Query("SELECT * FROM Orders WHERE user_id = :userId")
    List<Order> getOrdersByUserId(int userId);

    @Query("SELECT * FROM Orders WHERE shipper_id = :shipperId")
    List<Order> getOrdersByShipperId(int shipperId);

    @Query("UPDATE Orders SET order_status = :newStatus, shipper_id = :shipperId WHERE order_id = :orderId")
    void updateOrderStatus(int orderId, String newStatus, int shipperId);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Order order);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Order> orders);

    @Update
    void update(Order order);

    @Delete
    void delete(Order order);

    @Query("DELETE FROM Orders WHERE order_id = :id")
    void deleteById(int id);

    @Query("DELETE FROM Orders")
    void deleteAll();

    @Query("SELECT * FROM Orders WHERE order_status IN ('Delivered', 'Cancelled','Preparing') AND user_id = :userId")
    List<Order> getDeliveredOrCancelledOrdersByUserId(int userId);

    @Query("SELECT o.order_id, o.user_id, od.food_id, f.name AS food_name " +
            "FROM Orders o " +
            "INNER JOIN OrderDetails od ON o.order_id = od.order_id " +
            "INNER JOIN Foods f ON od.food_id = f.food_id " +
            "WHERE o.order_id = :orderId")
    List<FoodWithOrder> getFoodNamesByOrderId(int orderId);

    @Query("SELECT o.order_id, o.user_id, od.food_id, f.image_url AS image_url " +
            "FROM Orders o " +
            "INNER JOIN OrderDetails od ON o.order_id = od.order_id " +
            "INNER JOIN Foods f ON od.food_id = f.food_id " +
            "WHERE o.order_id = :orderId")
    List<FoodWithOrder> getImageByOrderId(int orderId);
    // A custom class to hold the results of the join query

    @Query("SELECT * FROM Orders WHERE order_status IN ('Preparing', 'Delivering', 'Delivered') AND user_Id = :userId " )
    List<Order> getFilteredOrdersByUserId(int userId);

    @Query("SELECT * FROM Orders WHERE order_status = 'Delivered' AND restaurant_id = :restaurantId")
    List<Order> getFilteredOrdersPaidByUserId(int restaurantId);

    @Query("SELECT * FROM Orders WHERE order_status = 'Delivered'")
    List<Order> getFilteredOrdersPaid();

    @Query("SELECT Orders.order_id AS orderId, Orders.shipper_id AS shipperId, " +
            "Users.FullName AS shipperName, Users.Phone AS shipperPhone " +
            "FROM Orders " +
            "INNER JOIN Shippers ON Orders.shipper_id = Shippers.ShipperID " +
            "INNER JOIN Users ON Shippers.UserID = Users.UserID " +
            "WHERE Orders.order_id = :orderId")

    ShipperWithOrder getShipperWithOrder(int orderId);
    @Query("SELECT * FROM Orders WHERE restaurant_id = :restaurantId")
    List<Order> getOrdersByRestaurantId(int restaurantId);
    @Query("SELECT o.order_id, o.user_id, od.food_id, f.name AS food_name, f.image_url AS image_url " +
            "FROM Orders o " +
            "INNER JOIN OrderDetails od ON o.order_id = od.order_id " +
            "INNER JOIN Foods f ON od.food_id = f.food_id " +
            "WHERE o.order_id = :orderId")
    List<FoodWithOrder> getFoodsByOrderId(int orderId);

}
