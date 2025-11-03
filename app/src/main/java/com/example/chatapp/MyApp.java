package com.example.chatapp;

import android.app.Application;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class MyApp extends Application {
  @Override public void onCreate() {
    super.onCreate();
    String host = "10.0.2.2"; // Android Emulator -> PC

    FirebaseAuth.getInstance().useEmulator(host, 9099);
    FirebaseDatabase.getInstance().useEmulator(host, 9000);
    FirebaseStorage.getInstance().useEmulator(host, 9199);
  }
}