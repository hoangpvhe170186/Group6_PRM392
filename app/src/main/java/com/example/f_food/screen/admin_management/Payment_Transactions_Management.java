package com.example.f_food.screen.admin_management;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.f_food.adapter.PaymentManagementAdapter;
import com.example.f_food.entity.Payment;
import com.example.f_food.R;
import com.example.f_food.repository.OrderRepository;
import com.example.f_food.repository.PaymentRepository;
import com.example.f_food.repository.UserRepository;

import java.util.List;

public class Payment_Transactions_Management extends AppCompatActivity {

    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment_transactions_management);

        // Initialize OrderRepository and UserRepository (if needed for the full name)
        OrderRepository orderRepository = new OrderRepository(this);
        UserRepository userRepository = new UserRepository(this);  // Assuming you also have a UserRepository

        // Initialize PaymentRepository and retrieve all payments
        PaymentRepository paymentRepository = new PaymentRepository(this);
        List<Payment> paymentList = paymentRepository.getAllPayments();
        back = findViewById(R.id.btnBack_Transactions_Management);
        back.setOnClickListener(v -> {
            Intent intent = new Intent(Payment_Transactions_Management.this, AdminScreen.class);
            startActivity(intent);
            finish(); // Để đóng màn hiện tại nếu không cần quay lại
        });

        // Initialize RecyclerView
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        RecyclerView recyclerView = findViewById(R.id.transaction_management_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the adapter with payment list and repositories
        PaymentManagementAdapter adapter = new PaymentManagementAdapter(paymentList, orderRepository, userRepository);
        recyclerView.setAdapter(adapter);

        // Apply window insets for edge-to-edge padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
