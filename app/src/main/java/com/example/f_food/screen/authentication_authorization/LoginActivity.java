package com.example.f_food.screen.authentication_authorization;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.f_food.R;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);  // dùng layout của bạn

        EditText etEmail = findViewById(R.id.etEmail);
        EditText etPassword = findViewById(R.id.etPassword);
        Button btnLogin = findViewById(R.id.btnLogin);

        // Demo: bấm nút chỉ hiện Toast (không gọi repo, không điều hướng)
        btnLogin.setOnClickListener(v ->
                Toast.makeText(this, "Demo UI: Đã bấm Đăng nhập", Toast.LENGTH_SHORT).show()
        );
    }
}
