package com.example.chatapp;

import android.app.Application;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class MyApp extends Application {
  private static final String TAG = "MyApp";
  private static final String EMU_HOST = "10.0.2.2";
  private static final int AUTH_PORT = 9099;
  private static final int DATABASE_PORT = 9000;
  private static final int STORAGE_PORT = 9199;

  // THÊM DATABASE URL CHO EMULATOR
  private static final String DATABASE_URL = "http://" + EMU_HOST + ":" + DATABASE_PORT + "?ns=chat-app-22abe-default-rtdb";

  @Override
  public void onCreate() {
    super.onCreate();

    try {
      FirebaseApp.initializeApp(this);
      Log.d(TAG, "Firebase initialized");

      // CẤU HÌNH DATABASE TRƯỚC KHI KẾT NỐI EMULATOR
      setupDatabase();

      // Sau đó kết nối emulators
      setupEmulators();

      Log.d(TAG, "✓ All Firebase services configured successfully");

    } catch (Exception e) {
      Log.e(TAG, "Firebase init failed: " + e.getMessage());
    }
  }

  private void setupDatabase() {
    try {
      // QUAN TRỌNG: Get instance với database URL cụ thể
      FirebaseDatabase database = FirebaseDatabase.getInstance(DATABASE_URL);
      database.setPersistenceEnabled(true);
      database.setLogLevel(com.google.firebase.database.Logger.Level.DEBUG);
      Log.d(TAG, "✓ Database configured with URL: " + DATABASE_URL);
    } catch (Exception e) {
      Log.e(TAG, "✗ Database configuration failed: " + e.getMessage());
    }
  }

  private void setupEmulators() {
    try {
      Log.d(TAG, "Setting up Firebase Emulators...");

      // Database Emulator - SỬ DỤNG INSTANCE ĐÃ ĐƯỢC CẤU HÌNH
      FirebaseDatabase database = FirebaseDatabase.getInstance(DATABASE_URL);
      database.useEmulator(EMU_HOST, DATABASE_PORT);
      Log.d(TAG, "✓ Database emulator connected to " + EMU_HOST + ":" + DATABASE_PORT);

      // Auth Emulator
      FirebaseAuth auth = FirebaseAuth.getInstance();
      auth.useEmulator(EMU_HOST, AUTH_PORT);
      Log.d(TAG, "✓ Auth emulator connected to " + EMU_HOST + ":" + AUTH_PORT);

      // Storage Emulator
      FirebaseStorage storage = FirebaseStorage.getInstance();
      storage.useEmulator(EMU_HOST, STORAGE_PORT);
      Log.d(TAG, "✓ Storage emulator connected to " + EMU_HOST + ":" + STORAGE_PORT);

      Log.d(TAG, "✓ All emulators connected successfully");

    } catch (Exception e) {
      Log.e(TAG, "✗ Emulator connection failed: " + e.getMessage());
    }
  }
}