package com.example.f_food.repository;

import android.content.Context;

import com.example.f_food.dao.RestaurantDAO;
import com.example.f_food.dao.RestaurantRoomDatabase;
import com.example.f_food.entity.Restaurant;

import java.util.Arrays;
import java.util.List;

public class RestaurantRepository {
    private RestaurantDAO restaurantDAO;

    public RestaurantRepository(Context context) {
        RestaurantRoomDatabase db = RestaurantRoomDatabase.getInstance(context);
        restaurantDAO = db.restaurantDAO();

        // Kiểm tra nếu chưa có dữ liệu, thì thêm dữ liệu mẫu
        if (restaurantDAO.getAllRestaurants().isEmpty()) {
            insertSampleData();
        }
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantDAO.getAllRestaurants();
    }

    public Restaurant getRestaurantById(int id) {
        return restaurantDAO.getRestaurantById(id);
    }
    public Restaurant getRestaurantByUserId(int id) {
        return restaurantDAO.getRestaurantByUserId(id);
    }

    public void deleteById(int id) {
        restaurantDAO.deleteById(id);
    }

    public void deleteAll() {
        restaurantDAO.deleteAll();
    }

    public void insert(Restaurant restaurant) {
        restaurantDAO.insert(restaurant);
    }

    public void insertAll(List<Restaurant> listRestaurant) {
        restaurantDAO.insertAll(listRestaurant);
    }


    public void update(Restaurant restaurant) {
        restaurantDAO.update(restaurant);
    }

    private void insertSampleData() {
        List<Restaurant> sampleRestaurants = Arrays.asList(
//                new Restaurant(1, "Huy Nguyn", "Ha Noi", "012345", "Open", "https://images.unsplash.com/photo-1517248135467-4c7edcad34c4?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8cmVzdGF1cmFudHxlbnwwfHwwfHx8MA%3D%3D"),
//                new Restaurant(2, "Tung Ngun", "Ha Noi", "012345", "Open", "https://t3.ftcdn.net/jpg/03/24/73/92/360_F_324739203_keeq8udvv0P2h1MLYJ0GLSlTBagoXS48.jpg"),
//                new Restaurant(3, "Bach Nguyn", "Ha Noi", "012345", "Open", "https://images.wsj.net/im-65599456?size=1.5"),
//                new Restaurant(4, "Bình Chuẩn Bilast", "Ha Noi", "012345", "Open", "https://images.squarespace-cdn.com/content/v1/61d2ccabbc553c1fec7c16e9/b43cd3de-f28e-4763-93cb-44b066748bca/DSC_8711.jpg"),
                new Restaurant(1, "Pizza 4P's", "Ha Noi", "0919006043", "Open", "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/18/5e/5d/71/photo4jpg.jpg?w=1100&h=600&s=1"),
                new Restaurant(2, "Vinci Pizza & Grill", "Ha Noi", "09969756304", "Open", "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/18/af/e8/ac/interiors.jpg?w=1000&h=600&s=1"),
                new Restaurant(3, "Pizza Belga", "Ha Noi", "0937308883", "Open", "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/25/5c/16/db/pizza-belga.jpg?w=1100&h=600&s=1")
        );

        for (Restaurant restaurant : sampleRestaurants) {
            restaurantDAO.insert(restaurant);
        }

    }
}
