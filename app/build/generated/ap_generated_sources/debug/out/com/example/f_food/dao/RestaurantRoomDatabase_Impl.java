package com.example.f_food.dao;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class RestaurantRoomDatabase_Impl extends RestaurantRoomDatabase {
  private volatile RestaurantDAO _restaurantDAO;

  private volatile FoodDAO _foodDAO;

  private volatile PolicyDAO _policyDAO;

  private volatile UserDAO _userDAO;

  private volatile OrderDAO _orderDAO;

  private volatile PaymentDAO _paymentDAO;

  private volatile CategoryDAO _categoryDAO;

  private volatile OrderDetailDAO _orderDetailDAO;

  private volatile ReviewDAO _reviewDAO;

  private volatile AddressDAO _addressDAO;

  private volatile ShipperDAO _shipperDAO;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(30) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `Restaurants` (`restaurant_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `user_id` INTEGER NOT NULL, `name` TEXT, `address` TEXT, `phone` TEXT, `status` TEXT, `created_at` TEXT, `image` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `Foods` (`food_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `restaurant_id` INTEGER NOT NULL, `name` TEXT, `description` TEXT, `price` REAL NOT NULL, `category_id` INTEGER NOT NULL, `image_url` TEXT, `stock_status` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `Users` (`UserID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `FullName` TEXT, `Email` TEXT, `Password` TEXT, `Phone` TEXT, `UserType` TEXT, `CreatedAt` TEXT, `UpdatedAt` TEXT, `IsDelete` INTEGER, `VerifyCode` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `Policies` (`policy_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT, `description` TEXT, `created_at` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `Orders` (`order_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `user_id` INTEGER NOT NULL, `restaurant_id` INTEGER NOT NULL, `total_price` REAL NOT NULL, `payment_method` TEXT, `order_status` TEXT, `created_at` TEXT, `updated_at` TEXT, `shipper_id` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `OrderDetails` (`order_id` INTEGER NOT NULL, `food_id` INTEGER NOT NULL, `quantity` INTEGER NOT NULL, `price` REAL NOT NULL, PRIMARY KEY(`order_id`, `food_id`), FOREIGN KEY(`order_id`) REFERENCES `Orders`(`order_id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`food_id`) REFERENCES `Foods`(`food_id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE TABLE IF NOT EXISTS `review` (`review_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `user_id` INTEGER NOT NULL, `restaurant_id` INTEGER NOT NULL, `rating` INTEGER NOT NULL, `comment` TEXT, `created_at` TEXT, `food_name` TEXT, `food_image` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `Categories` (`category_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `Address` (`addressId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `userId` INTEGER NOT NULL, `address` TEXT, `detailAddress` TEXT, `isDefault` INTEGER NOT NULL, `addressType` TEXT, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `Shippers` (`ShipperID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `UserID` INTEGER NOT NULL, `Status` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `Payments` (`payment_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `order_id` INTEGER NOT NULL, `amount` REAL NOT NULL, `payment_method` TEXT, `payment_status` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'dacbdf96747ed3bfc1b5f16863dd1ad9')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `Restaurants`");
        db.execSQL("DROP TABLE IF EXISTS `Foods`");
        db.execSQL("DROP TABLE IF EXISTS `Users`");
        db.execSQL("DROP TABLE IF EXISTS `Policies`");
        db.execSQL("DROP TABLE IF EXISTS `Orders`");
        db.execSQL("DROP TABLE IF EXISTS `OrderDetails`");
        db.execSQL("DROP TABLE IF EXISTS `review`");
        db.execSQL("DROP TABLE IF EXISTS `Categories`");
        db.execSQL("DROP TABLE IF EXISTS `Address`");
        db.execSQL("DROP TABLE IF EXISTS `Shippers`");
        db.execSQL("DROP TABLE IF EXISTS `Payments`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsRestaurants = new HashMap<String, TableInfo.Column>(8);
        _columnsRestaurants.put("restaurant_id", new TableInfo.Column("restaurant_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRestaurants.put("user_id", new TableInfo.Column("user_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRestaurants.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRestaurants.put("address", new TableInfo.Column("address", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRestaurants.put("phone", new TableInfo.Column("phone", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRestaurants.put("status", new TableInfo.Column("status", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRestaurants.put("created_at", new TableInfo.Column("created_at", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRestaurants.put("image", new TableInfo.Column("image", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRestaurants = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRestaurants = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRestaurants = new TableInfo("Restaurants", _columnsRestaurants, _foreignKeysRestaurants, _indicesRestaurants);
        final TableInfo _existingRestaurants = TableInfo.read(db, "Restaurants");
        if (!_infoRestaurants.equals(_existingRestaurants)) {
          return new RoomOpenHelper.ValidationResult(false, "Restaurants(com.example.f_food.entity.Restaurant).\n"
                  + " Expected:\n" + _infoRestaurants + "\n"
                  + " Found:\n" + _existingRestaurants);
        }
        final HashMap<String, TableInfo.Column> _columnsFoods = new HashMap<String, TableInfo.Column>(8);
        _columnsFoods.put("food_id", new TableInfo.Column("food_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFoods.put("restaurant_id", new TableInfo.Column("restaurant_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFoods.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFoods.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFoods.put("price", new TableInfo.Column("price", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFoods.put("category_id", new TableInfo.Column("category_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFoods.put("image_url", new TableInfo.Column("image_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFoods.put("stock_status", new TableInfo.Column("stock_status", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFoods = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFoods = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFoods = new TableInfo("Foods", _columnsFoods, _foreignKeysFoods, _indicesFoods);
        final TableInfo _existingFoods = TableInfo.read(db, "Foods");
        if (!_infoFoods.equals(_existingFoods)) {
          return new RoomOpenHelper.ValidationResult(false, "Foods(com.example.f_food.entity.Food).\n"
                  + " Expected:\n" + _infoFoods + "\n"
                  + " Found:\n" + _existingFoods);
        }
        final HashMap<String, TableInfo.Column> _columnsUsers = new HashMap<String, TableInfo.Column>(10);
        _columnsUsers.put("UserID", new TableInfo.Column("UserID", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("FullName", new TableInfo.Column("FullName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("Email", new TableInfo.Column("Email", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("Password", new TableInfo.Column("Password", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("Phone", new TableInfo.Column("Phone", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("UserType", new TableInfo.Column("UserType", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("CreatedAt", new TableInfo.Column("CreatedAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("UpdatedAt", new TableInfo.Column("UpdatedAt", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("IsDelete", new TableInfo.Column("IsDelete", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("VerifyCode", new TableInfo.Column("VerifyCode", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUsers = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUsers = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUsers = new TableInfo("Users", _columnsUsers, _foreignKeysUsers, _indicesUsers);
        final TableInfo _existingUsers = TableInfo.read(db, "Users");
        if (!_infoUsers.equals(_existingUsers)) {
          return new RoomOpenHelper.ValidationResult(false, "Users(com.example.f_food.entity.User).\n"
                  + " Expected:\n" + _infoUsers + "\n"
                  + " Found:\n" + _existingUsers);
        }
        final HashMap<String, TableInfo.Column> _columnsPolicies = new HashMap<String, TableInfo.Column>(4);
        _columnsPolicies.put("policy_id", new TableInfo.Column("policy_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPolicies.put("title", new TableInfo.Column("title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPolicies.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPolicies.put("created_at", new TableInfo.Column("created_at", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPolicies = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPolicies = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPolicies = new TableInfo("Policies", _columnsPolicies, _foreignKeysPolicies, _indicesPolicies);
        final TableInfo _existingPolicies = TableInfo.read(db, "Policies");
        if (!_infoPolicies.equals(_existingPolicies)) {
          return new RoomOpenHelper.ValidationResult(false, "Policies(com.example.f_food.entity.Policy).\n"
                  + " Expected:\n" + _infoPolicies + "\n"
                  + " Found:\n" + _existingPolicies);
        }
        final HashMap<String, TableInfo.Column> _columnsOrders = new HashMap<String, TableInfo.Column>(9);
        _columnsOrders.put("order_id", new TableInfo.Column("order_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("user_id", new TableInfo.Column("user_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("restaurant_id", new TableInfo.Column("restaurant_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("total_price", new TableInfo.Column("total_price", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("payment_method", new TableInfo.Column("payment_method", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("order_status", new TableInfo.Column("order_status", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("created_at", new TableInfo.Column("created_at", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("updated_at", new TableInfo.Column("updated_at", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("shipper_id", new TableInfo.Column("shipper_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysOrders = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesOrders = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoOrders = new TableInfo("Orders", _columnsOrders, _foreignKeysOrders, _indicesOrders);
        final TableInfo _existingOrders = TableInfo.read(db, "Orders");
        if (!_infoOrders.equals(_existingOrders)) {
          return new RoomOpenHelper.ValidationResult(false, "Orders(com.example.f_food.entity.Order).\n"
                  + " Expected:\n" + _infoOrders + "\n"
                  + " Found:\n" + _existingOrders);
        }
        final HashMap<String, TableInfo.Column> _columnsOrderDetails = new HashMap<String, TableInfo.Column>(4);
        _columnsOrderDetails.put("order_id", new TableInfo.Column("order_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrderDetails.put("food_id", new TableInfo.Column("food_id", "INTEGER", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrderDetails.put("quantity", new TableInfo.Column("quantity", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrderDetails.put("price", new TableInfo.Column("price", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysOrderDetails = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysOrderDetails.add(new TableInfo.ForeignKey("Orders", "CASCADE", "NO ACTION", Arrays.asList("order_id"), Arrays.asList("order_id")));
        _foreignKeysOrderDetails.add(new TableInfo.ForeignKey("Foods", "CASCADE", "NO ACTION", Arrays.asList("food_id"), Arrays.asList("food_id")));
        final HashSet<TableInfo.Index> _indicesOrderDetails = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoOrderDetails = new TableInfo("OrderDetails", _columnsOrderDetails, _foreignKeysOrderDetails, _indicesOrderDetails);
        final TableInfo _existingOrderDetails = TableInfo.read(db, "OrderDetails");
        if (!_infoOrderDetails.equals(_existingOrderDetails)) {
          return new RoomOpenHelper.ValidationResult(false, "OrderDetails(com.example.f_food.entity.OrderDetail).\n"
                  + " Expected:\n" + _infoOrderDetails + "\n"
                  + " Found:\n" + _existingOrderDetails);
        }
        final HashMap<String, TableInfo.Column> _columnsReview = new HashMap<String, TableInfo.Column>(8);
        _columnsReview.put("review_id", new TableInfo.Column("review_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReview.put("user_id", new TableInfo.Column("user_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReview.put("restaurant_id", new TableInfo.Column("restaurant_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReview.put("rating", new TableInfo.Column("rating", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReview.put("comment", new TableInfo.Column("comment", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReview.put("created_at", new TableInfo.Column("created_at", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReview.put("food_name", new TableInfo.Column("food_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReview.put("food_image", new TableInfo.Column("food_image", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysReview = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesReview = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoReview = new TableInfo("review", _columnsReview, _foreignKeysReview, _indicesReview);
        final TableInfo _existingReview = TableInfo.read(db, "review");
        if (!_infoReview.equals(_existingReview)) {
          return new RoomOpenHelper.ValidationResult(false, "review(com.example.f_food.entity.Review).\n"
                  + " Expected:\n" + _infoReview + "\n"
                  + " Found:\n" + _existingReview);
        }
        final HashMap<String, TableInfo.Column> _columnsCategories = new HashMap<String, TableInfo.Column>(2);
        _columnsCategories.put("category_id", new TableInfo.Column("category_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCategories.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCategories = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCategories = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCategories = new TableInfo("Categories", _columnsCategories, _foreignKeysCategories, _indicesCategories);
        final TableInfo _existingCategories = TableInfo.read(db, "Categories");
        if (!_infoCategories.equals(_existingCategories)) {
          return new RoomOpenHelper.ValidationResult(false, "Categories(com.example.f_food.entity.Category).\n"
                  + " Expected:\n" + _infoCategories + "\n"
                  + " Found:\n" + _existingCategories);
        }
        final HashMap<String, TableInfo.Column> _columnsAddress = new HashMap<String, TableInfo.Column>(8);
        _columnsAddress.put("addressId", new TableInfo.Column("addressId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAddress.put("userId", new TableInfo.Column("userId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAddress.put("address", new TableInfo.Column("address", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAddress.put("detailAddress", new TableInfo.Column("detailAddress", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAddress.put("isDefault", new TableInfo.Column("isDefault", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAddress.put("addressType", new TableInfo.Column("addressType", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAddress.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAddress.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAddress = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAddress = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAddress = new TableInfo("Address", _columnsAddress, _foreignKeysAddress, _indicesAddress);
        final TableInfo _existingAddress = TableInfo.read(db, "Address");
        if (!_infoAddress.equals(_existingAddress)) {
          return new RoomOpenHelper.ValidationResult(false, "Address(com.example.f_food.entity.Address).\n"
                  + " Expected:\n" + _infoAddress + "\n"
                  + " Found:\n" + _existingAddress);
        }
        final HashMap<String, TableInfo.Column> _columnsShippers = new HashMap<String, TableInfo.Column>(3);
        _columnsShippers.put("ShipperID", new TableInfo.Column("ShipperID", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShippers.put("UserID", new TableInfo.Column("UserID", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShippers.put("Status", new TableInfo.Column("Status", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysShippers = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesShippers = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoShippers = new TableInfo("Shippers", _columnsShippers, _foreignKeysShippers, _indicesShippers);
        final TableInfo _existingShippers = TableInfo.read(db, "Shippers");
        if (!_infoShippers.equals(_existingShippers)) {
          return new RoomOpenHelper.ValidationResult(false, "Shippers(com.example.f_food.entity.Shipper).\n"
                  + " Expected:\n" + _infoShippers + "\n"
                  + " Found:\n" + _existingShippers);
        }
        final HashMap<String, TableInfo.Column> _columnsPayments = new HashMap<String, TableInfo.Column>(5);
        _columnsPayments.put("payment_id", new TableInfo.Column("payment_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPayments.put("order_id", new TableInfo.Column("order_id", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPayments.put("amount", new TableInfo.Column("amount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPayments.put("payment_method", new TableInfo.Column("payment_method", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPayments.put("payment_status", new TableInfo.Column("payment_status", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPayments = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPayments = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPayments = new TableInfo("Payments", _columnsPayments, _foreignKeysPayments, _indicesPayments);
        final TableInfo _existingPayments = TableInfo.read(db, "Payments");
        if (!_infoPayments.equals(_existingPayments)) {
          return new RoomOpenHelper.ValidationResult(false, "Payments(com.example.f_food.entity.Payment).\n"
                  + " Expected:\n" + _infoPayments + "\n"
                  + " Found:\n" + _existingPayments);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "dacbdf96747ed3bfc1b5f16863dd1ad9", "120b87045bdd8ae61133f17121896cca");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "Restaurants","Foods","Users","Policies","Orders","OrderDetails","review","Categories","Address","Shippers","Payments");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `Restaurants`");
      _db.execSQL("DELETE FROM `Foods`");
      _db.execSQL("DELETE FROM `Users`");
      _db.execSQL("DELETE FROM `Policies`");
      _db.execSQL("DELETE FROM `Orders`");
      _db.execSQL("DELETE FROM `OrderDetails`");
      _db.execSQL("DELETE FROM `review`");
      _db.execSQL("DELETE FROM `Categories`");
      _db.execSQL("DELETE FROM `Address`");
      _db.execSQL("DELETE FROM `Shippers`");
      _db.execSQL("DELETE FROM `Payments`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(RestaurantDAO.class, RestaurantDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(FoodDAO.class, FoodDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(PolicyDAO.class, PolicyDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(UserDAO.class, UserDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(OrderDAO.class, OrderDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(PaymentDAO.class, PaymentDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(CategoryDAO.class, CategoryDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(OrderDetailDAO.class, OrderDetailDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(ReviewDAO.class, ReviewDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(AddressDAO.class, AddressDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(ShipperDAO.class, ShipperDAO_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public RestaurantDAO restaurantDAO() {
    if (_restaurantDAO != null) {
      return _restaurantDAO;
    } else {
      synchronized(this) {
        if(_restaurantDAO == null) {
          _restaurantDAO = new RestaurantDAO_Impl(this);
        }
        return _restaurantDAO;
      }
    }
  }

  @Override
  public FoodDAO foodDAO() {
    if (_foodDAO != null) {
      return _foodDAO;
    } else {
      synchronized(this) {
        if(_foodDAO == null) {
          _foodDAO = new FoodDAO_Impl(this);
        }
        return _foodDAO;
      }
    }
  }

  @Override
  public PolicyDAO policyDAO() {
    if (_policyDAO != null) {
      return _policyDAO;
    } else {
      synchronized(this) {
        if(_policyDAO == null) {
          _policyDAO = new PolicyDAO_Impl(this);
        }
        return _policyDAO;
      }
    }
  }

  @Override
  public UserDAO userDAO() {
    if (_userDAO != null) {
      return _userDAO;
    } else {
      synchronized(this) {
        if(_userDAO == null) {
          _userDAO = new UserDAO_Impl(this);
        }
        return _userDAO;
      }
    }
  }

  @Override
  public OrderDAO orderDAO() {
    if (_orderDAO != null) {
      return _orderDAO;
    } else {
      synchronized(this) {
        if(_orderDAO == null) {
          _orderDAO = new OrderDAO_Impl(this);
        }
        return _orderDAO;
      }
    }
  }

  @Override
  public PaymentDAO paymentDAO() {
    if (_paymentDAO != null) {
      return _paymentDAO;
    } else {
      synchronized(this) {
        if(_paymentDAO == null) {
          _paymentDAO = new PaymentDAO_Impl(this);
        }
        return _paymentDAO;
      }
    }
  }

  @Override
  public CategoryDAO categoryDAO() {
    if (_categoryDAO != null) {
      return _categoryDAO;
    } else {
      synchronized(this) {
        if(_categoryDAO == null) {
          _categoryDAO = new CategoryDAO_Impl(this);
        }
        return _categoryDAO;
      }
    }
  }

  @Override
  public OrderDetailDAO orderDetailDAO() {
    if (_orderDetailDAO != null) {
      return _orderDetailDAO;
    } else {
      synchronized(this) {
        if(_orderDetailDAO == null) {
          _orderDetailDAO = new OrderDetailDAO_Impl(this);
        }
        return _orderDetailDAO;
      }
    }
  }

  @Override
  public ReviewDAO reviewDAO() {
    if (_reviewDAO != null) {
      return _reviewDAO;
    } else {
      synchronized(this) {
        if(_reviewDAO == null) {
          _reviewDAO = new ReviewDAO_Impl(this);
        }
        return _reviewDAO;
      }
    }
  }

  @Override
  public AddressDAO addressDAO() {
    if (_addressDAO != null) {
      return _addressDAO;
    } else {
      synchronized(this) {
        if(_addressDAO == null) {
          _addressDAO = new AddressDAO_Impl(this);
        }
        return _addressDAO;
      }
    }
  }

  @Override
  public ShipperDAO shipperDAO() {
    if (_shipperDAO != null) {
      return _shipperDAO;
    } else {
      synchronized(this) {
        if(_shipperDAO == null) {
          _shipperDAO = new ShipperDAO_Impl(this);
        }
        return _shipperDAO;
      }
    }
  }
}
