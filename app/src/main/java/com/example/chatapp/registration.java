package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class registration extends AppCompatActivity {
    TextView loginbut;
    EditText rg_username, rg_email, rg_password, rg_repassword;
    Button rg_signup;
    CircleImageView rg_profileImg;
    FirebaseAuth auth;
    Uri imageURI;
    String imageuri;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    FirebaseDatabase database;
    FirebaseStorage storage;
    ProgressDialog progressDialog;

    private static final String TAG = "RegistrationActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Establishing The Account");
        progressDialog.setCancelable(false);
        database = Db.get();
        storage = FirebaseStorage.getInstance();
        auth = FirebaseAuth.getInstance();
        loginbut = findViewById(R.id.loginbut);
        rg_username = findViewById(R.id.rgusername);
        rg_email = findViewById(R.id.rgemail);
        rg_password = findViewById(R.id.rgpassword);
        rg_repassword = findViewById(R.id.rgrepassword);
        rg_profileImg = findViewById(R.id.profilerg0);
        rg_signup = findViewById(R.id.signupbutton);

        loginbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(registration.this, login.class);
                startActivity(intent);
                finish();
            }
        });

        rg_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String namee = rg_username.getText().toString();
                final String emaill = rg_email.getText().toString();
                final String Password = rg_password.getText().toString();
                String cPassword = rg_repassword.getText().toString();
                final String status = "Hey I'm Using This Application";

                progressDialog.show();
                Log.d(TAG, "Starting registration for: " + emaill);

                if (TextUtils.isEmpty(namee) || TextUtils.isEmpty(emaill) ||
                        TextUtils.isEmpty(Password) || TextUtils.isEmpty(cPassword)) {
                    progressDialog.dismiss();
                    Toast.makeText(registration.this, "Please Enter Valid Information", Toast.LENGTH_SHORT).show();
                } else if (!emaill.matches(emailPattern)) {
                    progressDialog.dismiss();
                    rg_email.setError("Type A Valid Email Here");
                } else if (Password.length() < 6) {
                    progressDialog.dismiss();
                    rg_password.setError("Password Must Be 6 Characters Or More");
                } else if (!Password.equals(cPassword)) {
                    progressDialog.dismiss();
                    rg_password.setError("The Password Doesn't Match");
                } else {
                    // Tạo user trong Firebase Auth
                    auth.createUserWithEmailAndPassword(emaill, Password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        String id = task.getResult().getUser().getUid();
                                        Log.d(TAG, "Auth user created successfully, UID: " + id);

                                        DatabaseReference reference = database.getReference().child("user").child(id);
                                        StorageReference storageReference = storage.getReference().child("Upload").child(id);

                                        if (imageURI != null) {
                                            // Upload ảnh nếu có
                                            storageReference.putFile(imageURI).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                                    if (task.isSuccessful()) {
                                                        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                            @Override
                                                            public void onSuccess(Uri uri) {
                                                                imageuri = uri.toString();
                                                                createUserInDatabase(id, namee, emaill, Password, imageuri, status, reference);
                                                            }
                                                        });
                                                    } else {
                                                        progressDialog.dismiss();
                                                        Log.e(TAG, "Image upload failed: " + task.getException().getMessage());
                                                        Toast.makeText(registration.this, "Image upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                        // Vẫn tạo user nhưng với ảnh mặc định
                                                        createUserInDatabase(id, namee, emaill, Password, "https://via.placeholder.com/150", status, reference);
                                                    }
                                                }
                                            });
                                        } else {
                                            imageuri = "https://via.placeholder.com/150";
                                            createUserInDatabase(id, namee, emaill, Password, imageuri, status, reference);
                                        }
                                    } else {
                                        progressDialog.dismiss();
                                        Log.e(TAG, "Auth creation failed: " + task.getException().getMessage());
                                        Toast.makeText(registration.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        rg_profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 10);
            }
        });
    }

    /**
     * Phương thức tạo user trong Database với cấu trúc đúng
     */
    private void createUserInDatabase(String uid, String name, String email, String password,
                                      String profilePic, String status, DatabaseReference reference) {
        Log.d(TAG, "Creating user data in database for UID: " + uid);

        // Sử dụng Map để đảm bảo cấu trúc đúng
        Map<String, Object> userData = new HashMap<>();
        userData.put("userId", uid);
        userData.put("userName", name);
        userData.put("mail", email);
        userData.put("password", password);
        userData.put("profilepic", profilePic);
        userData.put("status", status);
        userData.put("role", "user");
        userData.put("banned", false);
        userData.put("lastMessage", "");

        reference.setValue(userData)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful()) {
                            Log.d(TAG, "User data created successfully in database");
                            Toast.makeText(registration.this, "Registration Successful! Please login.", Toast.LENGTH_SHORT).show();

                            // Đăng xuất để user login lại
                            FirebaseAuth.getInstance().signOut();

                            Intent intent = new Intent(registration.this, login.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Log.e(TAG, "Error creating user data: " + task.getException().getMessage());
                            Toast.makeText(registration.this,
                                    "Error in creating user profile: " + task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();

                            // Xóa user auth nếu tạo database thất bại
                            FirebaseAuth.getInstance().getCurrentUser().delete();
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10) {
            if (data != null) {
                imageURI = data.getData();
                rg_profileImg.setImageURI(imageURI);
            }
        }
    }
}