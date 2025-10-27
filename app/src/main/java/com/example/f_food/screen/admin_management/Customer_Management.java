package com.example.f_food.screen.admin_management;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
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

import com.example.f_food.adapter.CustomerManagementAdapter;
import com.example.f_food.entity.User;
import com.example.f_food.R;
import com.example.f_food.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class Customer_Management extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CustomerManagementAdapter adapter;
    private List<User> customerList;
    private List<User> filteredList;
    private UserRepository userRepository;
    private EditText etSearchCustomer;
    private Button btnSearchCustomer;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_management);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.customer_management_activity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        etSearchCustomer = findViewById(R.id.etSearchCustomer);
        btnSearchCustomer = findViewById(R.id.btnSearchCustomer);


        recyclerView = findViewById(R.id.recyclerViewCustomerManagement);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Khởi tạo repository và lấy dữ liệu
        userRepository = new UserRepository(this);
        customerList = userRepository.getAllCustomer();

        if (customerList == null) {
            customerList = new ArrayList<>();
        }

        // Ban đầu danh sách hiển thị toàn bộ khách hàng
        filteredList = new ArrayList<>(customerList);

        // Set adapter với danh sách lọc
        adapter = new CustomerManagementAdapter(this, filteredList);
        recyclerView.setAdapter(adapter);

        // Thêm sự kiện cho ô tìm kiếm
        btnSearchCustomer.setOnClickListener(v -> filterCustomers());
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Customer Management");
            actionBar.setDisplayHomeAsUpEnabled(true); // Hiện nút quay lại
        }
        // Tìm kiếm tự động khi nhập vào ô tìm kiếm
        etSearchCustomer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterCustomers();
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }
    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(Customer_Management.this, AdminScreen.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    private void filterCustomers() {
        String query = etSearchCustomer.getText().toString().trim().toLowerCase();
        filteredList.clear();

        if (query.isEmpty()) {
            filteredList.addAll(customerList);
        } else {
            for (User user : customerList) {
                if (user.getFullName().toLowerCase().contains(query)) {
                    filteredList.add(user);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }
}
