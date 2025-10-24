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
import com.example.f_food.entity.User;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class UserDAO_Impl implements UserDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<User> __insertionAdapterOfUser;

  private final EntityDeletionOrUpdateAdapter<User> __deletionAdapterOfUser;

  private final EntityDeletionOrUpdateAdapter<User> __updateAdapterOfUser;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public UserDAO_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUser = new EntityInsertionAdapter<User>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `Users` (`UserID`,`FullName`,`Email`,`Password`,`Phone`,`UserType`,`CreatedAt`,`UpdatedAt`,`IsDelete`,`VerifyCode`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final User entity) {
        statement.bindLong(1, entity.getUserId());
        if (entity.getFullName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getFullName());
        }
        if (entity.getEmail() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getEmail());
        }
        if (entity.getPassword() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getPassword());
        }
        if (entity.getPhone() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getPhone());
        }
        if (entity.getUserType() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getUserType());
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
        final Integer _tmp = entity.getIsDelete() == null ? null : (entity.getIsDelete() ? 1 : 0);
        if (_tmp == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, _tmp);
        }
        if (entity.getVerifyCode() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getVerifyCode());
        }
      }
    };
    this.__deletionAdapterOfUser = new EntityDeletionOrUpdateAdapter<User>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `Users` WHERE `UserID` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final User entity) {
        statement.bindLong(1, entity.getUserId());
      }
    };
    this.__updateAdapterOfUser = new EntityDeletionOrUpdateAdapter<User>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `Users` SET `UserID` = ?,`FullName` = ?,`Email` = ?,`Password` = ?,`Phone` = ?,`UserType` = ?,`CreatedAt` = ?,`UpdatedAt` = ?,`IsDelete` = ?,`VerifyCode` = ? WHERE `UserID` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final User entity) {
        statement.bindLong(1, entity.getUserId());
        if (entity.getFullName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getFullName());
        }
        if (entity.getEmail() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getEmail());
        }
        if (entity.getPassword() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getPassword());
        }
        if (entity.getPhone() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getPhone());
        }
        if (entity.getUserType() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getUserType());
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
        final Integer _tmp = entity.getIsDelete() == null ? null : (entity.getIsDelete() ? 1 : 0);
        if (_tmp == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, _tmp);
        }
        if (entity.getVerifyCode() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getVerifyCode());
        }
        statement.bindLong(11, entity.getUserId());
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM Users WHERE UserID = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM Users";
        return _query;
      }
    };
  }

  @Override
  public void insert(final User user) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfUser.insert(user);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(final List<User> users) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfUser.insert(users);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final User user) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfUser.handle(user);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final User user) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfUser.handle(user);
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
  public List<User> getAllUsers() {
    final String _sql = "SELECT * FROM Users";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "UserID");
      final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "FullName");
      final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "Email");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "Password");
      final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "Phone");
      final int _cursorIndexOfUserType = CursorUtil.getColumnIndexOrThrow(_cursor, "UserType");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedAt");
      final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "UpdatedAt");
      final int _cursorIndexOfIsDelete = CursorUtil.getColumnIndexOrThrow(_cursor, "IsDelete");
      final int _cursorIndexOfVerifyCode = CursorUtil.getColumnIndexOrThrow(_cursor, "VerifyCode");
      final List<User> _result = new ArrayList<User>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final User _item;
        _item = new User();
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        _item.setUserId(_tmpUserId);
        final String _tmpFullName;
        if (_cursor.isNull(_cursorIndexOfFullName)) {
          _tmpFullName = null;
        } else {
          _tmpFullName = _cursor.getString(_cursorIndexOfFullName);
        }
        _item.setFullName(_tmpFullName);
        final String _tmpEmail;
        if (_cursor.isNull(_cursorIndexOfEmail)) {
          _tmpEmail = null;
        } else {
          _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        }
        _item.setEmail(_tmpEmail);
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        _item.setPassword(_tmpPassword);
        final String _tmpPhone;
        if (_cursor.isNull(_cursorIndexOfPhone)) {
          _tmpPhone = null;
        } else {
          _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
        }
        _item.setPhone(_tmpPhone);
        final String _tmpUserType;
        if (_cursor.isNull(_cursorIndexOfUserType)) {
          _tmpUserType = null;
        } else {
          _tmpUserType = _cursor.getString(_cursorIndexOfUserType);
        }
        _item.setUserType(_tmpUserType);
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
        final Boolean _tmpIsDelete;
        final Integer _tmp;
        if (_cursor.isNull(_cursorIndexOfIsDelete)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getInt(_cursorIndexOfIsDelete);
        }
        _tmpIsDelete = _tmp == null ? null : _tmp != 0;
        _item.setIsDelete(_tmpIsDelete);
        final String _tmpVerifyCode;
        if (_cursor.isNull(_cursorIndexOfVerifyCode)) {
          _tmpVerifyCode = null;
        } else {
          _tmpVerifyCode = _cursor.getString(_cursorIndexOfVerifyCode);
        }
        _item.setVerifyCode(_tmpVerifyCode);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public User getUserById(final int id) {
    final String _sql = "SELECT * FROM Users WHERE UserID = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "UserID");
      final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "FullName");
      final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "Email");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "Password");
      final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "Phone");
      final int _cursorIndexOfUserType = CursorUtil.getColumnIndexOrThrow(_cursor, "UserType");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedAt");
      final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "UpdatedAt");
      final int _cursorIndexOfIsDelete = CursorUtil.getColumnIndexOrThrow(_cursor, "IsDelete");
      final int _cursorIndexOfVerifyCode = CursorUtil.getColumnIndexOrThrow(_cursor, "VerifyCode");
      final User _result;
      if (_cursor.moveToFirst()) {
        _result = new User();
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        _result.setUserId(_tmpUserId);
        final String _tmpFullName;
        if (_cursor.isNull(_cursorIndexOfFullName)) {
          _tmpFullName = null;
        } else {
          _tmpFullName = _cursor.getString(_cursorIndexOfFullName);
        }
        _result.setFullName(_tmpFullName);
        final String _tmpEmail;
        if (_cursor.isNull(_cursorIndexOfEmail)) {
          _tmpEmail = null;
        } else {
          _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        }
        _result.setEmail(_tmpEmail);
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        _result.setPassword(_tmpPassword);
        final String _tmpPhone;
        if (_cursor.isNull(_cursorIndexOfPhone)) {
          _tmpPhone = null;
        } else {
          _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
        }
        _result.setPhone(_tmpPhone);
        final String _tmpUserType;
        if (_cursor.isNull(_cursorIndexOfUserType)) {
          _tmpUserType = null;
        } else {
          _tmpUserType = _cursor.getString(_cursorIndexOfUserType);
        }
        _result.setUserType(_tmpUserType);
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
        final Boolean _tmpIsDelete;
        final Integer _tmp;
        if (_cursor.isNull(_cursorIndexOfIsDelete)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getInt(_cursorIndexOfIsDelete);
        }
        _tmpIsDelete = _tmp == null ? null : _tmp != 0;
        _result.setIsDelete(_tmpIsDelete);
        final String _tmpVerifyCode;
        if (_cursor.isNull(_cursorIndexOfVerifyCode)) {
          _tmpVerifyCode = null;
        } else {
          _tmpVerifyCode = _cursor.getString(_cursorIndexOfVerifyCode);
        }
        _result.setVerifyCode(_tmpVerifyCode);
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
  public List<User> getAllCustomers() {
    final String _sql = "SELECT * FROM Users WHERE UserType = 'Customer'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "UserID");
      final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "FullName");
      final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "Email");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "Password");
      final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "Phone");
      final int _cursorIndexOfUserType = CursorUtil.getColumnIndexOrThrow(_cursor, "UserType");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedAt");
      final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "UpdatedAt");
      final int _cursorIndexOfIsDelete = CursorUtil.getColumnIndexOrThrow(_cursor, "IsDelete");
      final int _cursorIndexOfVerifyCode = CursorUtil.getColumnIndexOrThrow(_cursor, "VerifyCode");
      final List<User> _result = new ArrayList<User>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final User _item;
        _item = new User();
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        _item.setUserId(_tmpUserId);
        final String _tmpFullName;
        if (_cursor.isNull(_cursorIndexOfFullName)) {
          _tmpFullName = null;
        } else {
          _tmpFullName = _cursor.getString(_cursorIndexOfFullName);
        }
        _item.setFullName(_tmpFullName);
        final String _tmpEmail;
        if (_cursor.isNull(_cursorIndexOfEmail)) {
          _tmpEmail = null;
        } else {
          _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        }
        _item.setEmail(_tmpEmail);
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        _item.setPassword(_tmpPassword);
        final String _tmpPhone;
        if (_cursor.isNull(_cursorIndexOfPhone)) {
          _tmpPhone = null;
        } else {
          _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
        }
        _item.setPhone(_tmpPhone);
        final String _tmpUserType;
        if (_cursor.isNull(_cursorIndexOfUserType)) {
          _tmpUserType = null;
        } else {
          _tmpUserType = _cursor.getString(_cursorIndexOfUserType);
        }
        _item.setUserType(_tmpUserType);
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
        final Boolean _tmpIsDelete;
        final Integer _tmp;
        if (_cursor.isNull(_cursorIndexOfIsDelete)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getInt(_cursorIndexOfIsDelete);
        }
        _tmpIsDelete = _tmp == null ? null : _tmp != 0;
        _item.setIsDelete(_tmpIsDelete);
        final String _tmpVerifyCode;
        if (_cursor.isNull(_cursorIndexOfVerifyCode)) {
          _tmpVerifyCode = null;
        } else {
          _tmpVerifyCode = _cursor.getString(_cursorIndexOfVerifyCode);
        }
        _item.setVerifyCode(_tmpVerifyCode);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public User getUserByEmail(final String email) {
    final String _sql = "SELECT * FROM Users WHERE LOWER(Email) = LOWER(?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (email == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, email);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "UserID");
      final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "FullName");
      final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "Email");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "Password");
      final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "Phone");
      final int _cursorIndexOfUserType = CursorUtil.getColumnIndexOrThrow(_cursor, "UserType");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedAt");
      final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "UpdatedAt");
      final int _cursorIndexOfIsDelete = CursorUtil.getColumnIndexOrThrow(_cursor, "IsDelete");
      final int _cursorIndexOfVerifyCode = CursorUtil.getColumnIndexOrThrow(_cursor, "VerifyCode");
      final User _result;
      if (_cursor.moveToFirst()) {
        _result = new User();
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        _result.setUserId(_tmpUserId);
        final String _tmpFullName;
        if (_cursor.isNull(_cursorIndexOfFullName)) {
          _tmpFullName = null;
        } else {
          _tmpFullName = _cursor.getString(_cursorIndexOfFullName);
        }
        _result.setFullName(_tmpFullName);
        final String _tmpEmail;
        if (_cursor.isNull(_cursorIndexOfEmail)) {
          _tmpEmail = null;
        } else {
          _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        }
        _result.setEmail(_tmpEmail);
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        _result.setPassword(_tmpPassword);
        final String _tmpPhone;
        if (_cursor.isNull(_cursorIndexOfPhone)) {
          _tmpPhone = null;
        } else {
          _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
        }
        _result.setPhone(_tmpPhone);
        final String _tmpUserType;
        if (_cursor.isNull(_cursorIndexOfUserType)) {
          _tmpUserType = null;
        } else {
          _tmpUserType = _cursor.getString(_cursorIndexOfUserType);
        }
        _result.setUserType(_tmpUserType);
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
        final Boolean _tmpIsDelete;
        final Integer _tmp;
        if (_cursor.isNull(_cursorIndexOfIsDelete)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getInt(_cursorIndexOfIsDelete);
        }
        _tmpIsDelete = _tmp == null ? null : _tmp != 0;
        _result.setIsDelete(_tmpIsDelete);
        final String _tmpVerifyCode;
        if (_cursor.isNull(_cursorIndexOfVerifyCode)) {
          _tmpVerifyCode = null;
        } else {
          _tmpVerifyCode = _cursor.getString(_cursorIndexOfVerifyCode);
        }
        _result.setVerifyCode(_tmpVerifyCode);
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
  public User getUserByPhone(final String phone) {
    final String _sql = "SELECT * FROM Users WHERE Phone = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (phone == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, phone);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "UserID");
      final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "FullName");
      final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "Email");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "Password");
      final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "Phone");
      final int _cursorIndexOfUserType = CursorUtil.getColumnIndexOrThrow(_cursor, "UserType");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "CreatedAt");
      final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "UpdatedAt");
      final int _cursorIndexOfIsDelete = CursorUtil.getColumnIndexOrThrow(_cursor, "IsDelete");
      final int _cursorIndexOfVerifyCode = CursorUtil.getColumnIndexOrThrow(_cursor, "VerifyCode");
      final User _result;
      if (_cursor.moveToFirst()) {
        _result = new User();
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        _result.setUserId(_tmpUserId);
        final String _tmpFullName;
        if (_cursor.isNull(_cursorIndexOfFullName)) {
          _tmpFullName = null;
        } else {
          _tmpFullName = _cursor.getString(_cursorIndexOfFullName);
        }
        _result.setFullName(_tmpFullName);
        final String _tmpEmail;
        if (_cursor.isNull(_cursorIndexOfEmail)) {
          _tmpEmail = null;
        } else {
          _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        }
        _result.setEmail(_tmpEmail);
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        _result.setPassword(_tmpPassword);
        final String _tmpPhone;
        if (_cursor.isNull(_cursorIndexOfPhone)) {
          _tmpPhone = null;
        } else {
          _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
        }
        _result.setPhone(_tmpPhone);
        final String _tmpUserType;
        if (_cursor.isNull(_cursorIndexOfUserType)) {
          _tmpUserType = null;
        } else {
          _tmpUserType = _cursor.getString(_cursorIndexOfUserType);
        }
        _result.setUserType(_tmpUserType);
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
        final Boolean _tmpIsDelete;
        final Integer _tmp;
        if (_cursor.isNull(_cursorIndexOfIsDelete)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getInt(_cursorIndexOfIsDelete);
        }
        _tmpIsDelete = _tmp == null ? null : _tmp != 0;
        _result.setIsDelete(_tmpIsDelete);
        final String _tmpVerifyCode;
        if (_cursor.isNull(_cursorIndexOfVerifyCode)) {
          _tmpVerifyCode = null;
        } else {
          _tmpVerifyCode = _cursor.getString(_cursorIndexOfVerifyCode);
        }
        _result.setVerifyCode(_tmpVerifyCode);
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
