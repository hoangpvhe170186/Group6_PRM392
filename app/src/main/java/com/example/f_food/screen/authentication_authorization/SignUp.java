package com.example.f_food.screen.authentication_authorization;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.f_food.entity.User;
import com.example.f_food.R;
import com.example.f_food.repository.UserRepository;
import com.squareup.picasso.Picasso;

import java.util.Random;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {
    private EditText fullName, email, phone, password, confirmPassword;
    private Button btnSignUp;
    private UserRepository userRepository;
    private Button Signin;
    ImageView imgLogoSignup;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.signup), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        fullName = findViewById(R.id.txtSignup_fullname);
        email = findViewById(R.id.txtSignup_Email);
        phone = findViewById(R.id.txtSignup_Phone);
        password = findViewById(R.id.txtSignup_Password);
        confirmPassword = findViewById(R.id.txtSignup_ConfirmPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        Signin = findViewById(R.id.btn_signup_login);
        imgLogoSignup = findViewById(R.id.imgLogoSignup);
        Picasso.get()
                .load(R.drawable.login)
                .resize(500, 500)
                .centerCrop()
                .into(imgLogoSignup);

        Signin.setOnClickListener(v -> {
            Intent intent = new Intent(SignUp.this, LoginActivity.class);
            startActivity(intent);
        });

        btnSignUp.setOnClickListener(v -> {
            String name = fullName.getText().toString().trim();
            String emailText = email.getText().toString().trim();
            String phoneText = phone.getText().toString().trim();
            String pass = password.getText().toString().trim();
            String confirmPass = confirmPassword.getText().toString().trim();
            userRepository = new UserRepository(this);

            // Validate Full Name
            if (name.isEmpty() || !isValidName(name)) {
                Toast.makeText(this, "Full name must be 2-50 characters long and contain only letters, spaces, or diacritics", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validate Phone
            if (phoneText.isEmpty() || !isValidPhone(phoneText)) {
                Toast.makeText(this, "Phone must start with 0, be exactly 10 digits, and contain no special characters", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validate Email
            if (emailText.isEmpty() || !isValidEmail(emailText)) {
                Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validate Password
            if (pass.isEmpty() || !isValidPassword(pass)) {
                Toast.makeText(this, "Password must be at least 8 characters long, with uppercase, lowercase, numbers, and special characters", Toast.LENGTH_SHORT).show();
                return;
            }

            // Check if passwords match
            if (!pass.equals(confirmPass)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            // Check for existing email or phone
            if (userRepository.getUserByEmail(emailText) != null) {
                Toast.makeText(this, "Email already exists", Toast.LENGTH_SHORT).show();
                return;
            }

            if (userRepository.getUserByPhone(phoneText) != null) {
                Toast.makeText(this, "Phone number already exists", Toast.LENGTH_SHORT).show();
                return;
            }

            String verifyCode = generateVerifyCode(6);
            User newUser = new User(0, name, emailText, pass, phoneText, "Customer", "2024-03-14 12:00:00", "2024-03-14 12:00:00", false, verifyCode);
            userRepository.insert(newUser);

            new GMailSender(emailText, "Verify Your Account", "Welcome, " + name + "!\n\nYour verification code is: " + verifyCode + "\n\nPlease enter this code to activate your account.", new GMailSender.SendMailCallback() {
                @Override
                public void onSuccess() {
                    runOnUiThread(() -> {
                        Toast.makeText(SignUp.this, "Verification code sent to your email.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUp.this, VerifyCodeActivity.class);
                        intent.putExtra("email", emailText);
                        startActivity(intent);
                        finish();
                    });
                }

                @Override
                public void onFailure(Exception e) {
                    runOnUiThread(() -> {
                        Toast.makeText(SignUp.this, "Failed to send verification code: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                }
            }).execute();
        });
    }

    private String generateVerifyCode(int length) {
        String chars = "0123456789";
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            code.append(chars.charAt(random.nextInt(chars.length())));
        }
        return code.toString();
    }

    private boolean isValidName(String name) {
        if (name.length() < 2 || name.length() > 50) return false;
        // Allow letters, spaces, and Vietnamese diacritics
        return Pattern.matches("^[\\p{L} ]+$", name);
    }

    private boolean isValidPhone(String phone) {
        if (phone.length() != 10 || !phone.startsWith("0")) return false;
        return Pattern.matches("^[0-9]+$", phone);
    }

    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                (email.endsWith(".com") || email.endsWith(".edu.vn") || email.endsWith(".org"));
    }

    private boolean isValidPassword(String password) {
        // At least 8 characters, with uppercase, lowercase, digits, and special characters
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&%*])(?=\\S+$).{8,}$";
        return Pattern.matches(passwordPattern, password);
    }
}