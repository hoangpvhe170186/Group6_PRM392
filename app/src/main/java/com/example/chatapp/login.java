package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    TextView logsignup;
    Button button;
    EditText email, password;
    FirebaseAuth auth;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        auth = FirebaseAuth.getInstance();
        button = findViewById(R.id.logbutton);
        email = findViewById(R.id.editTexLogEmail);
        password = findViewById(R.id.editTextLogPassword);
        logsignup = findViewById(R.id.logsignup);

        logsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, registration.class);
                startActivity(intent);
                finish();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString();
                String pass = password.getText().toString();

                if ((TextUtils.isEmpty(Email))) {
                    Toast.makeText(login.this, "Enter The Email", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(login.this, "Enter The Password", Toast.LENGTH_SHORT).show();
                } else if (!Email.matches(emailPattern)) {
                    email.setError("Give Proper Email Address");
                } else if (password.length() < 6) {
                    password.setError("More Then Six Characters");
                    Toast.makeText(login.this, "Password Needs To Be Longer Then Six Characters", Toast.LENGTH_SHORT).show();
                } else {
                    progressDialog.show();
                    Log.d(TAG, "Attempting login for: " + Email);

                    auth.signInWithEmailAndPassword(Email, pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        String uid = task.getResult().getUser().getUid();
                                        Log.d(TAG, "Auth successful, UID: " + uid);

                                        DatabaseReference ref = Db.get().getReference("user").child(uid);
                                        Log.d(TAG, "Checking user data at: user/" + uid);

                                        ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                progressDialog.dismiss();

                                                Log.d(TAG, "User data exists: " + snapshot.exists());

                                                if (!snapshot.exists()) {
                                                    Toast.makeText(login.this, "Tài khoản chưa có hồ sơ user.", Toast.LENGTH_SHORT).show();
                                                    Log.e(TAG, "No user data found for UID: " + uid);
                                                    FirebaseAuth.getInstance().signOut();
                                                    return;
                                                }

                                                // Debug: log tất cả data
                                                Log.d(TAG, "User data: " + snapshot.getValue());

                                                Boolean banned = snapshot.child("banned").getValue(Boolean.class);
                                                String role = snapshot.child("role").getValue(String.class);

                                                Log.d(TAG, "Role: " + role + ", Banned: " + banned);

                                                if (banned != null && banned) {
                                                    Toast.makeText(login.this, "Tài khoản đã bị ban.", Toast.LENGTH_LONG).show();
                                                    FirebaseAuth.getInstance().signOut();
                                                    return;
                                                }

                                                Intent intent;
                                                if ("admin".equals(role)) {
                                                    intent = new Intent(login.this, AdminActivity.class);
                                                    Toast.makeText(login.this, "Welcome Admin!", Toast.LENGTH_SHORT).show();
                                                    Log.d(TAG, "Redirecting to AdminActivity");
                                                } else {
                                                    intent = new Intent(login.this, MainActivity.class);
                                                    Log.d(TAG, "Redirecting to MainActivity");
                                                }
                                                startActivity(intent);
                                                finish();
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                                progressDialog.dismiss();
                                                Toast.makeText(login.this, "Lỗi database: " + error.getMessage(), Toast.LENGTH_LONG).show();
                                                Log.e(TAG, "Database error: " + error.getMessage());
                                            }
                                        });
                                    } else {
                                        progressDialog.dismiss();
                                        Toast.makeText(login.this, "Sai email hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                                        Log.e(TAG, "Auth failed: " + task.getException().getMessage());
                                    }
                                }
                            });
                }
            }
        });
    }
}