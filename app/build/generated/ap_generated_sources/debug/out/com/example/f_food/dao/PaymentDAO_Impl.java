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
import com.example.f_food.entity.Payment;
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
public final class PaymentDAO_Impl implements PaymentDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Payment> __insertionAdapterOfPayment;

  private final EntityDeletionOrUpdateAdapter<Payment> __deletionAdapterOfPayment;

  private final EntityDeletionOrUpdateAdapter<Payment> __updateAdapterOfPayment;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public PaymentDAO_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPayment = new EntityInsertionAdapter<Payment>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `Payments` (`payment_id`,`order_id`,`amount`,`payment_method`,`payment_status`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Payment entity) {
        statement.bindLong(1, entity.getPaymentId());
        statement.bindLong(2, entity.getOrderId());
        statement.bindDouble(3, entity.getAmount());
        if (entity.getPaymentMethod() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getPaymentMethod());
        }
        if (entity.getPaymentStatus() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getPaymentStatus());
        }
      }
    };
    this.__deletionAdapterOfPayment = new EntityDeletionOrUpdateAdapter<Payment>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `Payments` WHERE `payment_id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Payment entity) {
        statement.bindLong(1, entity.getPaymentId());
      }
    };
    this.__updateAdapterOfPayment = new EntityDeletionOrUpdateAdapter<Payment>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `Payments` SET `payment_id` = ?,`order_id` = ?,`amount` = ?,`payment_method` = ?,`payment_status` = ? WHERE `payment_id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Payment entity) {
        statement.bindLong(1, entity.getPaymentId());
        statement.bindLong(2, entity.getOrderId());
        statement.bindDouble(3, entity.getAmount());
        if (entity.getPaymentMethod() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getPaymentMethod());
        }
        if (entity.getPaymentStatus() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getPaymentStatus());
        }
        statement.bindLong(6, entity.getPaymentId());
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM Payments WHERE payment_id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM Payments";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Payment payment) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfPayment.insert(payment);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(final List<Payment> payments) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfPayment.insert(payments);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Payment payment) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfPayment.handle(payment);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Payment payment) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfPayment.handle(payment);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
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
  public List<Payment> getAllPayments() {
    final String _sql = "SELECT * FROM Payments";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfPaymentId = CursorUtil.getColumnIndexOrThrow(_cursor, "payment_id");
      final int _cursorIndexOfOrderId = CursorUtil.getColumnIndexOrThrow(_cursor, "order_id");
      final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
      final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "payment_method");
      final int _cursorIndexOfPaymentStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "payment_status");
      final List<Payment> _result = new ArrayList<Payment>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Payment _item;
        final int _tmpOrderId;
        _tmpOrderId = _cursor.getInt(_cursorIndexOfOrderId);
        final double _tmpAmount;
        _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
        final String _tmpPaymentMethod;
        if (_cursor.isNull(_cursorIndexOfPaymentMethod)) {
          _tmpPaymentMethod = null;
        } else {
          _tmpPaymentMethod = _cursor.getString(_cursorIndexOfPaymentMethod);
        }
        final String _tmpPaymentStatus;
        if (_cursor.isNull(_cursorIndexOfPaymentStatus)) {
          _tmpPaymentStatus = null;
        } else {
          _tmpPaymentStatus = _cursor.getString(_cursorIndexOfPaymentStatus);
        }
        _item = new Payment(_tmpOrderId,_tmpAmount,_tmpPaymentMethod,_tmpPaymentStatus);
        final int _tmpPaymentId;
        _tmpPaymentId = _cursor.getInt(_cursorIndexOfPaymentId);
        _item.setPaymentId(_tmpPaymentId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Payment getPaymentById(final int id) {
    final String _sql = "SELECT * FROM Payments WHERE payment_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfPaymentId = CursorUtil.getColumnIndexOrThrow(_cursor, "payment_id");
      final int _cursorIndexOfOrderId = CursorUtil.getColumnIndexOrThrow(_cursor, "order_id");
      final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
      final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "payment_method");
      final int _cursorIndexOfPaymentStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "payment_status");
      final Payment _result;
      if (_cursor.moveToFirst()) {
        final int _tmpOrderId;
        _tmpOrderId = _cursor.getInt(_cursorIndexOfOrderId);
        final double _tmpAmount;
        _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
        final String _tmpPaymentMethod;
        if (_cursor.isNull(_cursorIndexOfPaymentMethod)) {
          _tmpPaymentMethod = null;
        } else {
          _tmpPaymentMethod = _cursor.getString(_cursorIndexOfPaymentMethod);
        }
        final String _tmpPaymentStatus;
        if (_cursor.isNull(_cursorIndexOfPaymentStatus)) {
          _tmpPaymentStatus = null;
        } else {
          _tmpPaymentStatus = _cursor.getString(_cursorIndexOfPaymentStatus);
        }
        _result = new Payment(_tmpOrderId,_tmpAmount,_tmpPaymentMethod,_tmpPaymentStatus);
        final int _tmpPaymentId;
        _tmpPaymentId = _cursor.getInt(_cursorIndexOfPaymentId);
        _result.setPaymentId(_tmpPaymentId);
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
  public List<Payment> getPaymentsByOrderId(final int orderId) {
    final String _sql = "SELECT * FROM Payments WHERE order_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, orderId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfPaymentId = CursorUtil.getColumnIndexOrThrow(_cursor, "payment_id");
      final int _cursorIndexOfOrderId = CursorUtil.getColumnIndexOrThrow(_cursor, "order_id");
      final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
      final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "payment_method");
      final int _cursorIndexOfPaymentStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "payment_status");
      final List<Payment> _result = new ArrayList<Payment>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Payment _item;
        final int _tmpOrderId;
        _tmpOrderId = _cursor.getInt(_cursorIndexOfOrderId);
        final double _tmpAmount;
        _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
        final String _tmpPaymentMethod;
        if (_cursor.isNull(_cursorIndexOfPaymentMethod)) {
          _tmpPaymentMethod = null;
        } else {
          _tmpPaymentMethod = _cursor.getString(_cursorIndexOfPaymentMethod);
        }
        final String _tmpPaymentStatus;
        if (_cursor.isNull(_cursorIndexOfPaymentStatus)) {
          _tmpPaymentStatus = null;
        } else {
          _tmpPaymentStatus = _cursor.getString(_cursorIndexOfPaymentStatus);
        }
        _item = new Payment(_tmpOrderId,_tmpAmount,_tmpPaymentMethod,_tmpPaymentStatus);
        final int _tmpPaymentId;
        _tmpPaymentId = _cursor.getInt(_cursorIndexOfPaymentId);
        _item.setPaymentId(_tmpPaymentId);
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
