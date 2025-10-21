package com.example.f_food;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.f_food.repository.*;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //hhi
    OrderRepository orderRepository = new OrderRepository(this);
    UserRepository userRepository = new UserRepository(this);
    OrderDetailRepository orderDetailRepository = new OrderDetailRepository(this);
    CategoryRepository categoryRepository = new CategoryRepository(this);
    AddressRepository addressRepository = new AddressRepository(this);
    FoodRepository foodRepository = new FoodRepository(this);
    RestaurantRepository restaurantRepository = new RestaurantRepository(this);
    ShipperRepository shipperRepository = new ShipperRepository(this);
    PaymentRepository paymentRepository = new PaymentRepository(this);
    PolicyRepository policyRepository = new PolicyRepository(this);
    ReviewRepository repository=new ReviewRepository(this);


    }
}