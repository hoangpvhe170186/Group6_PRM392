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
import com.example.f_food.entity.Address;
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
public final class AddressDAO_Impl implements AddressDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Address> __insertionAdapterOfAddress;

  private final EntityDeletionOrUpdateAdapter<Address> __deletionAdapterOfAddress;

  private final EntityDeletionOrUpdateAdapter<Address> __updateAdapterOfAddress;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  public AddressDAO_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAddress = new EntityInsertionAdapter<Address>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `Address` (`addressId`,`userId`,`address`,`detailAddress`,`isDefault`,`addressType`,`latitude`,`longitude`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Address entity) {
        statement.bindLong(1, entity.getAddressId());
        statement.bindLong(2, entity.getUserId());
        if (entity.getAddress() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getAddress());
        }
        if (entity.getDetailAddress() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getDetailAddress());
        }
        final int _tmp = entity.isDefault() ? 1 : 0;
        statement.bindLong(5, _tmp);
        if (entity.getAddressType() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getAddressType());
        }
        statement.bindDouble(7, entity.getLatitude());
        statement.bindDouble(8, entity.getLongitude());
      }
    };
    this.__deletionAdapterOfAddress = new EntityDeletionOrUpdateAdapter<Address>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `Address` WHERE `addressId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Address entity) {
        statement.bindLong(1, entity.getAddressId());
      }
    };
    this.__updateAdapterOfAddress = new EntityDeletionOrUpdateAdapter<Address>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `Address` SET `addressId` = ?,`userId` = ?,`address` = ?,`detailAddress` = ?,`isDefault` = ?,`addressType` = ?,`latitude` = ?,`longitude` = ? WHERE `addressId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Address entity) {
        statement.bindLong(1, entity.getAddressId());
        statement.bindLong(2, entity.getUserId());
        if (entity.getAddress() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getAddress());
        }
        if (entity.getDetailAddress() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getDetailAddress());
        }
        final int _tmp = entity.isDefault() ? 1 : 0;
        statement.bindLong(5, _tmp);
        if (entity.getAddressType() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getAddressType());
        }
        statement.bindDouble(7, entity.getLatitude());
        statement.bindDouble(8, entity.getLongitude());
        statement.bindLong(9, entity.getAddressId());
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM Address WHERE addressId = ?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Address address) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfAddress.insert(address);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Address address) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfAddress.handle(address);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Address address) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfAddress.handle(address);
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
  public List<Address> getAllAddresses() {
    final String _sql = "SELECT * FROM Address";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfAddressId = CursorUtil.getColumnIndexOrThrow(_cursor, "addressId");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "address");
      final int _cursorIndexOfDetailAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "detailAddress");
      final int _cursorIndexOfIsDefault = CursorUtil.getColumnIndexOrThrow(_cursor, "isDefault");
      final int _cursorIndexOfAddressType = CursorUtil.getColumnIndexOrThrow(_cursor, "addressType");
      final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
      final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
      final List<Address> _result = new ArrayList<Address>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Address _item;
        _item = new Address();
        final int _tmpAddressId;
        _tmpAddressId = _cursor.getInt(_cursorIndexOfAddressId);
        _item.setAddressId(_tmpAddressId);
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        _item.setUserId(_tmpUserId);
        final String _tmpAddress;
        if (_cursor.isNull(_cursorIndexOfAddress)) {
          _tmpAddress = null;
        } else {
          _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
        }
        _item.setAddress(_tmpAddress);
        final String _tmpDetailAddress;
        if (_cursor.isNull(_cursorIndexOfDetailAddress)) {
          _tmpDetailAddress = null;
        } else {
          _tmpDetailAddress = _cursor.getString(_cursorIndexOfDetailAddress);
        }
        _item.setDetailAddress(_tmpDetailAddress);
        final boolean _tmpIsDefault;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsDefault);
        _tmpIsDefault = _tmp != 0;
        _item.setDefault(_tmpIsDefault);
        final String _tmpAddressType;
        if (_cursor.isNull(_cursorIndexOfAddressType)) {
          _tmpAddressType = null;
        } else {
          _tmpAddressType = _cursor.getString(_cursorIndexOfAddressType);
        }
        _item.setAddressType(_tmpAddressType);
        final double _tmpLatitude;
        _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
        _item.setLatitude(_tmpLatitude);
        final double _tmpLongitude;
        _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
        _item.setLongitude(_tmpLongitude);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<AddressWithUser> getAddressesByUserId(final int userId) {
    final String _sql = "SELECT User.FullName AS username, Address.address, Address.detailAddress, Address.isDefault, Address.addressType FROM Address INNER JOIN Users AS User ON Address.userId = User.UserID WHERE Address.userId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUsername = 0;
      final int _cursorIndexOfAddress = 1;
      final int _cursorIndexOfDetailAddress = 2;
      final int _cursorIndexOfIsDefault = 3;
      final int _cursorIndexOfAddressType = 4;
      final List<AddressWithUser> _result = new ArrayList<AddressWithUser>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final AddressWithUser _item;
        final String _tmpUsername;
        if (_cursor.isNull(_cursorIndexOfUsername)) {
          _tmpUsername = null;
        } else {
          _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
        }
        final String _tmpAddress;
        if (_cursor.isNull(_cursorIndexOfAddress)) {
          _tmpAddress = null;
        } else {
          _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
        }
        final String _tmpDetailAddress;
        if (_cursor.isNull(_cursorIndexOfDetailAddress)) {
          _tmpDetailAddress = null;
        } else {
          _tmpDetailAddress = _cursor.getString(_cursorIndexOfDetailAddress);
        }
        final boolean _tmpIsDefault;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsDefault);
        _tmpIsDefault = _tmp != 0;
        final String _tmpAddressType;
        if (_cursor.isNull(_cursorIndexOfAddressType)) {
          _tmpAddressType = null;
        } else {
          _tmpAddressType = _cursor.getString(_cursorIndexOfAddressType);
        }
        _item = new AddressWithUser(_tmpUsername,_tmpAddress,_tmpDetailAddress,_tmpIsDefault,_tmpAddressType);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Address getDefaultAddressForUser(final int userId) {
    final String _sql = "SELECT * FROM Address WHERE userId = ? AND isDefault = 1 LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfAddressId = CursorUtil.getColumnIndexOrThrow(_cursor, "addressId");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
      final int _cursorIndexOfAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "address");
      final int _cursorIndexOfDetailAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "detailAddress");
      final int _cursorIndexOfIsDefault = CursorUtil.getColumnIndexOrThrow(_cursor, "isDefault");
      final int _cursorIndexOfAddressType = CursorUtil.getColumnIndexOrThrow(_cursor, "addressType");
      final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
      final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
      final Address _result;
      if (_cursor.moveToFirst()) {
        _result = new Address();
        final int _tmpAddressId;
        _tmpAddressId = _cursor.getInt(_cursorIndexOfAddressId);
        _result.setAddressId(_tmpAddressId);
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        _result.setUserId(_tmpUserId);
        final String _tmpAddress;
        if (_cursor.isNull(_cursorIndexOfAddress)) {
          _tmpAddress = null;
        } else {
          _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
        }
        _result.setAddress(_tmpAddress);
        final String _tmpDetailAddress;
        if (_cursor.isNull(_cursorIndexOfDetailAddress)) {
          _tmpDetailAddress = null;
        } else {
          _tmpDetailAddress = _cursor.getString(_cursorIndexOfDetailAddress);
        }
        _result.setDetailAddress(_tmpDetailAddress);
        final boolean _tmpIsDefault;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsDefault);
        _tmpIsDefault = _tmp != 0;
        _result.setDefault(_tmpIsDefault);
        final String _tmpAddressType;
        if (_cursor.isNull(_cursorIndexOfAddressType)) {
          _tmpAddressType = null;
        } else {
          _tmpAddressType = _cursor.getString(_cursorIndexOfAddressType);
        }
        _result.setAddressType(_tmpAddressType);
        final double _tmpLatitude;
        _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
        _result.setLatitude(_tmpLatitude);
        final double _tmpLongitude;
        _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
        _result.setLongitude(_tmpLongitude);
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
