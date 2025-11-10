package com.example.chatapp;

import com.google.firebase.database.FirebaseDatabase;
import android.util.Log;

public final class Db {
    private static final String TAG = "Db";
    private static FirebaseDatabase database;

    // THÊM DATABASE URL
    private static final String DATABASE_URL = "http://10.0.2.2:9000?ns=chat-app-22abe-default-rtdb";

    public static FirebaseDatabase get() {
        if (database == null) {
            try {
                // SỬ DỤNG DATABASE URL CỤ THỂ
                database = FirebaseDatabase.getInstance(DATABASE_URL);
                database.setLogLevel(com.google.firebase.database.Logger.Level.DEBUG);
                Log.d(TAG, "Database instance obtained with URL: " + DATABASE_URL);
            } catch (Exception e) {
                Log.e(TAG, "Error getting database instance: " + e.getMessage());
                // Fallback to default instance
                database = FirebaseDatabase.getInstance();
            }
        }
        return database;
    }

    private Db() {}
}