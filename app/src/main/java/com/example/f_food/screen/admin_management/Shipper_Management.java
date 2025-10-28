package com.example.f_food.screen.admin_management;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.f_food.adapter.ShipperManagementAdapter;
import com.example.f_food.entity.Shipper;
import com.example.f_food.R;
import com.example.f_food.entity.User;
import com.example.f_food.repository.ShipperRepository;
import com.example.f_food.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class Shipper_Management extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ShipperManagementAdapter shipperManagementAdapter;
    private ShipperRepository shipperRepository;
    private UserRepository userRepository;
    private List<Shipper> shipperList;
    private ImageView back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shipper_management);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_ShipperManagement), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize repositories
        shipperRepository = new ShipperRepository(this);
        userRepository = new UserRepository(this);

        // Fetch shipper list from SQLite
        shipperList = shipperRepository.getAllShippers();
        if (shipperList == null) {
            shipperList = new ArrayList<>(); // Ensure list is never null
        }

        // Back button setup


        // Search functionality
        EditText etSearchShipper = findViewById(R.id.etSearchShipper);
        etSearchShipper.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                // No action needed
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                filterShipperList(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // No action needed
            }
        });



        // RecyclerView setup
        recyclerView = findViewById(R.id.recyclerViewShipperManagement);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize adapter and set it to RecyclerView
        shipperManagementAdapter = new ShipperManagementAdapter(this, shipperList);
        recyclerView.setAdapter(shipperManagementAdapter);

        // Check if shipper list is empty
        if (shipperList.isEmpty()) {
            Toast.makeText(this, "No shippers available", Toast.LENGTH_SHORT).show();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Shipper Management");
            actionBar.setDisplayHomeAsUpEnabled(true); // Hiện nút quay lại
        }
    }
    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(Shipper_Management.this, AdminScreen.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    private void filterShipperList(String query) {
        List<Shipper> filteredList = new ArrayList<>();

        if (query.isEmpty()) {
            // If query is empty, show all shippers
            filteredList.addAll(shipperList);
        } else {
            // Filter shippers based on the query
            for (Shipper shipper : shipperList) {
                User user = userRepository.getUserById(shipper.getUserId());
                if (user != null && user.getFullName().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(shipper);
                }
            }
        }

        // Update the adapter with the filtered list
        shipperManagementAdapter.updateShipperList(filteredList);
    }
}