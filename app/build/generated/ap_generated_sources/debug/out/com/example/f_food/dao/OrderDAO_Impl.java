package com.example.f_food.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.f_food.entity.Order;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class OrderDAO_Impl implements OrderDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Order> __insertionAdapterOfOrder;

  private final EntityDeletionOrUpdateAdapter<Order> __deletionAdapterOfOrder;

  private final EntityDeletionOrUpdateAdapter<Order> __updateAdapterOfOrder;

  private final SharedSQLiteStatement __preparedStmtOfUpdateOrderStatus;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public OrderDAO_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfOrder = new EntityInsertionAdapter<Order>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `Orders` (`order_id`,`user_id`,`restaurant_id`,`total_price`,`payment_method`,`order_status`,`created_at`,`updated_at`,`shipper_id`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Order entity) {
        statement.bindLong(1, entity.getOrderId());
        statement.bindLong(2, entity.getUserId());
        statement.bindLong(3, entity.getRestaurantId());
        statement.bindDouble(4, entity.getTotalPrice());
        if (entity.getPaymentMethod() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getPaymentMethod());
        }
        if (entity.getOrderStatus() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getOrderStatus());
        }
        if (entity.getCreatedAt() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getCreatedAt());
        }
        if (entity.getUpdatedAt() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getUpdatedAt());
        }
        statement.bindLong(9, entity.getShipperId());
      }
    };
    this.__deletionAdapterOfOrder = new EntityDeletionOrUpdateAdapter<Order>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `Orders` WHERE `order_id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Order entity) {
        statement.bindLong(1, entity.getOrderId());
      }
    };
    this.__updateAdapterOfOrder = new EntityDeletionOrUpdateAdapter<Order>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `Orders` SET `order_id` = ?,`user_id` = ?,`restaurant_id` = ?,`total_price` = ?,`payment_method` = ?,`order_status` = ?,`created_at` = ?,`updated_at` = ?,`shipper_id` = ? WHERE `order_id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Order entity) {
        statement.bindLong(1, entity.getOrderId());
        statement.bindLong(2, entity.getUserId());
        statement.bindLong(3, entity.getRestaurantId());
        statement.bindDouble(4, entity.getTotalPrice());
        if (entity.getPaymentMethod() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getPaymentMethod());
        }
        if (entity.getOrderStatus() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getOrderStatus());
        }
        if (entity.getCreatedAt() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getCreatedAt());
        }
        if (entity.getUpdatedAt() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getUpdatedAt());
        }
        statement.bindLong(9, entity.getShipperId());
        statement.bindLong(10, entity.getOrderId());
      }
    };
    this.__preparedStmtOfUpdateOrderStatus = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE Orders SET order_status = ?, shipper_id = ? WHERE order_id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM Orders WHERE order_id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM Orders";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Order order) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfOrder.insert(order);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(final List<Order> orders) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfOrder.insert(orders);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Order order) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfOrder.handle(order);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Order order) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfOrder.handle(order);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateOrderStatus(final int orderId, final String newStatus, final int shipperId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateOrderStatus.acquire();
    int _argIndex = 1;
    if (newStatus == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, newStatus);
    }
    _argIndex = 2;
    _stmt.bindLong(_argIndex, shipperId);
    _argIndex = 3;
    _stmt.bindLong(_argIndex, orderId);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfUpdateOrderStatus.release(_stmt);
    }
  }

  @Override
  public void deleteById(final int id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteById.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, id);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteById.release(_stmt);
    }
  }

  @Override
  public void deleteAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public List<Order> getAllOrders() {
    final String _sql = "SELECT * FROM Orders";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfOrderId = CursorUtil.getColumnIndexOrThrow(_cursor, "order_id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
      final int _cursorIndexOfRestaurantId = CursorUtil.getColumnIndexOrThrow(_cursor, "restaurant_id");
      final int _cursorIndexOfTotalPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "total_price");
      final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "payment_method");
      final int _cursorIndexOfOrderStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "order_status");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
      final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
      final int _cursorIndexOfShipperId = CursorUtil.getColumnIndexOrThrow(_cursor, "shipper_id");
      final List<Order> _result = new ArrayList<Order>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Order _item;
        _item = new Order();
        final int _tmpOrderId;
        _tmpOrderId = _cursor.getInt(_cursorIndexOfOrderId);
        _item.setOrderId(_tmpOrderId);
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        _item.setUserId(_tmpUserId);
        final int _tmpRestaurantId;
        _tmpRestaurantId = _cursor.getInt(_cursorIndexOfRestaurantId);
        _item.setRestaurantId(_tmpRestaurantId);
        final double _tmpTotalPrice;
        _tmpTotalPrice = _cursor.getDouble(_cursorIndexOfTotalPrice);
        _item.setTotalPrice(_tmpTotalPrice);
        final String _tmpPaymentMethod;
        if (_cursor.isNull(_cursorIndexOfPaymentMethod)) {
          _tmpPaymentMethod = null;
        } else {
          _tmpPaymentMethod = _cursor.getString(_cursorIndexOfPaymentMethod);
        }
        _item.setPaymentMethod(_tmpPaymentMethod);
        final String _tmpOrderStatus;
        if (_cursor.isNull(_cursorIndexOfOrderStatus)) {
          _tmpOrderStatus = null;
        } else {
          _tmpOrderStatus = _cursor.getString(_cursorIndexOfOrderStatus);
        }
        _item.setOrderStatus(_tmpOrderStatus);
        final String _tmpCreatedAt;
        if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
          _tmpCreatedAt = null;
        } else {
          _tmpCreatedAt = _cursor.getString(_cursorIndexOfCreatedAt);
        }
        _item.setCreatedAt(_tmpCreatedAt);
        final String _tmpUpdatedAt;
        if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
          _tmpUpdatedAt = null;
        } else {
          _tmpUpdatedAt = _cursor.getString(_cursorIndexOfUpdatedAt);
        }
        _item.setUpdatedAt(_tmpUpdatedAt);
        final int _tmpShipperId;
        _tmpShipperId = _cursor.getInt(_cursorIndexOfShipperId);
        _item.setShipperId(_tmpShipperId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Order getOrderById(final int id) {
    final String _sql = "SELECT * FROM Orders WHERE order_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfOrderId = CursorUtil.getColumnIndexOrThrow(_cursor, "order_id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
      final int _cursorIndexOfRestaurantId = CursorUtil.getColumnIndexOrThrow(_cursor, "restaurant_id");
      final int _cursorIndexOfTotalPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "total_price");
      final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "payment_method");
      final int _cursorIndexOfOrderStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "order_status");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
      final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
      final int _cursorIndexOfShipperId = CursorUtil.getColumnIndexOrThrow(_cursor, "shipper_id");
      final Order _result;
      if (_cursor.moveToFirst()) {
        _result = new Order();
        final int _tmpOrderId;
        _tmpOrderId = _cursor.getInt(_cursorIndexOfOrderId);
        _result.setOrderId(_tmpOrderId);
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        _result.setUserId(_tmpUserId);
        final int _tmpRestaurantId;
        _tmpRestaurantId = _cursor.getInt(_cursorIndexOfRestaurantId);
        _result.setRestaurantId(_tmpRestaurantId);
        final double _tmpTotalPrice;
        _tmpTotalPrice = _cursor.getDouble(_cursorIndexOfTotalPrice);
        _result.setTotalPrice(_tmpTotalPrice);
        final String _tmpPaymentMethod;
        if (_cursor.isNull(_cursorIndexOfPaymentMethod)) {
          _tmpPaymentMethod = null;
        } else {
          _tmpPaymentMethod = _cursor.getString(_cursorIndexOfPaymentMethod);
        }
        _result.setPaymentMethod(_tmpPaymentMethod);
        final String _tmpOrderStatus;
        if (_cursor.isNull(_cursorIndexOfOrderStatus)) {
          _tmpOrderStatus = null;
        } else {
          _tmpOrderStatus = _cursor.getString(_cursorIndexOfOrderStatus);
        }
        _result.setOrderStatus(_tmpOrderStatus);
        final String _tmpCreatedAt;
        if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
          _tmpCreatedAt = null;
        } else {
          _tmpCreatedAt = _cursor.getString(_cursorIndexOfCreatedAt);
        }
        _result.setCreatedAt(_tmpCreatedAt);
        final String _tmpUpdatedAt;
        if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
          _tmpUpdatedAt = null;
        } else {
          _tmpUpdatedAt = _cursor.getString(_cursorIndexOfUpdatedAt);
        }
        _result.setUpdatedAt(_tmpUpdatedAt);
        final int _tmpShipperId;
        _tmpShipperId = _cursor.getInt(_cursorIndexOfShipperId);
        _result.setShipperId(_tmpShipperId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public double getTotalPriceByOrderId(final int orderId) {
    final String _sql = "SELECT COALESCE(SUM(f.price * od.quantity), 0) FROM OrderDetails od INNER JOIN Foods f ON od.food_id = f.food_id WHERE od.order_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, orderId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final double _result;
      if (_cursor.moveToFirst()) {
        _result = _cursor.getDouble(0);
      } else {
        _result = 0.0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Order getLastInsertedOrder() {
    final String _sql = "SELECT * FROM `Orders` ORDER BY order_id DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfOrderId = CursorUtil.getColumnIndexOrThrow(_cursor, "order_id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
      final int _cursorIndexOfRestaurantId = CursorUtil.getColumnIndexOrThrow(_cursor, "restaurant_id");
      final int _cursorIndexOfTotalPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "total_price");
      final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "payment_method");
      final int _cursorIndexOfOrderStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "order_status");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
      final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
      final int _cursorIndexOfShipperId = CursorUtil.getColumnIndexOrThrow(_cursor, "shipper_id");
      final Order _result;
      if (_cursor.moveToFirst()) {
        _result = new Order();
        final int _tmpOrderId;
        _tmpOrderId = _cursor.getInt(_cursorIndexOfOrderId);
        _result.setOrderId(_tmpOrderId);
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        _result.setUserId(_tmpUserId);
        final int _tmpRestaurantId;
        _tmpRestaurantId = _cursor.getInt(_cursorIndexOfRestaurantId);
        _result.setRestaurantId(_tmpRestaurantId);
        final double _tmpTotalPrice;
        _tmpTotalPrice = _cursor.getDouble(_cursorIndexOfTotalPrice);
        _result.setTotalPrice(_tmpTotalPrice);
        final String _tmpPaymentMethod;
        if (_cursor.isNull(_cursorIndexOfPaymentMethod)) {
          _tmpPaymentMethod = null;
        } else {
          _tmpPaymentMethod = _cursor.getString(_cursorIndexOfPaymentMethod);
        }
        _result.setPaymentMethod(_tmpPaymentMethod);
        final String _tmpOrderStatus;
        if (_cursor.isNull(_cursorIndexOfOrderStatus)) {
          _tmpOrderStatus = null;
        } else {
          _tmpOrderStatus = _cursor.getString(_cursorIndexOfOrderStatus);
        }
        _result.setOrderStatus(_tmpOrderStatus);
        final String _tmpCreatedAt;
        if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
          _tmpCreatedAt = null;
        } else {
          _tmpCreatedAt = _cursor.getString(_cursorIndexOfCreatedAt);
        }
        _result.setCreatedAt(_tmpCreatedAt);
        final String _tmpUpdatedAt;
        if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
          _tmpUpdatedAt = null;
        } else {
          _tmpUpdatedAt = _cursor.getString(_cursorIndexOfUpdatedAt);
        }
        _result.setUpdatedAt(_tmpUpdatedAt);
        final int _tmpShipperId;
        _tmpShipperId = _cursor.getInt(_cursorIndexOfShipperId);
        _result.setShipperId(_tmpShipperId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Order> getOrdersByUserId(final int userId) {
    final String _sql = "SELECT * FROM Orders WHERE user_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfOrderId = CursorUtil.getColumnIndexOrThrow(_cursor, "order_id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
      final int _cursorIndexOfRestaurantId = CursorUtil.getColumnIndexOrThrow(_cursor, "restaurant_id");
      final int _cursorIndexOfTotalPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "total_price");
      final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "payment_method");
      final int _cursorIndexOfOrderStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "order_status");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
      final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
      final int _cursorIndexOfShipperId = CursorUtil.getColumnIndexOrThrow(_cursor, "shipper_id");
      final List<Order> _result = new ArrayList<Order>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Order _item;
        _item = new Order();
        final int _tmpOrderId;
        _tmpOrderId = _cursor.getInt(_cursorIndexOfOrderId);
        _item.setOrderId(_tmpOrderId);
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        _item.setUserId(_tmpUserId);
        final int _tmpRestaurantId;
        _tmpRestaurantId = _cursor.getInt(_cursorIndexOfRestaurantId);
        _item.setRestaurantId(_tmpRestaurantId);
        final double _tmpTotalPrice;
        _tmpTotalPrice = _cursor.getDouble(_cursorIndexOfTotalPrice);
        _item.setTotalPrice(_tmpTotalPrice);
        final String _tmpPaymentMethod;
        if (_cursor.isNull(_cursorIndexOfPaymentMethod)) {
          _tmpPaymentMethod = null;
        } else {
          _tmpPaymentMethod = _cursor.getString(_cursorIndexOfPaymentMethod);
        }
        _item.setPaymentMethod(_tmpPaymentMethod);
        final String _tmpOrderStatus;
        if (_cursor.isNull(_cursorIndexOfOrderStatus)) {
          _tmpOrderStatus = null;
        } else {
          _tmpOrderStatus = _cursor.getString(_cursorIndexOfOrderStatus);
        }
        _item.setOrderStatus(_tmpOrderStatus);
        final String _tmpCreatedAt;
        if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
          _tmpCreatedAt = null;
        } else {
          _tmpCreatedAt = _cursor.getString(_cursorIndexOfCreatedAt);
        }
        _item.setCreatedAt(_tmpCreatedAt);
        final String _tmpUpdatedAt;
        if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
          _tmpUpdatedAt = null;
        } else {
          _tmpUpdatedAt = _cursor.getString(_cursorIndexOfUpdatedAt);
        }
        _item.setUpdatedAt(_tmpUpdatedAt);
        final int _tmpShipperId;
        _tmpShipperId = _cursor.getInt(_cursorIndexOfShipperId);
        _item.setShipperId(_tmpShipperId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Order> getOrdersByShipperId(final int shipperId) {
    final String _sql = "SELECT * FROM Orders WHERE shipper_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, shipperId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfOrderId = CursorUtil.getColumnIndexOrThrow(_cursor, "order_id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
      final int _cursorIndexOfRestaurantId = CursorUtil.getColumnIndexOrThrow(_cursor, "restaurant_id");
      final int _cursorIndexOfTotalPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "total_price");
      final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "payment_method");
      final int _cursorIndexOfOrderStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "order_status");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
      final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
      final int _cursorIndexOfShipperId = CursorUtil.getColumnIndexOrThrow(_cursor, "shipper_id");
      final List<Order> _result = new ArrayList<Order>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Order _item;
        _item = new Order();
        final int _tmpOrderId;
        _tmpOrderId = _cursor.getInt(_cursorIndexOfOrderId);
        _item.setOrderId(_tmpOrderId);
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        _item.setUserId(_tmpUserId);
        final int _tmpRestaurantId;
        _tmpRestaurantId = _cursor.getInt(_cursorIndexOfRestaurantId);
        _item.setRestaurantId(_tmpRestaurantId);
        final double _tmpTotalPrice;
        _tmpTotalPrice = _cursor.getDouble(_cursorIndexOfTotalPrice);
        _item.setTotalPrice(_tmpTotalPrice);
        final String _tmpPaymentMethod;
        if (_cursor.isNull(_cursorIndexOfPaymentMethod)) {
          _tmpPaymentMethod = null;
        } else {
          _tmpPaymentMethod = _cursor.getString(_cursorIndexOfPaymentMethod);
        }
        _item.setPaymentMethod(_tmpPaymentMethod);
        final String _tmpOrderStatus;
        if (_cursor.isNull(_cursorIndexOfOrderStatus)) {
          _tmpOrderStatus = null;
        } else {
          _tmpOrderStatus = _cursor.getString(_cursorIndexOfOrderStatus);
        }
        _item.setOrderStatus(_tmpOrderStatus);
        final String _tmpCreatedAt;
        if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
          _tmpCreatedAt = null;
        } else {
          _tmpCreatedAt = _cursor.getString(_cursorIndexOfCreatedAt);
        }
        _item.setCreatedAt(_tmpCreatedAt);
        final String _tmpUpdatedAt;
        if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
          _tmpUpdatedAt = null;
        } else {
          _tmpUpdatedAt = _cursor.getString(_cursorIndexOfUpdatedAt);
        }
        _item.setUpdatedAt(_tmpUpdatedAt);
        final int _tmpShipperId;
        _tmpShipperId = _cursor.getInt(_cursorIndexOfShipperId);
        _item.setShipperId(_tmpShipperId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Order> getDeliveredOrCancelledOrdersByUserId(final int userId) {
    final String _sql = "SELECT * FROM Orders WHERE order_status IN ('Delivered', 'Cancelled','Preparing') AND user_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfOrderId = CursorUtil.getColumnIndexOrThrow(_cursor, "order_id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
      final int _cursorIndexOfRestaurantId = CursorUtil.getColumnIndexOrThrow(_cursor, "restaurant_id");
      final int _cursorIndexOfTotalPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "total_price");
      final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "payment_method");
      final int _cursorIndexOfOrderStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "order_status");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
      final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
      final int _cursorIndexOfShipperId = CursorUtil.getColumnIndexOrThrow(_cursor, "shipper_id");
      final List<Order> _result = new ArrayList<Order>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Order _item;
        _item = new Order();
        final int _tmpOrderId;
        _tmpOrderId = _cursor.getInt(_cursorIndexOfOrderId);
        _item.setOrderId(_tmpOrderId);
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        _item.setUserId(_tmpUserId);
        final int _tmpRestaurantId;
        _tmpRestaurantId = _cursor.getInt(_cursorIndexOfRestaurantId);
        _item.setRestaurantId(_tmpRestaurantId);
        final double _tmpTotalPrice;
        _tmpTotalPrice = _cursor.getDouble(_cursorIndexOfTotalPrice);
        _item.setTotalPrice(_tmpTotalPrice);
        final String _tmpPaymentMethod;
        if (_cursor.isNull(_cursorIndexOfPaymentMethod)) {
          _tmpPaymentMethod = null;
        } else {
          _tmpPaymentMethod = _cursor.getString(_cursorIndexOfPaymentMethod);
        }
        _item.setPaymentMethod(_tmpPaymentMethod);
        final String _tmpOrderStatus;
        if (_cursor.isNull(_cursorIndexOfOrderStatus)) {
          _tmpOrderStatus = null;
        } else {
          _tmpOrderStatus = _cursor.getString(_cursorIndexOfOrderStatus);
        }
        _item.setOrderStatus(_tmpOrderStatus);
        final String _tmpCreatedAt;
        if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
          _tmpCreatedAt = null;
        } else {
          _tmpCreatedAt = _cursor.getString(_cursorIndexOfCreatedAt);
        }
        _item.setCreatedAt(_tmpCreatedAt);
        final String _tmpUpdatedAt;
        if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
          _tmpUpdatedAt = null;
        } else {
          _tmpUpdatedAt = _cursor.getString(_cursorIndexOfUpdatedAt);
        }
        _item.setUpdatedAt(_tmpUpdatedAt);
        final int _tmpShipperId;
        _tmpShipperId = _cursor.getInt(_cursorIndexOfShipperId);
        _item.setShipperId(_tmpShipperId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<FoodWithOrder> getFoodNamesByOrderId(final int orderId) {
    final String _sql = "SELECT o.order_id, o.user_id, od.food_id, f.name AS food_name FROM Orders o INNER JOIN OrderDetails od ON o.order_id = od.order_id INNER JOIN Foods f ON od.food_id = f.food_id WHERE o.order_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, orderId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfOrderId = 0;
      final int _cursorIndexOfUserId = 1;
      final int _cursorIndexOfFoodId = 2;
      final int _cursorIndexOfFoodName = 3;
      final List<FoodWithOrder> _result = new ArrayList<FoodWithOrder>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final FoodWithOrder _item;
        _item = new FoodWithOrder();
        _item.order_id = _cursor.getInt(_cursorIndexOfOrderId);
        _item.user_id = _cursor.getInt(_cursorIndexOfUserId);
        _item.food_id = _cursor.getInt(_cursorIndexOfFoodId);
        if (_cursor.isNull(_cursorIndexOfFoodName)) {
          _item.food_name = null;
        } else {
          _item.food_name = _cursor.getString(_cursorIndexOfFoodName);
        }
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<FoodWithOrder> getImageByOrderId(final int orderId) {
    final String _sql = "SELECT o.order_id, o.user_id, od.food_id, f.image_url AS image_url FROM Orders o INNER JOIN OrderDetails od ON o.order_id = od.order_id INNER JOIN Foods f ON od.food_id = f.food_id WHERE o.order_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, orderId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfOrderId = 0;
      final int _cursorIndexOfUserId = 1;
      final int _cursorIndexOfFoodId = 2;
      final int _cursorIndexOfImageUrl = 3;
      final List<FoodWithOrder> _result = new ArrayList<FoodWithOrder>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final FoodWithOrder _item;
        _item = new FoodWithOrder();
        _item.order_id = _cursor.getInt(_cursorIndexOfOrderId);
        _item.user_id = _cursor.getInt(_cursorIndexOfUserId);
        _item.food_id = _cursor.getInt(_cursorIndexOfFoodId);
        if (_cursor.isNull(_cursorIndexOfImageUrl)) {
          _item.image_url = null;
        } else {
          _item.image_url = _cursor.getString(_cursorIndexOfImageUrl);
        }
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Order> getFilteredOrdersByUserId(final int userId) {
    final String _sql = "SELECT * FROM Orders WHERE order_status IN ('Preparing', 'Delivering', 'Delivered') AND user_Id = ? ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfOrderId = CursorUtil.getColumnIndexOrThrow(_cursor, "order_id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
      final int _cursorIndexOfRestaurantId = CursorUtil.getColumnIndexOrThrow(_cursor, "restaurant_id");
      final int _cursorIndexOfTotalPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "total_price");
      final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "payment_method");
      final int _cursorIndexOfOrderStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "order_status");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
      final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
      final int _cursorIndexOfShipperId = CursorUtil.getColumnIndexOrThrow(_cursor, "shipper_id");
      final List<Order> _result = new ArrayList<Order>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Order _item;
        _item = new Order();
        final int _tmpOrderId;
        _tmpOrderId = _cursor.getInt(_cursorIndexOfOrderId);
        _item.setOrderId(_tmpOrderId);
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        _item.setUserId(_tmpUserId);
        final int _tmpRestaurantId;
        _tmpRestaurantId = _cursor.getInt(_cursorIndexOfRestaurantId);
        _item.setRestaurantId(_tmpRestaurantId);
        final double _tmpTotalPrice;
        _tmpTotalPrice = _cursor.getDouble(_cursorIndexOfTotalPrice);
        _item.setTotalPrice(_tmpTotalPrice);
        final String _tmpPaymentMethod;
        if (_cursor.isNull(_cursorIndexOfPaymentMethod)) {
          _tmpPaymentMethod = null;
        } else {
          _tmpPaymentMethod = _cursor.getString(_cursorIndexOfPaymentMethod);
        }
        _item.setPaymentMethod(_tmpPaymentMethod);
        final String _tmpOrderStatus;
        if (_cursor.isNull(_cursorIndexOfOrderStatus)) {
          _tmpOrderStatus = null;
        } else {
          _tmpOrderStatus = _cursor.getString(_cursorIndexOfOrderStatus);
        }
        _item.setOrderStatus(_tmpOrderStatus);
        final String _tmpCreatedAt;
        if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
          _tmpCreatedAt = null;
        } else {
          _tmpCreatedAt = _cursor.getString(_cursorIndexOfCreatedAt);
        }
        _item.setCreatedAt(_tmpCreatedAt);
        final String _tmpUpdatedAt;
        if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
          _tmpUpdatedAt = null;
        } else {
          _tmpUpdatedAt = _cursor.getString(_cursorIndexOfUpdatedAt);
        }
        _item.setUpdatedAt(_tmpUpdatedAt);
        final int _tmpShipperId;
        _tmpShipperId = _cursor.getInt(_cursorIndexOfShipperId);
        _item.setShipperId(_tmpShipperId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Order> getFilteredOrdersPaidByUserId(final int restaurantId) {
    final String _sql = "SELECT * FROM Orders WHERE order_status = 'Delivered' AND restaurant_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, restaurantId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfOrderId = CursorUtil.getColumnIndexOrThrow(_cursor, "order_id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
      final int _cursorIndexOfRestaurantId = CursorUtil.getColumnIndexOrThrow(_cursor, "restaurant_id");
      final int _cursorIndexOfTotalPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "total_price");
      final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "payment_method");
      final int _cursorIndexOfOrderStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "order_status");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
      final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
      final int _cursorIndexOfShipperId = CursorUtil.getColumnIndexOrThrow(_cursor, "shipper_id");
      final List<Order> _result = new ArrayList<Order>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Order _item;
        _item = new Order();
        final int _tmpOrderId;
        _tmpOrderId = _cursor.getInt(_cursorIndexOfOrderId);
        _item.setOrderId(_tmpOrderId);
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        _item.setUserId(_tmpUserId);
        final int _tmpRestaurantId;
        _tmpRestaurantId = _cursor.getInt(_cursorIndexOfRestaurantId);
        _item.setRestaurantId(_tmpRestaurantId);
        final double _tmpTotalPrice;
        _tmpTotalPrice = _cursor.getDouble(_cursorIndexOfTotalPrice);
        _item.setTotalPrice(_tmpTotalPrice);
        final String _tmpPaymentMethod;
        if (_cursor.isNull(_cursorIndexOfPaymentMethod)) {
          _tmpPaymentMethod = null;
        } else {
          _tmpPaymentMethod = _cursor.getString(_cursorIndexOfPaymentMethod);
        }
        _item.setPaymentMethod(_tmpPaymentMethod);
        final String _tmpOrderStatus;
        if (_cursor.isNull(_cursorIndexOfOrderStatus)) {
          _tmpOrderStatus = null;
        } else {
          _tmpOrderStatus = _cursor.getString(_cursorIndexOfOrderStatus);
        }
        _item.setOrderStatus(_tmpOrderStatus);
        final String _tmpCreatedAt;
        if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
          _tmpCreatedAt = null;
        } else {
          _tmpCreatedAt = _cursor.getString(_cursorIndexOfCreatedAt);
        }
        _item.setCreatedAt(_tmpCreatedAt);
        final String _tmpUpdatedAt;
        if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
          _tmpUpdatedAt = null;
        } else {
          _tmpUpdatedAt = _cursor.getString(_cursorIndexOfUpdatedAt);
        }
        _item.setUpdatedAt(_tmpUpdatedAt);
        final int _tmpShipperId;
        _tmpShipperId = _cursor.getInt(_cursorIndexOfShipperId);
        _item.setShipperId(_tmpShipperId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Order> getFilteredOrdersPaid() {
    final String _sql = "SELECT * FROM Orders WHERE order_status = 'Delivered'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfOrderId = CursorUtil.getColumnIndexOrThrow(_cursor, "order_id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
      final int _cursorIndexOfRestaurantId = CursorUtil.getColumnIndexOrThrow(_cursor, "restaurant_id");
      final int _cursorIndexOfTotalPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "total_price");
      final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "payment_method");
      final int _cursorIndexOfOrderStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "order_status");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
      final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
      final int _cursorIndexOfShipperId = CursorUtil.getColumnIndexOrThrow(_cursor, "shipper_id");
      final List<Order> _result = new ArrayList<Order>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Order _item;
        _item = new Order();
        final int _tmpOrderId;
        _tmpOrderId = _cursor.getInt(_cursorIndexOfOrderId);
        _item.setOrderId(_tmpOrderId);
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        _item.setUserId(_tmpUserId);
        final int _tmpRestaurantId;
        _tmpRestaurantId = _cursor.getInt(_cursorIndexOfRestaurantId);
        _item.setRestaurantId(_tmpRestaurantId);
        final double _tmpTotalPrice;
        _tmpTotalPrice = _cursor.getDouble(_cursorIndexOfTotalPrice);
        _item.setTotalPrice(_tmpTotalPrice);
        final String _tmpPaymentMethod;
        if (_cursor.isNull(_cursorIndexOfPaymentMethod)) {
          _tmpPaymentMethod = null;
        } else {
          _tmpPaymentMethod = _cursor.getString(_cursorIndexOfPaymentMethod);
        }
        _item.setPaymentMethod(_tmpPaymentMethod);
        final String _tmpOrderStatus;
        if (_cursor.isNull(_cursorIndexOfOrderStatus)) {
          _tmpOrderStatus = null;
        } else {
          _tmpOrderStatus = _cursor.getString(_cursorIndexOfOrderStatus);
        }
        _item.setOrderStatus(_tmpOrderStatus);
        final String _tmpCreatedAt;
        if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
          _tmpCreatedAt = null;
        } else {
          _tmpCreatedAt = _cursor.getString(_cursorIndexOfCreatedAt);
        }
        _item.setCreatedAt(_tmpCreatedAt);
        final String _tmpUpdatedAt;
        if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
          _tmpUpdatedAt = null;
        } else {
          _tmpUpdatedAt = _cursor.getString(_cursorIndexOfUpdatedAt);
        }
        _item.setUpdatedAt(_tmpUpdatedAt);
        final int _tmpShipperId;
        _tmpShipperId = _cursor.getInt(_cursorIndexOfShipperId);
        _item.setShipperId(_tmpShipperId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public ShipperWithOrder getShipperWithOrder(final int orderId) {
    final String _sql = "SELECT Orders.order_id AS orderId, Orders.shipper_id AS shipperId, Users.FullName AS shipperName, Users.Phone AS shipperPhone FROM Orders INNER JOIN Shippers ON Orders.shipper_id = Shippers.ShipperID INNER JOIN Users ON Shippers.UserID = Users.UserID WHERE Orders.order_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, orderId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfOrderId = 0;
      final int _cursorIndexOfShipperId = 1;
      final int _cursorIndexOfShipperName = 2;
      final int _cursorIndexOfShipperPhone = 3;
      final ShipperWithOrder _result;
      if (_cursor.moveToFirst()) {
        final int _tmpOrderId;
        _tmpOrderId = _cursor.getInt(_cursorIndexOfOrderId);
        final int _tmpShipperId;
        _tmpShipperId = _cursor.getInt(_cursorIndexOfShipperId);
        final String _tmpShipperName;
        if (_cursor.isNull(_cursorIndexOfShipperName)) {
          _tmpShipperName = null;
        } else {
          _tmpShipperName = _cursor.getString(_cursorIndexOfShipperName);
        }
        final String _tmpShipperPhone;
        if (_cursor.isNull(_cursorIndexOfShipperPhone)) {
          _tmpShipperPhone = null;
        } else {
          _tmpShipperPhone = _cursor.getString(_cursorIndexOfShipperPhone);
        }
        _result = new ShipperWithOrder(_tmpOrderId,_tmpShipperId,_tmpShipperName,_tmpShipperPhone);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Order> getOrdersByRestaurantId(final int restaurantId) {
    final String _sql = "SELECT * FROM Orders WHERE restaurant_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, restaurantId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfOrderId = CursorUtil.getColumnIndexOrThrow(_cursor, "order_id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
      final int _cursorIndexOfRestaurantId = CursorUtil.getColumnIndexOrThrow(_cursor, "restaurant_id");
      final int _cursorIndexOfTotalPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "total_price");
      final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "payment_method");
      final int _cursorIndexOfOrderStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "order_status");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
      final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updated_at");
      final int _cursorIndexOfShipperId = CursorUtil.getColumnIndexOrThrow(_cursor, "shipper_id");
      final List<Order> _result = new ArrayList<Order>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Order _item;
        _item = new Order();
        final int _tmpOrderId;
        _tmpOrderId = _cursor.getInt(_cursorIndexOfOrderId);
        _item.setOrderId(_tmpOrderId);
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        _item.setUserId(_tmpUserId);
        final int _tmpRestaurantId;
        _tmpRestaurantId = _cursor.getInt(_cursorIndexOfRestaurantId);
        _item.setRestaurantId(_tmpRestaurantId);
        final double _tmpTotalPrice;
        _tmpTotalPrice = _cursor.getDouble(_cursorIndexOfTotalPrice);
        _item.setTotalPrice(_tmpTotalPrice);
        final String _tmpPaymentMethod;
        if (_cursor.isNull(_cursorIndexOfPaymentMethod)) {
          _tmpPaymentMethod = null;
        } else {
          _tmpPaymentMethod = _cursor.getString(_cursorIndexOfPaymentMethod);
        }
        _item.setPaymentMethod(_tmpPaymentMethod);
        final String _tmpOrderStatus;
        if (_cursor.isNull(_cursorIndexOfOrderStatus)) {
          _tmpOrderStatus = null;
        } else {
          _tmpOrderStatus = _cursor.getString(_cursorIndexOfOrderStatus);
        }
        _item.setOrderStatus(_tmpOrderStatus);
        final String _tmpCreatedAt;
        if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
          _tmpCreatedAt = null;
        } else {
          _tmpCreatedAt = _cursor.getString(_cursorIndexOfCreatedAt);
        }
        _item.setCreatedAt(_tmpCreatedAt);
        final String _tmpUpdatedAt;
        if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
          _tmpUpdatedAt = null;
        } else {
          _tmpUpdatedAt = _cursor.getString(_cursorIndexOfUpdatedAt);
        }
        _item.setUpdatedAt(_tmpUpdatedAt);
        final int _tmpShipperId;
        _tmpShipperId = _cursor.getInt(_cursorIndexOfShipperId);
        _item.setShipperId(_tmpShipperId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<FoodWithOrder> getFoodsByOrderId(final int orderId) {
    final String _sql = "SELECT o.order_id, o.user_id, od.food_id, f.name AS food_name, f.image_url AS image_url FROM Orders o INNER JOIN OrderDetails od ON o.order_id = od.order_id INNER JOIN Foods f ON od.food_id = f.food_id WHERE o.order_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, orderId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfOrderId = 0;
      final int _cursorIndexOfUserId = 1;
      final int _cursorIndexOfFoodId = 2;
      final int _cursorIndexOfFoodName = 3;
      final int _cursorIndexOfImageUrl = 4;
      final List<FoodWithOrder> _result = new ArrayList<FoodWithOrder>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final FoodWithOrder _item;
        _item = new FoodWithOrder();
        _item.order_id = _cursor.getInt(_cursorIndexOfOrderId);
        _item.user_id = _cursor.getInt(_cursorIndexOfUserId);
        _item.food_id = _cursor.getInt(_cursorIndexOfFoodId);
        if (_cursor.isNull(_cursorIndexOfFoodName)) {
          _item.food_name = null;
        } else {
          _item.food_name = _cursor.getString(_cursorIndexOfFoodName);
        }
        if (_cursor.isNull(_cursorIndexOfImageUrl)) {
          _item.image_url = null;
        } else {
          _item.image_url = _cursor.getString(_cursorIndexOfImageUrl);
        }
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
