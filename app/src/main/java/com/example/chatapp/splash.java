package com.example.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class splash extends AppCompatActivity {
    ImageView logo;
    TextView name, own1, own2;
    Animation topAnim, bottomAnim;
    private static final String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        logo = findViewById(R.id.logoimg);
        name = findViewById(R.id.logonameimg);
        own1 = findViewById(R.id.ownone);
        own2 = findViewById(R.id.owntwo);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        logo.setAnimation(topAnim);
        name.setAnimation(topAnim);
        own1.setAnimation(bottomAnim);
        own2.setAnimation(bottomAnim);

        // Tạo admin account ngay lập tức
        createAdminAccount();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splash.this, login.class);
                startActivity(intent);
                finish();
            }
        }, 4000);
    }

    private void createAdminAccount() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        // SỬA: Sử dụng Db.get() thay vì FirebaseDatabase.getInstance()
        DatabaseReference dbRef = Db.get().getReference("user");

        final String adminEmail = "admin@gmail.com";
        final String adminPass = "123456";

        Log.d(TAG, "Checking/Creating admin account...");

        // Thử đăng nhập trước để kiểm tra account có tồn tại không
        auth.signInWithEmailAndPassword(adminEmail, adminPass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Đăng nhập thành công - kiểm tra xem có dữ liệu user không
                            String uid = task.getResult().getUser().getUid();
                            Log.d(TAG, "Admin auth exists, UID: " + uid);

                            dbRef.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (!snapshot.exists()) {
                                        // Có auth nhưng chưa có data user - tạo data
                                        Log.d(TAG, "Admin auth exists but no user data, creating...");
                                        createAdminUserData(uid, adminEmail);
                                    } else {
                                        Log.d(TAG, "Admin account already exists with data");
                                    }
                                    auth.signOut(); // Luôn signOut sau khi kiểm tra
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Log.e(TAG, "Error checking admin data: " + error.getMessage());
                                    auth.signOut();
                                }
                            });
                        } else {
                            // Auth không tồn tại - tạo mới hoàn toàn
                            Log.d(TAG, "Admin auth doesn't exist, creating new account...");
                            auth.createUserWithEmailAndPassword(adminEmail, adminPass)
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                String uid = task.getResult().getUser().getUid();
                                                Log.d(TAG, "Admin auth created, UID: " + uid);
                                                createAdminUserData(uid, adminEmail);
                                            } else {
                                                Log.e(TAG, "Failed to create admin auth: " + task.getException().getMessage());
                                            }
                                        }
                                    });
                        }
                    }
                });
    }

    private void createAdminUserData(String uid, String email) {
        // SỬA: Sử dụng Db.get() thay vì FirebaseDatabase.getInstance()
        DatabaseReference dbRef = Db.get().getReference("user").child(uid);

        // Sử dụng Map thay vì class Users để đảm bảo cấu trúc đúng
        Map<String, Object> adminData = new HashMap<>();
        adminData.put("userId", uid);
        adminData.put("userName", "Administrator");
        adminData.put("mail", email);
        adminData.put("password", "123456");
        adminData.put("profilepic", "https://via.placeholder.com/150");
        adminData.put("status", "Hey I'm Admin");
        adminData.put("role", "admin");
        adminData.put("banned", false);
        adminData.put("lastMessage", "");

        dbRef.setValue(adminData)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Admin user data created successfully");
                            Toast.makeText(splash.this, "Admin account ready!", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e(TAG, "Failed to create admin user data: " + task.getException().getMessage());
                        }
                    }
                });
    }
}