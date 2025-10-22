package com.example.f_food.screen.authentication_authorization;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.f_food.entity.User;
import com.example.f_food.R;
import com.example.f_food.repository.AddressRepository;
import com.example.f_food.repository.UserRepository;
import com.example.f_food.screen.admin_management.AdminScreen;
import com.example.f_food.screen.features_customer.HomeStart;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin, btnLoginForPartner, btnLoginForShipper, btnRegister;
    private TextView tvForgotPassword;
    private UserRepository userRepository;
    private AddressRepository addressRepository;
    private ImageView imgLogoLogin, ivTogglePassword;
    private CheckBox cbRememberMe;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize UI components
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLoginForPartner = findViewById(R.id.btnLoginForPartner);
        btnLoginForShipper = findViewById(R.id.btnLoginForShipper);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        imgLogoLogin = findViewById(R.id.imgLogoLogin);
        btnRegister = findViewById(R.id.btnRegister);
        cbRememberMe = findViewById(R.id.cbRememberMe);
        ivTogglePassword = findViewById(R.id.ivTogglePassword);

        // Load logo with Picasso
        Picasso.get()
                .load(R.drawable.login)
                .resize(500, 500)
                .centerCrop()
                .into(imgLogoLogin);

        // Initialize repositories
        userRepository = new UserRepository(this);
        addressRepository = new AddressRepository(this);

        // Login button click listener
        btnLogin.setOnClickListener(v -> handleLogin());

        // Partner and Shipper login buttons
        btnLoginForPartner.setOnClickListener(v -> navigateToRestaurantLogIn());
        btnLoginForShipper.setOnClickListener(v -> navigateToShipperLogIn());

        // Register button
        btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUp.class);
            startActivity(intent);
        });

        // Forgot password
        tvForgotPassword.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, ForgotPassActivity.class);
            startActivity(intent);
        });

        // Toggle password visibility
        ivTogglePassword.setOnClickListener(v -> {
            isPasswordVisible = !isPasswordVisible;
            if (isPasswordVisible) {
                etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                ivTogglePassword.setImageResource(R.drawable.icon_eye_open); // Ensure this drawable exists
            } else {
                etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                ivTogglePassword.setImageResource(R.drawable.icon_eye_close); // Ensure this drawable exists
            }
            etPassword.setSelection(etPassword.getText().length()); // Move cursor to end
        });
    }

    private void handleLogin() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            showAlertDialog("Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        List<User> users = userRepository.getAllUsers();

        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("userId", user.getUserId());

                if (cbRememberMe.isChecked()) {
                    editor.putString("email", email);
                    editor.putString("password", password);
                } else {
                    editor.remove("email");
                    editor.remove("password");
                }
                editor.apply();

                Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();

                Intent intent;
                if ("Customer".equals(user.getUserType())) {
                    intent = new Intent(this, HomeStart.class);
                    intent.putExtra("fullName", user.getFullName());
                } else if ("Admin".equals(user.getUserType())) {
                    intent = new Intent(this, AdminScreen.class);
                } else {
                    showAlertDialog("Email hoặc mật khẩu không đúng!");
                    return;
                }
                startActivity(intent);
                finish();
                return;
            }
        }

        showAlertDialog("Email hoặc mật khẩu không đúng!");
    }

    private void showAlertDialog(String message) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }

    private void navigateToRestaurantLogIn() {
        Intent intent = new Intent(this, RestaurantLogIn.class);
        startActivity(intent);
    }

    private void navigateToShipperLogIn() {
        Intent intent = new Intent(this, ShipperLogin.class);
        startActivity(intent);
    }
}