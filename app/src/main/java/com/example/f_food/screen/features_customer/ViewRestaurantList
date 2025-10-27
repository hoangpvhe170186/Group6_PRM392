package com.example.f_food.screen.features_customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.f_food.adapter.RestaurantListAdapter;
import com.example.f_food.entity.Restaurant;
import com.example.f_food.R;
import com.example.f_food.repository.RestaurantRepository;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class ViewRestaurantList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RestaurantListAdapter adapter;
    private List<Restaurant> restaurantList;

    private RestaurantRepository restaurantRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_restaurant_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.viewRestaurantList), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Ẩn thanh điều hướng (Navigation Bar)
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
        recyclerView = findViewById(R.id.recyclerViewListRestaurant);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        init();
        adapter = new RestaurantListAdapter(restaurantList, new RestaurantListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int id) {
                // Xử lý sự kiện click: Chuyển sang Activity mới
                Intent intent = new Intent(ViewRestaurantList.this, ProductListRestaurant.class);
                intent.putExtra("restaurantId", id);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            Intent intent1 = null;

            if (itemId == R.id.nav_home) {
                intent1 = new Intent(this, HomeStart.class); // Màn hình Trang chủ
            } else if (itemId == R.id.nav_bag) {
                intent1 = new Intent(this, OrderHistory.class); // Màn hình Đơn hàng
            } else if (itemId == R.id.nav_orders) {
                intent1 = new Intent(this, ViewRestaurantList.class); // Màn hình Nhà hàng
            } else if (itemId == R.id.nav_profile) {
                intent1 = new Intent(this, activity_cart.class); // Màn hình Thông báo
            }
            if (intent1 != null) {
                startActivity(intent1);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out); // Hiệu ứng chuyển màn
            }
            return true;
        });
    }

    private void init() {
        restaurantRepository = new RestaurantRepository(this);
        restaurantList = restaurantRepository.getAllRestaurants();
    }

}