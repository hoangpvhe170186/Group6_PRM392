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
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.f_food.adapter.RestaurantManagementListAdapter;
import com.example.f_food.entity.Restaurant;
import com.example.f_food.R;
import com.example.f_food.repository.RestaurantRepository;
import java.util.ArrayList;
import java.util.List;

public class Restaurant_Management extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RestaurantManagementListAdapter adapter;
    private List<Restaurant> restaurantList;
    private RestaurantRepository restaurantRepository;
    private ImageView back;
    private EditText searchInput;
    private Button searchButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_restaurant_management);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.restaurantManagementActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        recyclerView = findViewById(R.id.recyclerViewListRestaurant);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Restaurant Management");
            actionBar.setDisplayHomeAsUpEnabled(true); // Hiện nút quay lại
        }
        // Initialize repository and data
        restaurantRepository = new RestaurantRepository(this);
        init();
        searchInput = findViewById(R.id.etNameRestaurant);
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchRestaurant(s.toString()); // Gọi hàm tìm kiếm khi nhập văn bản
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });



        // Set adapter with item click listener
        adapter = new RestaurantManagementListAdapter(restaurantList, this::updateRestaurantStatus);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(Restaurant_Management.this, AdminScreen.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        restaurantList = restaurantRepository.getAllRestaurants();

        if (restaurantList == null) {
            restaurantList = new ArrayList<>(); // Prevent NullPointerException
        }
    }

    private void searchRestaurant(String query) {
        List<Restaurant> filteredList = new ArrayList<>();
        for (Restaurant restaurant : restaurantList) {
            if (restaurant.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(restaurant);
            }
        }
        adapter.updateList(filteredList);
    }


    private void updateRestaurantStatus(int position) {
        Restaurant restaurant = restaurantList.get(position);

        // Create an AlertDialog to confirm the status change
        new AlertDialog.Builder(this)
                .setTitle("Change Status")
                .setMessage("Are you sure you want to change the status of this restaurant?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    // If user clicks Yes, update the restaurant status
                    if (restaurant.getStatus().equals("Open")) {
                        restaurant.setStatus("Close");
                    } else {
                        restaurant.setStatus("Open");
                    }

                    // Update status in the database (if applicable)
                    restaurantRepository.update(restaurant);

                    // Notify the adapter that the item has been changed
                    adapter.notifyItemChanged(position);

                    // Show a success message
                    Toast.makeText(Restaurant_Management.this, "Status updated successfully", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("No", (dialog, which) -> {
                    // If user clicks No, do nothing (dialog will close)
                    dialog.dismiss();
                })
                .show();
    }

}
