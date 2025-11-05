package com.example.chatapp;

import android.app.Application;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class MyApp extends Application {
  private static final String TAG = "MyApp";

  @Override
  public void onCreate() {
    super.onCreate();

    try {
      // Khởi tạo Firebase
      FirebaseApp.initializeApp(this);
      Log.d(TAG, "Firebase initialized successfully");

      String host = "10.0.2.2"; // Android Emulator -> PC

      // Sử dụng emulator với try-catch
      try {
        FirebaseAuth.getInstance().useEmulator(host, 9099);
        FirebaseDatabase.getInstance().useEmulator(host, 9000);
        FirebaseStorage.getInstance().useEmulator(host, 9199);
        Log.d(TAG, "All emulators connected successfully");
      } catch (Exception e) {
        Log.e(TAG, "Error connecting to emulators: " + e.getMessage());
        // Tiếp tục chạy ngay cả khi không kết nối được emulator
      }

      // Tắt persistence để tránh cache
      FirebaseDatabase.getInstance().setPersistenceEnabled(false);

    } catch (Exception e) {
      Log.e(TAG, "Firebase initialization failed: " + e.getMessage());
    }
  }
}