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
import com.example.f_food.entity.Food;
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
public final class FoodDAO_Impl implements FoodDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Food> __insertionAdapterOfFood;

  private final EntityDeletionOrUpdateAdapter<Food> __deletionAdapterOfFood;

  private final EntityDeletionOrUpdateAdapter<Food> __updateAdapterOfFood;

  private final SharedSQLiteStatement __preparedStmtOfUpdateFoodInfo;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public FoodDAO_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFood = new EntityInsertionAdapter<Food>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `Foods` (`food_id`,`restaurant_id`,`name`,`description`,`price`,`category_id`,`image_url`,`stock_status`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Food entity) {
        statement.bindLong(1, entity.getFoodId());
        statement.bindLong(2, entity.getRestaurantId());
        if (entity.getName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getName());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getDescription());
        }
        statement.bindDouble(5, entity.getPrice());
        statement.bindLong(6, entity.getCategoryId());
        if (entity.getImageUrl() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getImageUrl());
        }
        if (entity.getStockStatus() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getStockStatus());
        }
      }
    };
    this.__deletionAdapterOfFood = new EntityDeletionOrUpdateAdapter<Food>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `Foods` WHERE `food_id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Food entity) {
        statement.bindLong(1, entity.getFoodId());
      }
    };
    this.__updateAdapterOfFood = new EntityDeletionOrUpdateAdapter<Food>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `Foods` SET `food_id` = ?,`restaurant_id` = ?,`name` = ?,`description` = ?,`price` = ?,`category_id` = ?,`image_url` = ?,`stock_status` = ? WHERE `food_id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Food entity) {
        statement.bindLong(1, entity.getFoodId());
        statement.bindLong(2, entity.getRestaurantId());
        if (entity.getName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getName());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getDescription());
        }
        statement.bindDouble(5, entity.getPrice());
        statement.bindLong(6, entity.getCategoryId());
        if (entity.getImageUrl() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getImageUrl());
        }
        if (entity.getStockStatus() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getStockStatus());
        }
        statement.bindLong(9, entity.getFoodId());
      }
    };
    this.__preparedStmtOfUpdateFoodInfo = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE Foods SET name = ?, price = ?, description = ?, category_id = ?, stock_status = ? WHERE food_id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM Foods WHERE food_id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM Foods";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Food food) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfFood.insert(food);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(final List<Food> foods) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfFood.insert(foods);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Food food) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfFood.handle(food);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Food food) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfFood.handle(food);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateFoodInfo(final int foodId, final String name, final double price,
      final String description, final int categoryId, final String stockStatus) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateFoodInfo.acquire();
    int _argIndex = 1;
    if (name == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, name);
    }
    _argIndex = 2;
    _stmt.bindDouble(_argIndex, price);
    _argIndex = 3;
    if (description == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, description);
    }
    _argIndex = 4;
    _stmt.bindLong(_argIndex, categoryId);
    _argIndex = 5;
    if (stockStatus == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, stockStatus);
    }
    _argIndex = 6;
    _stmt.bindLong(_argIndex, foodId);
    try {
      __db.beginTransaction();
      try {
        _stmt.executeUpdateDelete();
        __db.setTransactionSuccessful();
      } finally {
        __db.endTransaction();
      }
    } finally {
      __preparedStmtOfUpdateFoodInfo.release(_stmt);
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
  public List<Food> getAllFoods() {
    final String _sql = "SELECT * FROM Foods";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfFoodId = CursorUtil.getColumnIndexOrThrow(_cursor, "food_id");
      final int _cursorIndexOfRestaurantId = CursorUtil.getColumnIndexOrThrow(_cursor, "restaurant_id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "category_id");
      final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "image_url");
      final int _cursorIndexOfStockStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "stock_status");
      final List<Food> _result = new ArrayList<Food>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Food _item;
        _item = new Food();
        final int _tmpFoodId;
        _tmpFoodId = _cursor.getInt(_cursorIndexOfFoodId);
        _item.setFoodId(_tmpFoodId);
        final int _tmpRestaurantId;
        _tmpRestaurantId = _cursor.getInt(_cursorIndexOfRestaurantId);
        _item.setRestaurantId(_tmpRestaurantId);
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        _item.setName(_tmpName);
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        _item.setDescription(_tmpDescription);
        final double _tmpPrice;
        _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
        _item.setPrice(_tmpPrice);
        final int _tmpCategoryId;
        _tmpCategoryId = _cursor.getInt(_cursorIndexOfCategoryId);
        _item.setCategoryId(_tmpCategoryId);
        final String _tmpImageUrl;
        if (_cursor.isNull(_cursorIndexOfImageUrl)) {
          _tmpImageUrl = null;
        } else {
          _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
        }
        _item.setImageUrl(_tmpImageUrl);
        final String _tmpStockStatus;
        if (_cursor.isNull(_cursorIndexOfStockStatus)) {
          _tmpStockStatus = null;
        } else {
          _tmpStockStatus = _cursor.getString(_cursorIndexOfStockStatus);
        }
        _item.setStockStatus(_tmpStockStatus);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Food getFoodById(final int id) {
    final String _sql = "SELECT * FROM Foods WHERE food_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfFoodId = CursorUtil.getColumnIndexOrThrow(_cursor, "food_id");
      final int _cursorIndexOfRestaurantId = CursorUtil.getColumnIndexOrThrow(_cursor, "restaurant_id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "category_id");
      final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "image_url");
      final int _cursorIndexOfStockStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "stock_status");
      final Food _result;
      if (_cursor.moveToFirst()) {
        _result = new Food();
        final int _tmpFoodId;
        _tmpFoodId = _cursor.getInt(_cursorIndexOfFoodId);
        _result.setFoodId(_tmpFoodId);
        final int _tmpRestaurantId;
        _tmpRestaurantId = _cursor.getInt(_cursorIndexOfRestaurantId);
        _result.setRestaurantId(_tmpRestaurantId);
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        _result.setName(_tmpName);
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        _result.setDescription(_tmpDescription);
        final double _tmpPrice;
        _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
        _result.setPrice(_tmpPrice);
        final int _tmpCategoryId;
        _tmpCategoryId = _cursor.getInt(_cursorIndexOfCategoryId);
        _result.setCategoryId(_tmpCategoryId);
        final String _tmpImageUrl;
        if (_cursor.isNull(_cursorIndexOfImageUrl)) {
          _tmpImageUrl = null;
        } else {
          _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
        }
        _result.setImageUrl(_tmpImageUrl);
        final String _tmpStockStatus;
        if (_cursor.isNull(_cursorIndexOfStockStatus)) {
          _tmpStockStatus = null;
        } else {
          _tmpStockStatus = _cursor.getString(_cursorIndexOfStockStatus);
        }
        _result.setStockStatus(_tmpStockStatus);
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
  public List<Food> getFoodsByRestaurantId(final int restaurantId) {
    final String _sql = "SELECT * FROM Foods WHERE restaurant_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, restaurantId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfFoodId = CursorUtil.getColumnIndexOrThrow(_cursor, "food_id");
      final int _cursorIndexOfRestaurantId = CursorUtil.getColumnIndexOrThrow(_cursor, "restaurant_id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "category_id");
      final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "image_url");
      final int _cursorIndexOfStockStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "stock_status");
      final List<Food> _result = new ArrayList<Food>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Food _item;
        _item = new Food();
        final int _tmpFoodId;
        _tmpFoodId = _cursor.getInt(_cursorIndexOfFoodId);
        _item.setFoodId(_tmpFoodId);
        final int _tmpRestaurantId;
        _tmpRestaurantId = _cursor.getInt(_cursorIndexOfRestaurantId);
        _item.setRestaurantId(_tmpRestaurantId);
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        _item.setName(_tmpName);
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        _item.setDescription(_tmpDescription);
        final double _tmpPrice;
        _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
        _item.setPrice(_tmpPrice);
        final int _tmpCategoryId;
        _tmpCategoryId = _cursor.getInt(_cursorIndexOfCategoryId);
        _item.setCategoryId(_tmpCategoryId);
        final String _tmpImageUrl;
        if (_cursor.isNull(_cursorIndexOfImageUrl)) {
          _tmpImageUrl = null;
        } else {
          _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
        }
        _item.setImageUrl(_tmpImageUrl);
        final String _tmpStockStatus;
        if (_cursor.isNull(_cursorIndexOfStockStatus)) {
          _tmpStockStatus = null;
        } else {
          _tmpStockStatus = _cursor.getString(_cursorIndexOfStockStatus);
        }
        _item.setStockStatus(_tmpStockStatus);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Food> getFoodsByCategoryId(final int categoryId) {
    final String _sql = "SELECT * FROM Foods WHERE category_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, categoryId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfFoodId = CursorUtil.getColumnIndexOrThrow(_cursor, "food_id");
      final int _cursorIndexOfRestaurantId = CursorUtil.getColumnIndexOrThrow(_cursor, "restaurant_id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "category_id");
      final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "image_url");
      final int _cursorIndexOfStockStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "stock_status");
      final List<Food> _result = new ArrayList<Food>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Food _item;
        _item = new Food();
        final int _tmpFoodId;
        _tmpFoodId = _cursor.getInt(_cursorIndexOfFoodId);
        _item.setFoodId(_tmpFoodId);
        final int _tmpRestaurantId;
        _tmpRestaurantId = _cursor.getInt(_cursorIndexOfRestaurantId);
        _item.setRestaurantId(_tmpRestaurantId);
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        _item.setName(_tmpName);
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        _item.setDescription(_tmpDescription);
        final double _tmpPrice;
        _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
        _item.setPrice(_tmpPrice);
        final int _tmpCategoryId;
        _tmpCategoryId = _cursor.getInt(_cursorIndexOfCategoryId);
        _item.setCategoryId(_tmpCategoryId);
        final String _tmpImageUrl;
        if (_cursor.isNull(_cursorIndexOfImageUrl)) {
          _tmpImageUrl = null;
        } else {
          _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
        }
        _item.setImageUrl(_tmpImageUrl);
        final String _tmpStockStatus;
        if (_cursor.isNull(_cursorIndexOfStockStatus)) {
          _tmpStockStatus = null;
        } else {
          _tmpStockStatus = _cursor.getString(_cursorIndexOfStockStatus);
        }
        _item.setStockStatus(_tmpStockStatus);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Food> getFoodsByName(final String name) {
    final String _sql = "SELECT * FROM Foods WHERE name LIKE '%' || ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (name == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, name);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfFoodId = CursorUtil.getColumnIndexOrThrow(_cursor, "food_id");
      final int _cursorIndexOfRestaurantId = CursorUtil.getColumnIndexOrThrow(_cursor, "restaurant_id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "category_id");
      final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "image_url");
      final int _cursorIndexOfStockStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "stock_status");
      final List<Food> _result = new ArrayList<Food>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Food _item;
        _item = new Food();
        final int _tmpFoodId;
        _tmpFoodId = _cursor.getInt(_cursorIndexOfFoodId);
        _item.setFoodId(_tmpFoodId);
        final int _tmpRestaurantId;
        _tmpRestaurantId = _cursor.getInt(_cursorIndexOfRestaurantId);
        _item.setRestaurantId(_tmpRestaurantId);
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        _item.setName(_tmpName);
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        _item.setDescription(_tmpDescription);
        final double _tmpPrice;
        _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
        _item.setPrice(_tmpPrice);
        final int _tmpCategoryId;
        _tmpCategoryId = _cursor.getInt(_cursorIndexOfCategoryId);
        _item.setCategoryId(_tmpCategoryId);
        final String _tmpImageUrl;
        if (_cursor.isNull(_cursorIndexOfImageUrl)) {
          _tmpImageUrl = null;
        } else {
          _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
        }
        _item.setImageUrl(_tmpImageUrl);
        final String _tmpStockStatus;
        if (_cursor.isNull(_cursorIndexOfStockStatus)) {
          _tmpStockStatus = null;
        } else {
          _tmpStockStatus = _cursor.getString(_cursorIndexOfStockStatus);
        }
        _item.setStockStatus(_tmpStockStatus);
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
