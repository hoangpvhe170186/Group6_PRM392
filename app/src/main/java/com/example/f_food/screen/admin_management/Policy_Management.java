package com.example.f_food.screen.admin_management;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.f_food.adapter.PolicyManagementAdapter;
import com.example.f_food.entity.Policy;
import com.example.f_food.R;
import com.example.f_food.repository.PolicyRepository;

import java.util.List;

public class Policy_Management extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Policy> policyList;
    private PolicyRepository policyRepository;
    private PolicyManagementAdapter policyAdapter;
    private Button Create_policy;
    private ImageView back;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_policy_management);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Policy_activity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Create_policy=findViewById(R.id.btnAddPolicy);
        Create_policy.setOnClickListener(v->{
            Intent intent = new Intent(Policy_Management.this, AddPolicy.class);
            startActivity(intent);
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Policy Management");
            actionBar.setDisplayHomeAsUpEnabled(true); // Hiện nút quay lại
        }

        recyclerView = findViewById(R.id.recyclerViewPolicies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Init();
        policyAdapter = new PolicyManagementAdapter(policyList,this);
        recyclerView.setAdapter(policyAdapter);
    }
    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(Policy_Management.this, AdminScreen.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    public void Init(){
        policyRepository= new PolicyRepository(this);
        policyList=policyRepository.getAllPolicies();
    }
}