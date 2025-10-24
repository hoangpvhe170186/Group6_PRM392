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
import com.example.f_food.entity.Review;
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
public final class ReviewDAO_Impl implements ReviewDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Review> __insertionAdapterOfReview;

  private final EntityDeletionOrUpdateAdapter<Review> __deletionAdapterOfReview;

  private final EntityDeletionOrUpdateAdapter<Review> __updateAdapterOfReview;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public ReviewDAO_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfReview = new EntityInsertionAdapter<Review>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `review` (`review_id`,`user_id`,`restaurant_id`,`rating`,`comment`,`created_at`,`food_name`,`food_image`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Review entity) {
        statement.bindLong(1, entity.getReviewId());
        statement.bindLong(2, entity.getUserId());
        statement.bindLong(3, entity.getRestaurantId());
        statement.bindLong(4, entity.getRating());
        if (entity.getComment() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getComment());
        }
        if (entity.getCreatedAt() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getCreatedAt());
        }
        if (entity.getFoodName() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getFoodName());
        }
        if (entity.getFoodImage() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getFoodImage());
        }
      }
    };
    this.__deletionAdapterOfReview = new EntityDeletionOrUpdateAdapter<Review>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `review` WHERE `review_id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Review entity) {
        statement.bindLong(1, entity.getReviewId());
      }
    };
    this.__updateAdapterOfReview = new EntityDeletionOrUpdateAdapter<Review>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `review` SET `review_id` = ?,`user_id` = ?,`restaurant_id` = ?,`rating` = ?,`comment` = ?,`created_at` = ?,`food_name` = ?,`food_image` = ? WHERE `review_id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final Review entity) {
        statement.bindLong(1, entity.getReviewId());
        statement.bindLong(2, entity.getUserId());
        statement.bindLong(3, entity.getRestaurantId());
        statement.bindLong(4, entity.getRating());
        if (entity.getComment() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getComment());
        }
        if (entity.getCreatedAt() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getCreatedAt());
        }
        if (entity.getFoodName() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getFoodName());
        }
        if (entity.getFoodImage() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getFoodImage());
        }
        statement.bindLong(9, entity.getReviewId());
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM review WHERE review_id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM review";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Review review) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfReview.insert(review);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(final List<Review> reviews) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfReview.insert(reviews);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Review review) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfReview.handle(review);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Review review) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfReview.handle(review);
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
  public List<Review> getAllReviews() {
    final String _sql = "SELECT * FROM review";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfReviewId = CursorUtil.getColumnIndexOrThrow(_cursor, "review_id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
      final int _cursorIndexOfRestaurantId = CursorUtil.getColumnIndexOrThrow(_cursor, "restaurant_id");
      final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
      final int _cursorIndexOfComment = CursorUtil.getColumnIndexOrThrow(_cursor, "comment");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
      final int _cursorIndexOfFoodName = CursorUtil.getColumnIndexOrThrow(_cursor, "food_name");
      final int _cursorIndexOfFoodImage = CursorUtil.getColumnIndexOrThrow(_cursor, "food_image");
      final List<Review> _result = new ArrayList<Review>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Review _item;
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        final int _tmpRestaurantId;
        _tmpRestaurantId = _cursor.getInt(_cursorIndexOfRestaurantId);
        final int _tmpRating;
        _tmpRating = _cursor.getInt(_cursorIndexOfRating);
        final String _tmpComment;
        if (_cursor.isNull(_cursorIndexOfComment)) {
          _tmpComment = null;
        } else {
          _tmpComment = _cursor.getString(_cursorIndexOfComment);
        }
        final String _tmpCreatedAt;
        if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
          _tmpCreatedAt = null;
        } else {
          _tmpCreatedAt = _cursor.getString(_cursorIndexOfCreatedAt);
        }
        final String _tmpFoodName;
        if (_cursor.isNull(_cursorIndexOfFoodName)) {
          _tmpFoodName = null;
        } else {
          _tmpFoodName = _cursor.getString(_cursorIndexOfFoodName);
        }
        final String _tmpFoodImage;
        if (_cursor.isNull(_cursorIndexOfFoodImage)) {
          _tmpFoodImage = null;
        } else {
          _tmpFoodImage = _cursor.getString(_cursorIndexOfFoodImage);
        }
        _item = new Review(_tmpUserId,_tmpRestaurantId,_tmpRating,_tmpComment,_tmpCreatedAt,_tmpFoodName,_tmpFoodImage);
        final int _tmpReviewId;
        _tmpReviewId = _cursor.getInt(_cursorIndexOfReviewId);
        _item.setReviewId(_tmpReviewId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Review getReviewById(final int id) {
    final String _sql = "SELECT * FROM review WHERE review_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfReviewId = CursorUtil.getColumnIndexOrThrow(_cursor, "review_id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
      final int _cursorIndexOfRestaurantId = CursorUtil.getColumnIndexOrThrow(_cursor, "restaurant_id");
      final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
      final int _cursorIndexOfComment = CursorUtil.getColumnIndexOrThrow(_cursor, "comment");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
      final int _cursorIndexOfFoodName = CursorUtil.getColumnIndexOrThrow(_cursor, "food_name");
      final int _cursorIndexOfFoodImage = CursorUtil.getColumnIndexOrThrow(_cursor, "food_image");
      final Review _result;
      if (_cursor.moveToFirst()) {
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        final int _tmpRestaurantId;
        _tmpRestaurantId = _cursor.getInt(_cursorIndexOfRestaurantId);
        final int _tmpRating;
        _tmpRating = _cursor.getInt(_cursorIndexOfRating);
        final String _tmpComment;
        if (_cursor.isNull(_cursorIndexOfComment)) {
          _tmpComment = null;
        } else {
          _tmpComment = _cursor.getString(_cursorIndexOfComment);
        }
        final String _tmpCreatedAt;
        if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
          _tmpCreatedAt = null;
        } else {
          _tmpCreatedAt = _cursor.getString(_cursorIndexOfCreatedAt);
        }
        final String _tmpFoodName;
        if (_cursor.isNull(_cursorIndexOfFoodName)) {
          _tmpFoodName = null;
        } else {
          _tmpFoodName = _cursor.getString(_cursorIndexOfFoodName);
        }
        final String _tmpFoodImage;
        if (_cursor.isNull(_cursorIndexOfFoodImage)) {
          _tmpFoodImage = null;
        } else {
          _tmpFoodImage = _cursor.getString(_cursorIndexOfFoodImage);
        }
        _result = new Review(_tmpUserId,_tmpRestaurantId,_tmpRating,_tmpComment,_tmpCreatedAt,_tmpFoodName,_tmpFoodImage);
        final int _tmpReviewId;
        _tmpReviewId = _cursor.getInt(_cursorIndexOfReviewId);
        _result.setReviewId(_tmpReviewId);
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
  public List<Review> getReviewsByRestaurantId(final int restaurantId) {
    final String _sql = "SELECT * FROM review WHERE restaurant_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, restaurantId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfReviewId = CursorUtil.getColumnIndexOrThrow(_cursor, "review_id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
      final int _cursorIndexOfRestaurantId = CursorUtil.getColumnIndexOrThrow(_cursor, "restaurant_id");
      final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
      final int _cursorIndexOfComment = CursorUtil.getColumnIndexOrThrow(_cursor, "comment");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
      final int _cursorIndexOfFoodName = CursorUtil.getColumnIndexOrThrow(_cursor, "food_name");
      final int _cursorIndexOfFoodImage = CursorUtil.getColumnIndexOrThrow(_cursor, "food_image");
      final List<Review> _result = new ArrayList<Review>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Review _item;
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        final int _tmpRestaurantId;
        _tmpRestaurantId = _cursor.getInt(_cursorIndexOfRestaurantId);
        final int _tmpRating;
        _tmpRating = _cursor.getInt(_cursorIndexOfRating);
        final String _tmpComment;
        if (_cursor.isNull(_cursorIndexOfComment)) {
          _tmpComment = null;
        } else {
          _tmpComment = _cursor.getString(_cursorIndexOfComment);
        }
        final String _tmpCreatedAt;
        if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
          _tmpCreatedAt = null;
        } else {
          _tmpCreatedAt = _cursor.getString(_cursorIndexOfCreatedAt);
        }
        final String _tmpFoodName;
        if (_cursor.isNull(_cursorIndexOfFoodName)) {
          _tmpFoodName = null;
        } else {
          _tmpFoodName = _cursor.getString(_cursorIndexOfFoodName);
        }
        final String _tmpFoodImage;
        if (_cursor.isNull(_cursorIndexOfFoodImage)) {
          _tmpFoodImage = null;
        } else {
          _tmpFoodImage = _cursor.getString(_cursorIndexOfFoodImage);
        }
        _item = new Review(_tmpUserId,_tmpRestaurantId,_tmpRating,_tmpComment,_tmpCreatedAt,_tmpFoodName,_tmpFoodImage);
        final int _tmpReviewId;
        _tmpReviewId = _cursor.getInt(_cursorIndexOfReviewId);
        _item.setReviewId(_tmpReviewId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Review> getReviewsByUserId(final int userId) {
    final String _sql = "SELECT * FROM review WHERE user_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfReviewId = CursorUtil.getColumnIndexOrThrow(_cursor, "review_id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
      final int _cursorIndexOfRestaurantId = CursorUtil.getColumnIndexOrThrow(_cursor, "restaurant_id");
      final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
      final int _cursorIndexOfComment = CursorUtil.getColumnIndexOrThrow(_cursor, "comment");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
      final int _cursorIndexOfFoodName = CursorUtil.getColumnIndexOrThrow(_cursor, "food_name");
      final int _cursorIndexOfFoodImage = CursorUtil.getColumnIndexOrThrow(_cursor, "food_image");
      final List<Review> _result = new ArrayList<Review>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Review _item;
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        final int _tmpRestaurantId;
        _tmpRestaurantId = _cursor.getInt(_cursorIndexOfRestaurantId);
        final int _tmpRating;
        _tmpRating = _cursor.getInt(_cursorIndexOfRating);
        final String _tmpComment;
        if (_cursor.isNull(_cursorIndexOfComment)) {
          _tmpComment = null;
        } else {
          _tmpComment = _cursor.getString(_cursorIndexOfComment);
        }
        final String _tmpCreatedAt;
        if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
          _tmpCreatedAt = null;
        } else {
          _tmpCreatedAt = _cursor.getString(_cursorIndexOfCreatedAt);
        }
        final String _tmpFoodName;
        if (_cursor.isNull(_cursorIndexOfFoodName)) {
          _tmpFoodName = null;
        } else {
          _tmpFoodName = _cursor.getString(_cursorIndexOfFoodName);
        }
        final String _tmpFoodImage;
        if (_cursor.isNull(_cursorIndexOfFoodImage)) {
          _tmpFoodImage = null;
        } else {
          _tmpFoodImage = _cursor.getString(_cursorIndexOfFoodImage);
        }
        _item = new Review(_tmpUserId,_tmpRestaurantId,_tmpRating,_tmpComment,_tmpCreatedAt,_tmpFoodName,_tmpFoodImage);
        final int _tmpReviewId;
        _tmpReviewId = _cursor.getInt(_cursorIndexOfReviewId);
        _item.setReviewId(_tmpReviewId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Review getReviewByRestaurantAndFoodName(final int restaurantId, final String foodName) {
    final String _sql = "SELECT * FROM review WHERE restaurant_id = ? AND food_name = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, restaurantId);
    _argIndex = 2;
    if (foodName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, foodName);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfReviewId = CursorUtil.getColumnIndexOrThrow(_cursor, "review_id");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
      final int _cursorIndexOfRestaurantId = CursorUtil.getColumnIndexOrThrow(_cursor, "restaurant_id");
      final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
      final int _cursorIndexOfComment = CursorUtil.getColumnIndexOrThrow(_cursor, "comment");
      final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "created_at");
      final int _cursorIndexOfFoodName = CursorUtil.getColumnIndexOrThrow(_cursor, "food_name");
      final int _cursorIndexOfFoodImage = CursorUtil.getColumnIndexOrThrow(_cursor, "food_image");
      final Review _result;
      if (_cursor.moveToFirst()) {
        final int _tmpUserId;
        _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
        final int _tmpRestaurantId;
        _tmpRestaurantId = _cursor.getInt(_cursorIndexOfRestaurantId);
        final int _tmpRating;
        _tmpRating = _cursor.getInt(_cursorIndexOfRating);
        final String _tmpComment;
        if (_cursor.isNull(_cursorIndexOfComment)) {
          _tmpComment = null;
        } else {
          _tmpComment = _cursor.getString(_cursorIndexOfComment);
        }
        final String _tmpCreatedAt;
        if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
          _tmpCreatedAt = null;
        } else {
          _tmpCreatedAt = _cursor.getString(_cursorIndexOfCreatedAt);
        }
        final String _tmpFoodName;
        if (_cursor.isNull(_cursorIndexOfFoodName)) {
          _tmpFoodName = null;
        } else {
          _tmpFoodName = _cursor.getString(_cursorIndexOfFoodName);
        }
        final String _tmpFoodImage;
        if (_cursor.isNull(_cursorIndexOfFoodImage)) {
          _tmpFoodImage = null;
        } else {
          _tmpFoodImage = _cursor.getString(_cursorIndexOfFoodImage);
        }
        _result = new Review(_tmpUserId,_tmpRestaurantId,_tmpRating,_tmpComment,_tmpCreatedAt,_tmpFoodName,_tmpFoodImage);
        final int _tmpReviewId;
        _tmpReviewId = _cursor.getInt(_cursorIndexOfReviewId);
        _result.setReviewId(_tmpReviewId);
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
