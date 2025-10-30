package com.example.f_food.screen.admin_management;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
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

public class Update_Policy extends AppCompatActivity {

    private EditText etTitle, etDescription;
    private Button btnSave;
    private PolicyRepository policyRepository;
    private Policy currentPolicy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_policy);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.update_policy), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        policyRepository = new PolicyRepository(this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Update Policy");
            actionBar.setDisplayHomeAsUpEnabled(true); // Hiện nút quay lại
        }

        etTitle = findViewById(R.id.etPolicyTitle);
        etDescription = findViewById(R.id.etPolicyDescription);
        btnSave = findViewById(R.id.btnupdate_policy);

        // Nhận ID policy từ Intent
        // Thay đổi từ String sang int
        int policyId = getIntent().getIntExtra("POLICY_ID", -1);
        currentPolicy = policyRepository.getPolicyById(policyId);
        if (currentPolicy != null) {
            etTitle.setText(currentPolicy.getTitle());
            etDescription.setText(currentPolicy.getDescription());
        }

        btnSave.setOnClickListener(v -> updatePolicy());
    }
    private void updatePolicy() {
        String newTitle = etTitle.getText().toString();
        String newDescription = etDescription.getText().toString();

        if (newTitle.isEmpty() || newDescription.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        currentPolicy.setTitle(newTitle);
        currentPolicy.setDescription(newDescription);

        policyRepository.update(currentPolicy);
        Toast.makeText(this, "Policy updated", Toast.LENGTH_SHORT).show();
        finish();
    }
    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(Update_Policy.this, Policy_Management.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}