package com.example.f_food.screen.order_processing;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.f_food.R;
import com.example.f_food.adapter.FoodAcceptShippingAdapter;
import com.example.f_food.entity.OrderDetail;
import com.example.f_food.repository.FoodRepository;
import com.example.f_food.repository.OrderDetailRepository;
import com.example.f_food.repository.OrderRepository;
import com.example.f_food.repository.ShipperRepository;
import com.example.f_food.screen.features_customer.GoogleMaps;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class AcceptShippingOrder extends AppCompatActivity {
    private TextView tvOrderId, tvRestaurantAddress, tvDeliveryAddress, tvDeliveryTime, tvCost, DeliCost,OrderCost;
    private RecyclerView foodRecyclerView;
    private Button acceptButton;
    private BottomNavigationView bottomNavigationView;

    private double distance;
    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;
    private FoodRepository foodRepository;

    private int shipperId = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_shipping_order);

        ShipperRepository shipperRepository = new ShipperRepository(this);
        shipperId = shipperRepository.getShipperByUserId(getLoggedInUserId()).getShipperId();

        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        String userPhone = intent.getStringExtra("userPhone");
        String userEmail = intent.getStringExtra("email");
        String userPassword = intent.getStringExtra("password");

        orderRepository = new OrderRepository(this);
        orderDetailRepository = new OrderDetailRepository(this);
        foodRepository = new FoodRepository(this);

        tvOrderId = findViewById(R.id.orderId);
        tvRestaurantAddress = findViewById(R.id.restaurantAddress);
        tvDeliveryAddress = findViewById(R.id.deliveryAddress);
        tvDeliveryTime = findViewById(R.id.deliveryTime);
        tvCost = findViewById(R.id.foodCost);
        foodRecyclerView = findViewById(R.id.foodListAcceptShipping);
        acceptButton = findViewById(R.id.acceptButton);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        DeliCost = findViewById(R.id.DeliCost);

        foodRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        int orderId = getIntent().getIntExtra("orderId", -1);
        String restaurantAddress = getIntent().getStringExtra("restaurantAddress");
        String deliveryAddress = getIntent().getStringExtra("deliveryAddress");
        String deliveryTime = getIntent().getStringExtra("deliveryTime");
        double totalCost = orderRepository.getTotalPriceByOrderId(orderId);

        calculateDistanceAndDisplay(restaurantAddress, deliveryAddress, distanceKm -> {
            distance = distanceKm; // gán vào biến của class
            Log.d("DISTANCE_LOG", "Khoảng cách là: " + distanceKm + " km");

            // Cập nhật UI sau khi có khoảng cách
            tvDeliveryAddress.setText("Delivery Address: " + deliveryAddress + " - " + String.format("%.1f km", distance));
            DeliCost.setText("Phí ship: " + String.format("%.0f", distance * 10000) + " VND");
        });


        List<OrderDetail> orderDetails = orderDetailRepository.getOrderDetailsByOrderId(orderId);
        if (orderDetails != null && !orderDetails.isEmpty()) {
            FoodAcceptShippingAdapter foodAdapter = new FoodAcceptShippingAdapter(this, orderDetails);
            foodRecyclerView.setAdapter(foodAdapter);
        }


        // Nút Accept
        acceptButton.setOnClickListener(v -> showConfirmationDialog(orderId, shipperId));
        ImageButton btnOpenMap = findViewById(R.id.btnOpenMap);
        btnOpenMap.setOnClickListener(v -> openMap(restaurantAddress, deliveryAddress));


        tvOrderId.setText("Order ID: " + orderId);
        tvRestaurantAddress.setText("Restaurant: " + restaurantAddress);
        tvDeliveryTime.setText("Delivery Time: " + deliveryTime);
        tvCost.setText("Tiền đồ ăn: " + totalCost + " VND");



        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            Intent navIntent = new Intent();

            if (itemId == R.id.nav_home) {
                navIntent.setClass(AcceptShippingOrder.this, PendingOrder.class);
            } else if (itemId == R.id.nav_orders || itemId == R.id.nav_delivery) {
                navIntent.setClass(AcceptShippingOrder.this, DeliveryHistory.class);
            }

            navIntent.putExtra("email", userEmail);
            navIntent.putExtra("password", userPassword);
            navIntent.putExtra("userName", userName);
            navIntent.putExtra("userPhone", userPhone);
            startActivity(navIntent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            return true;
        });
    }
    public interface DistanceCallback {
        void onDistanceCalculated(double distance);
    }

    private void calculateDistanceAndDisplay(String resAddress, String deliveryAddress, DistanceCallback callback) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        new Thread(() -> {
            try {
                List<Address> startList = geocoder.getFromLocationName(resAddress, 1);
                List<Address> endList = geocoder.getFromLocationName(deliveryAddress, 1);

                if (!startList.isEmpty() && !endList.isEmpty()) {
                    Address start = startList.get(0);
                    Address end = endList.get(0);

                    float[] result = new float[1];
                    android.location.Location.distanceBetween(
                            start.getLatitude(), start.getLongitude(),
                            end.getLatitude(), end.getLongitude(),
                            result
                    );

                    double calculatedDistance = result[0] / 1000f;

                    runOnUiThread(() -> {
                        tvDeliveryAddress.setText("Delivery Address: " + deliveryAddress + " - " + String.format("%.1f km", calculatedDistance));
                        callback.onDistanceCalculated(calculatedDistance); // Gọi callback
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }


    private void openMap(String resAddress, String deliveryAddress) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> startList = geocoder.getFromLocationName(resAddress, 1);
            List<Address> endList = geocoder.getFromLocationName(deliveryAddress, 1);
            if (!startList.isEmpty() && !endList.isEmpty()) {
                Address start = startList.get(0);
                Address end = endList.get(0);

                Intent intentMap = new Intent(this, GoogleMaps.class);
                intentMap.putExtra("origin_lat", start.getLatitude());
                intentMap.putExtra("origin_lng", start.getLongitude());
                intentMap.putExtra("dest_lat", end.getLatitude());
                intentMap.putExtra("dest_lng", end.getLongitude());
                intentMap.putExtra("address", end.getAddressLine(0));
                startActivity(intentMap);
            } else {
                Toast.makeText(this, "Không tìm thấy một trong hai vị trí!", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Lỗi khi tìm địa chỉ", Toast.LENGTH_SHORT).show();
        }
    }

    private void showConfirmationDialog(int orderId, int shipperId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận đơn hàng");
        builder.setMessage("Bạn có chắc chắn muốn chấp nhận đơn hàng #" + orderId + " không?");
        builder.setPositiveButton("Chấp nhận", (dialog, which) -> {
            orderRepository.updateOrderStatus(orderId, "Delivering", shipperId);
            Toast.makeText(this, "Đơn hàng #" + orderId + " đã chuyển sang trạng thái 'Delivering'!", Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());
        builder.show();
    }


    private int getLoggedInUserId() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getInt("userId", -1);
    }
}

