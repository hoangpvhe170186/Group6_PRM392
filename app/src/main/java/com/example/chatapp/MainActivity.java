package com.example.chatapp;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    RecyclerView mainUserRecyclerView;
    UserAdpter adapter;
    FirebaseDatabase database;
    ArrayList<Users> usersArrayList;
    ImageView imglogout;
    ImageView settingBut;

    private static final String TAG = "MainActivity";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "MainActivity onCreate started");

        // Test Firebase connection đầu tiên
        testFirebaseConnection();

        // Kiểm tra Firebase Auth
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() == null) {
            Log.d(TAG, "No user logged in, redirecting to login");
            Intent intent = new Intent(MainActivity.this, login.class);
            startActivity(intent);
            finish();
            return;
        }

        Log.d(TAG, "User is logged in: " + auth.getCurrentUser().getEmail());
        Log.d(TAG, "User UID: " + auth.getCurrentUser().getUid());

        database = Db.get();

        // ⚡ QUAN TRỌNG: Sửa từ "Users" thành "user"
        DatabaseReference reference = Db.get()
                .getReference()
                .child("user");

        usersArrayList = new ArrayList<>();

        mainUserRecyclerView = findViewById(R.id.mainUserRecyclerView);
        mainUserRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserAdpter(MainActivity.this, usersArrayList);
        mainUserRecyclerView.setAdapter(adapter);

        // Load users từ Firebase
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d(TAG, "onDataChange: Data received, count: " + snapshot.getChildrenCount());
                usersArrayList.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Users users = dataSnapshot.getValue(Users.class);
                    if (users != null) {
                        Log.d(TAG, "Found user: " + users.getUserName() + " ID: " + users.getUserId());

                        // Bỏ qua user hiện tại
                        if (!users.getUserId().equals(auth.getCurrentUser().getUid())) {
                            usersArrayList.add(users);
                        }
                    }
                }

                Log.d(TAG, "Users list size: " + usersArrayList.size());
                adapter.notifyDataSetChanged();

                // Hiển thị thông báo nếu không có user
                if (usersArrayList.isEmpty()) {
                    Toast.makeText(MainActivity.this, "No other users found", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "No users found in database");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "Database error: " + error.getMessage());
                Toast.makeText(MainActivity.this, "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý logout
        imglogout = findViewById(R.id.logoutimg);
        if (imglogout != null) {
            imglogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog dialog = new Dialog(MainActivity.this, R.style.dialoge);
                    dialog.setContentView(R.layout.dialog_layout);
                    Button no, yes;
                    yes = dialog.findViewById(R.id.yesbnt);
                    no = dialog.findViewById(R.id.nobnt);

                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            FirebaseAuth.getInstance().signOut();
                            Intent intent = new Intent(MainActivity.this, login.class);
                            startActivity(intent);
                            finish();
                        }
                    });

                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            });
        } else {
            Log.e(TAG, "logoutimg not found in layout");
        }

        // Xử lý nút Settings
        settingBut = findViewById(R.id.settingBut);
        if (settingBut != null) {
            settingBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Settings button clicked");
                    Intent intent = new Intent(MainActivity.this, setting.class);
                    startActivity(intent);
                }
            });
        } else {
            Log.e(TAG, "settingBut not found in layout");
        }

        // Xử lý các nút khác trong bottom bar
        ImageView camBut = findViewById(R.id.camBut);
        ImageView chatBut = findViewById(R.id.chatBut);

        if (camBut != null) {
            camBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "Camera feature coming soon", Toast.LENGTH_SHORT).show();
                }
            });
        }

        if (chatBut != null) {
            chatBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "You're already in chat", Toast.LENGTH_SHORT).show();
                }
            });
        }

        Log.d(TAG, "MainActivity setup completed");

    }

    /**
     * Phương thức test kết nối Firebase
     * Test cả Database và Auth
     */
    private void testFirebaseConnection() {
        Log.d(TAG, "=== FIREBASE CONNECTION TEST STARTED ===");

        // Test Database connection
        testDatabaseConnection();

        // Test Auth connection
        testAuthConnection();

        // Test ping đến emulator
        testEmulatorPing();
    }

    /**
     * Test kết nối Database
     */
    private void testDatabaseConnection() {
        Log.d(TAG, "Testing Database connection...");

        DatabaseReference testRef = Db.get().getReference("connection_test");
        String testValue = "test_" + System.currentTimeMillis();

        testRef.setValue(testValue)
                .addOnSuccessListener(aVoid -> {
                    Log.d(TAG, "✓ Database WRITE test: SUCCESS");
                    Toast.makeText(MainActivity.this, "Database connected!", Toast.LENGTH_SHORT).show();

                    // Test đọc dữ liệu vừa ghi
                    testRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String value = snapshot.getValue(String.class);
                            if (testValue.equals(value)) {
                                Log.d(TAG, "✓ Database READ test: SUCCESS");
                            } else {
                                Log.w(TAG, "⚠ Database READ test: DATA MISMATCH");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.e(TAG, "✗ Database READ test: FAILED - " + error.getMessage());
                        }
                    });
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "✗ Database WRITE test: FAILED - " + e.getMessage());
                    Toast.makeText(MainActivity.this,
                            "Database connection failed: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                });
    }

    /**
     * Test kết nối Auth
     */
    private void testAuthConnection() {
        Log.d(TAG, "Testing Auth connection...");

        FirebaseAuth auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            Log.d(TAG, "✓ Auth connection: User is logged in - " +
                    auth.getCurrentUser().getEmail() + " (" + auth.getCurrentUser().getUid() + ")");
        } else {
            Log.d(TAG, "ℹ Auth connection: No user logged in (this is normal for first run)");
        }

        // Test lấy current user token
        if (auth.getCurrentUser() != null) {
            auth.getCurrentUser().getIdToken(true)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "✓ Auth token refresh: SUCCESS");
                        } else {
                            Log.e(TAG, "✗ Auth token refresh: FAILED - " +
                                    task.getException().getMessage());
                        }
                    });
        }
    }

    /**
     * Test ping đến emulator
     */
    private void testEmulatorPing() {
        Log.d(TAG, "Testing emulator ping...");

        DatabaseReference pingRef = Db.get().getReference("_ping_from_app");
        pingRef.setValue("hello_" + System.currentTimeMillis())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "✓ Emulator ping: SUCCESS");
                    } else {
                        Log.e(TAG, "✗ Emulator ping: FAILED - " +
                                (task.getException() != null ?
                                        task.getException().getMessage() : "Unknown error"));
                    }
                });
    }

    /**
     * Test đếm số user trong database
     */
    private void testUserCount() {
        Log.d(TAG, "Testing user count...");

        DatabaseReference userRef = Db.get().getReference("user");
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                long userCount = snapshot.getChildrenCount();
                Log.d(TAG, "✓ User count test: " + userCount + " users found in database");

                // Log chi tiết từng user
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    String userId = userSnapshot.getKey();
                    String userName = userSnapshot.child("userName").getValue(String.class);
                    String email = userSnapshot.child("mail").getValue(String.class);
                    Log.d(TAG, "  - User: " + userName + " (" + email + ") ID: " + userId);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "✗ User count test: FAILED - " + error.getMessage());
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "MainActivity onStart");

        // Test lại khi activity resume
        testUserCount();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "MainActivity onResume");
    }

}