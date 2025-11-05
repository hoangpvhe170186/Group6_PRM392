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
    ImageView settingBut; // ⭐ THÊM DÒNG NÀY

    private static final String TAG = "MainActivity";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase db = Db.get();
        Log.d("DB", "dbUrl=" + db.getApp().getOptions().getDatabaseUrl());
        db.getReference("_ping_from_app").setValue("hello")
                .addOnCompleteListener(t -> Log.d("DB", "ping=" + t.isSuccessful()
                        + (t.getException()!=null ? " err="+t.getException().getMessage() : "")));

        Log.d(TAG, "MainActivity onCreate started");

        // Kiểm tra Firebase Auth trước
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() == null) {
            Log.d(TAG, "No user logged in, redirecting to login");
            Intent intent = new Intent(MainActivity.this, login.class);
            startActivity(intent);
            finish();
            return;
        }

        Log.d(TAG, "User is logged in: " + auth.getCurrentUser().getEmail());

        database = Db.get();

        // ⚡ QUAN TRỌNG: Sửa từ "Users" thành "user"
        DatabaseReference reference = Db.get()
                .getReference()
                .child("user"); // đúng với key trong Emulator

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

        // ⭐ THÊM PHẦN NÀY: Xử lý nút Settings
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

        // ⭐ CÓ THỂ THÊM: Xử lý các nút khác trong bottom bar nếu cần
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
                    // Đã ở trong chat activity, có thể làm mới hoặc hiển thị thông báo
                    Toast.makeText(MainActivity.this, "You're already in chat", Toast.LENGTH_SHORT).show();
                }
            });
        }

        Log.d(TAG, "MainActivity setup completed");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "MainActivity onStart");
    }
}