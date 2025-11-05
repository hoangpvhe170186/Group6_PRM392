package com.example.chatapp;

import com.google.firebase.database.FirebaseDatabase;
import android.util.Log;

public final class Db {
    private static final String TAG = "Db";
    private static final String EMU_HOST = "10.0.2.2";
    private static final int EMU_PORT = 9000;
    private static final String RTDB_NS = "chat-app-22abe-default-rtdb";

    public static FirebaseDatabase get() {
        try {
            String url = "http://" + EMU_HOST + ":" + EMU_PORT + "?ns=" + RTDB_NS;
            FirebaseDatabase db = FirebaseDatabase.getInstance(url);
            Log.d(TAG, "Database URL: " + url);
            return db;
        } catch (Exception e) {
            Log.e(TAG, "Error getting database instance: " + e.getMessage());
            // Fallback to default database if emulator fails
            return FirebaseDatabase.getInstance();
        }
    }

    private Db() {}
}