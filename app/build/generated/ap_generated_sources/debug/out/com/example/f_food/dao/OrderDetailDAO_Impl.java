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
import com.example.f_food.entity.OrderDetail;
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
public final class OrderDetailDAO_Impl implements OrderDetailDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<OrderDetail> __insertionAdapterOfOrderDetail;

  private final EntityDeletionOrUpdateAdapter<OrderDetail> __deletionAdapterOfOrderDetail;

  private final EntityDeletionOrUpdateAdapter<OrderDetail> __updateAdapterOfOrderDetail;

  private final SharedSQLiteStatement __preparedStmtOfDeleteByOrderId;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public OrderDetailDAO_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfOrderDetail = new EntityInsertionAdapter<OrderDetail>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `OrderDetails` (`order_id`,`food_id`,`quantity`,`price`) VALUES (?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final OrderDetail entity) {
        statement.bindLong(1, entity.getOrderId());
        statement.bindLong(2, entity.getFoodId());
        statement.bindLong(3, entity.getQuantity());
        statement.bindDouble(4, entity.getPrice());
      }
    };
    this.__deletionAdapterOfOrderDetail = new EntityDeletionOrUpdateAdapter<OrderDetail>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `OrderDetails` WHERE `order_id` = ? AND `food_id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final OrderDetail entity) {
        statement.bindLong(1, entity.getOrderId());
        statement.bindLong(2, entity.getFoodId());
      }
    };
    this.__updateAdapterOfOrderDetail = new EntityDeletionOrUpdateAdapter<OrderDetail>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `OrderDetails` SET `order_id` = ?,`food_id` = ?,`quantity` = ?,`price` = ? WHERE `order_id` = ? AND `food_id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final OrderDetail entity) {
        statement.bindLong(1, entity.getOrderId());
        statement.bindLong(2, entity.getFoodId());
        statement.bindLong(3, entity.getQuantity());
        statement.bindDouble(4, entity.getPrice());
        statement.bindLong(5, entity.getOrderId());
        statement.bindLong(6, entity.getFoodId());
      }
    };
    this.__preparedStmtOfDeleteByOrderId = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM OrderDetails WHERE order_id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM OrderDetails";
        return _query;
      }
    };
  }

  @Override
  public void insert(final OrderDetail orderDetail) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfOrderDetail.insert(orderDetail);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(final List<OrderDetail> orderDetails) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfOrderDetail.insert(orderDetails);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final OrderDetail orderDetail) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfOrderDetail.handle(orderDetail);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final OrderDetail orderDetail) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfOrderDetail.handle(orderDetail);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteByOrderId(final int orderId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteByOrderId.acquire();
    int _argIndex = 1;
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
      __preparedStmtOfDeleteByOrderId.release(_stmt);
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
  public List<OrderDetail> getAllOrderDetails() {
    final String _sql = "SELECT * FROM OrderDetails";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfOrderId = CursorUtil.getColumnIndexOrThrow(_cursor, "order_id");
      final int _cursorIndexOfFoodId = CursorUtil.getColumnIndexOrThrow(_cursor, "food_id");
      final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final List<OrderDetail> _result = new ArrayList<OrderDetail>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final OrderDetail _item;
        _item = new OrderDetail();
        final int _tmpOrderId;
        _tmpOrderId = _cursor.getInt(_cursorIndexOfOrderId);
        _item.setOrderId(_tmpOrderId);
        final int _tmpFoodId;
        _tmpFoodId = _cursor.getInt(_cursorIndexOfFoodId);
        _item.setFoodId(_tmpFoodId);
        final int _tmpQuantity;
        _tmpQuantity = _cursor.getInt(_cursorIndexOfQuantity);
        _item.setQuantity(_tmpQuantity);
        final double _tmpPrice;
        _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
        _item.setPrice(_tmpPrice);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<OrderDetail> getOrderDetailsByOrderId(final int orderId) {
    final String _sql = "SELECT * FROM OrderDetails WHERE order_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, orderId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfOrderId = CursorUtil.getColumnIndexOrThrow(_cursor, "order_id");
      final int _cursorIndexOfFoodId = CursorUtil.getColumnIndexOrThrow(_cursor, "food_id");
      final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final List<OrderDetail> _result = new ArrayList<OrderDetail>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final OrderDetail _item;
        _item = new OrderDetail();
        final int _tmpOrderId;
        _tmpOrderId = _cursor.getInt(_cursorIndexOfOrderId);
        _item.setOrderId(_tmpOrderId);
        final int _tmpFoodId;
        _tmpFoodId = _cursor.getInt(_cursorIndexOfFoodId);
        _item.setFoodId(_tmpFoodId);
        final int _tmpQuantity;
        _tmpQuantity = _cursor.getInt(_cursorIndexOfQuantity);
        _item.setQuantity(_tmpQuantity);
        final double _tmpPrice;
        _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
        _item.setPrice(_tmpPrice);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public double getTotalPriceByOrderId(final int orderId) {
    final String _sql = "SELECT COALESCE(SUM(price * quantity), 0) FROM OrderDetails WHERE order_id = ?";
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
  public int getItemCountByOrderId(final int orderId) {
    final String _sql = "SELECT COUNT(*) FROM OrderDetails WHERE order_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, orderId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if (_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
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
