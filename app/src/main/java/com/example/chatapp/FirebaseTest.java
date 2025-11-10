package com.example.chatapp;

import android.util.Log;
import com.google.firebase.database.*;

public class FirebaseTest {
    private static final String TAG = "FirebaseTest";

    public static void testConnection() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(".info/connected");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Boolean connected = snapshot.getValue(Boolean.class);
                if (connected != null && connected) {
                    Log.d(TAG, "Firebase Database connected successfully");
                } else {
                    Log.e(TAG, "Firebase Database not connected");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e(TAG, "Firebase Database connection failed: " + error.getMessage());
            }
        });
    }
}