package com.example.f_food.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.f_food.R;
import com.example.f_food.entity.Food;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class HomeCustomerAdapter extends RecyclerView.Adapter<HomeCustomerAdapter.ViewHolder>{
    private List<Food> foodList;
    private FoodListAdapter.OnItemClickListener listener;

    private static Context context;

    public interface OnItemClickListener {
        void onItemClick(int foodId);
    }

    public HomeCustomerAdapter(Context context, List<Food> foodList, FoodListAdapter.OnItemClickListener listener) {
        this.context = context;
        this.foodList = foodList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HomeCustomerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new HomeCustomerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCustomerAdapter.ViewHolder holder, int position) {
        Food food = foodList.get(position);
        holder.bind(food, listener);
    }

    public void updateData(List<Food> newFoodList) {
        this.foodList.clear();
        this.foodList.addAll(newFoodList);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView productName, productDescription, productPrice, productStockStatus;
        private ImageView productImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productDescription = itemView.findViewById(R.id.productDescription);
            productPrice = itemView.findViewById(R.id.productPrice);
            productStockStatus = itemView.findViewById(R.id.productStockStatus);
            productImage = itemView.findViewById(R.id.productImage);
        }

        public void bind(final Food food, final FoodListAdapter.OnItemClickListener listener) {
            Context context = itemView.getContext();
            productName.setText(food.getName());
            productDescription.setText(food.getDescription());

            // Định dạng giá tiền
            NumberFormat formatter = NumberFormat.getInstance(new Locale("vi", "VN"));
            productPrice.setText(formatter.format(food.getPrice()) + " VNĐ");

            productStockStatus.setText(food.getStockStatus());

            if (food.getImageUrl() != null && !food.getImageUrl().isEmpty()) {
                Uri imageUri = Uri.parse(food.getImageUrl());
                // Load ảnh từ URL bằng Picasso
                Picasso.get()
                        .load(imageUri)
                        .resize(500, 500)
                        .centerCrop()
                        .into(productImage);

            } else {
                // Nếu không có ảnh, đặt ảnh mặc định
                productImage.setImageResource(R.drawable.bg_counter);
            }

            // Thay đổi màu trạng thái kho hàng
            if (Objects.equals(food.getStockStatus(), "Available")) {
                productStockStatus.setTextColor(Color.parseColor("#006400")); // Xanh đậm
            } else {
                productStockStatus.setTextColor(Color.RED);
            }
            itemView.setOnClickListener(v -> {

                    listener.onItemClick(food.getFoodId());

            });
        }
    }
}
