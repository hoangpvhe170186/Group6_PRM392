package com.example.f_food.screen.admin_management;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.f_food.R;
import com.example.f_food.entity.Policy;
import com.example.f_food.repository.PolicyRepository;

import java.time.LocalDateTime;
import java.util.Date;

public class AddPolicy extends AppCompatActivity {

    private EditText etTitle, etDescription;
    private Button btnSavePolicy;
    private PolicyRepository policyRepository;
    private  Button btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_policy);

        // Áp dụng padding cho hệ thống thanh điều hướng (navigation bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.addpolicy), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Add Policy");
            actionBar.setDisplayHomeAsUpEnabled(true); // Hiện nút quay lại
        }
        // Khởi tạo các view
        etTitle = findViewById(R.id.txtTitle_policy);
        etDescription = findViewById(R.id.txtDescription_policy);
        btnSavePolicy = findViewById(R.id.btnSavePolicy);
        btnSavePolicy.setOnClickListener(view -> {
            try {
                String Title = etTitle.getText().toString().trim();
                String Description = etDescription.getText().toString().trim();
                String createdAt = Policy.getCurrentTimeAsString();
                Policy p = new Policy(Title, Description, createdAt);
                policyRepository = new PolicyRepository(this); // Nếu PolicyRepository cần Context
                policyRepository.insert(p);
                Toast.makeText(this, "Policy Created successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddPolicy.this, Policy_Management.class);
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Error creating policy: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(AddPolicy.this, Policy_Management.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}