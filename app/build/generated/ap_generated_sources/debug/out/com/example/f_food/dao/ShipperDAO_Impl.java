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
import com.example.f_food.entity.Shipper;
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
public final class ShipperDAO_Impl implements ShipperDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Shipper> __insertionAdapterOfShipper;

  private final EntityDeletionOrUpdateAdapter<Shipper> __deletionAdapterOfShipper;

  private final EntityDeletionOrUpdateAdapter<Shipper> __updateAdapterOfShipper;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public ShipperDAO_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfShipper = new EntityInsertionAdapter<Shipper>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `Shippers` (`ShipperID`,`UserID`,`Status`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Shipper entity) {
        statement.bindLong(1, entity.getShipperId());
        statement.bindLong(2, entity.getUserId());
        if (entity.getStatus() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getStatus());
        }
      }
    };
    this.__deletionAdapterOfShipper = new EntityDeletionOrUpdateAdapter<Shipper>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `Shippers` WHERE `ShipperID` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Shipper entity) {
        statement.bindLong(1, entity.getShipperId());
      }
    };
    this.__updateAdapterOfShipper = new EntityDeletionOrUpdateAdapter<Shipper>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `Shippers` SET `ShipperID` = ?,`UserID` = ?,`Status` = ? WHERE `ShipperID` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Shipper entity) {
        statement.bindLong(1, entity.getShipperId());
        statement.bindLong(2, entity.getUserId());
        if (entity.getStatus() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getStatus());
        }
        statement.bindLong(4, entity.getShipperId());
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM Shippers WHERE ShipperID = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM Shippers";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Shipper shipper) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfShipper.insert(shipper);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(final List<Shipper> shippers) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfShipper.insert(shippers);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Shipper shipper) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfShipper.handle(shipper);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Shipper shipper) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfShipper.handle(shipper);
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
  public List<Shipper> getAllShippers() {
    final String _sql = "SELECT * FROM Shippers";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfShipperId = CursorUtil.getColumnIndexOrThrow(_cursor, "ShipperID");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "UserID");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "Status");
      final List<Shipper> _result = new ArrayList<Shipper>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Shipper _item;
        _item = new Shipper();
        final int _tmpShipperId;
        _tmpShipperId = _cursor.getInt(_cursorIndexOfShipperId);
        _item.setShipperId(_tmpShipperId);
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        _item.setUserId(_tmpUserId);
        final String _tmpStatus;
        if (_cursor.isNull(_cursorIndexOfStatus)) {
          _tmpStatus = null;
        } else {
          _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
        }
        _item.setStatus(_tmpStatus);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Shipper getShipperById(final int id) {
    final String _sql = "SELECT * FROM Shippers WHERE ShipperID = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfShipperId = CursorUtil.getColumnIndexOrThrow(_cursor, "ShipperID");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "UserID");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "Status");
      final Shipper _result;
      if (_cursor.moveToFirst()) {
        _result = new Shipper();
        final int _tmpShipperId;
        _tmpShipperId = _cursor.getInt(_cursorIndexOfShipperId);
        _result.setShipperId(_tmpShipperId);
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        _result.setUserId(_tmpUserId);
        final String _tmpStatus;
        if (_cursor.isNull(_cursorIndexOfStatus)) {
          _tmpStatus = null;
        } else {
          _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
        }
        _result.setStatus(_tmpStatus);
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
  public Shipper getShipperByUserId(final int userId) {
    final String _sql = "SELECT * FROM Shippers WHERE UserID = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfShipperId = CursorUtil.getColumnIndexOrThrow(_cursor, "ShipperID");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "UserID");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "Status");
      final Shipper _result;
      if (_cursor.moveToFirst()) {
        _result = new Shipper();
        final int _tmpShipperId;
        _tmpShipperId = _cursor.getInt(_cursorIndexOfShipperId);
        _result.setShipperId(_tmpShipperId);
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        _result.setUserId(_tmpUserId);
        final String _tmpStatus;
        if (_cursor.isNull(_cursorIndexOfStatus)) {
          _tmpStatus = null;
        } else {
          _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
        }
        _result.setStatus(_tmpStatus);
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
  public List<Shipper> getShippersByStatus(final String status) {
    final String _sql = "SELECT * FROM Shippers WHERE Status = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (status == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, status);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfShipperId = CursorUtil.getColumnIndexOrThrow(_cursor, "ShipperID");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "UserID");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "Status");
      final List<Shipper> _result = new ArrayList<Shipper>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Shipper _item;
        _item = new Shipper();
        final int _tmpShipperId;
        _tmpShipperId = _cursor.getInt(_cursorIndexOfShipperId);
        _item.setShipperId(_tmpShipperId);
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        _item.setUserId(_tmpUserId);
        final String _tmpStatus;
        if (_cursor.isNull(_cursorIndexOfStatus)) {
          _tmpStatus = null;
        } else {
          _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
        }
        _item.setStatus(_tmpStatus);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public String getShipperNameByUserId(final int userId) {
    final String _sql = "SELECT Users.FullName FROM Users INNER JOIN Shippers ON Shippers.UserID = Users.UserID WHERE Shippers.UserID = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final String _result;
      if (_cursor.moveToFirst()) {
        if (_cursor.isNull(0)) {
          _result = null;
        } else {
          _result = _cursor.getString(0);
        }
      } else {
        _result = null;
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
