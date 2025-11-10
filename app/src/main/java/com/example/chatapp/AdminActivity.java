package com.example.chatapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {

    private RecyclerView rv;
    private UsersAdapter adapter;
    private DatabaseReference userRef;
    private final List<Users> data = new ArrayList<>();
    private String currentUserId; // Thêm biến lưu ID của admin hiện tại

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // Lấy ID của admin đang đăng nhập
        currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        rv = findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UsersAdapter(this, data, currentUserId); // Truyền currentUserId vào adapter
        rv.setAdapter(adapter);

        userRef = Db.get().getReference("user");
        loadUsers();
    }

    private void loadUsers() {
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                for (DataSnapshot s : snapshot.getChildren()) {
                    Users u = s.getValue(Users.class);
                    if (u != null) {
                        if (u.getUid() == null) {
                            u.setUid(s.getKey());
                        }
                        data.add(u);
                    }
                }
                adapter.notifyDataSetChanged();

                // Hiển thị số lượng user
                Toast.makeText(AdminActivity.this, "Loaded " + data.size() + " users", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AdminActivity.this, "Lỗi tải dữ liệu: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void toggleBan(Users u) {
        if (u.getUid() == null) {
            Toast.makeText(this, "Lỗi: User không có UID", Toast.LENGTH_SHORT).show();
            return;
        }

        // KIỂM TRA: Không cho phép admin tự ban chính mình
        if (u.getUid().equals(currentUserId)) {
            Toast.makeText(this, "Bạn không thể tự ban chính mình!", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean newBan = !(u.getBanned() != null && u.getBanned());
        userRef.child(u.getUid()).child("banned").setValue(newBan)
                .addOnSuccessListener(v -> {
                    Toast.makeText(this, newBan ? "Đã ban" : "Đã bỏ ban", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.VH> {
        private final Context ctx;
        private final List<Users> list;
        private final String currentUserId; // Lưu ID của admin hiện tại

        UsersAdapter(Context c, List<Users> l, String currentUserId) {
            this.ctx = c;
            this.list = l;
            this.currentUserId = currentUserId;
        }

        class VH extends RecyclerView.ViewHolder {
            TextView tvName, tvEmail, tvRole, tvBanned, tvAvatar, tvLastLogin;
            Button btnBan;

            VH(@NonNull View v) {
                super(v);
                tvName = v.findViewById(R.id.tvName);
                tvEmail = v.findViewById(R.id.tvEmail);
                tvRole = v.findViewById(R.id.tvRole);
                tvBanned = v.findViewById(R.id.tvBanned);
                btnBan = v.findViewById(R.id.btnBan);
                tvAvatar = v.findViewById(R.id.tvAvatar);
                tvLastLogin = v.findViewById(R.id.tvLastLogin);
            }
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(ctx).inflate(R.layout.item_user_admin, parent, false);
            return new VH(v);
        }

        @Override
        public void onBindViewHolder(@NonNull VH h, int position) {
            Users u = list.get(position);

            // Set user info
            h.tvName.setText(u.getName() != null ? u.getName() : "No Name");
            h.tvEmail.setText(u.getEmail() != null ? u.getEmail() : "No Email");

            // Set avatar initial
            if (u.getName() != null && !u.getName().isEmpty()) {
                h.tvAvatar.setText(String.valueOf(u.getName().charAt(0)).toUpperCase());
            } else {
                h.tvAvatar.setText("U");
            }

            // Set role
            String role = u.getRole() != null ? u.getRole() : "user";
            h.tvRole.setText(role);
            if ("admin".equals(role)) {
                h.tvRole.setBackgroundResource(R.drawable.bg_role_admin);
            } else {
                h.tvRole.setBackgroundResource(R.drawable.bg_role_user);
            }

            // Set banned status
            boolean isBanned = u.getBanned() != null && u.getBanned();
            h.tvBanned.setText(isBanned ? "BANNED" : "ACTIVE");
            h.tvBanned.setBackgroundResource(isBanned ?
                    R.drawable.bg_status_banned : R.drawable.bg_status_active);

            // KIỂM TRA: Nếu là admin hiện tại thì disable nút Ban
            boolean isCurrentUser = u.getUid().equals(currentUserId);

            if (isCurrentUser) {
                // Ẩn nút Ban cho chính admin
                h.btnBan.setVisibility(View.GONE);
                // Hoặc có thể hiển thị nhưng disable
                // h.btnBan.setEnabled(false);
                // h.btnBan.setText("Current User");
                // h.btnBan.setBackgroundColor(ctx.getResources().getColor(R.color.gray_500));
            } else {
                h.btnBan.setVisibility(View.VISIBLE);
                h.btnBan.setEnabled(true);

                // Set button text and style
                h.btnBan.setText(isBanned ? "Unban" : "Ban");
                if (isBanned) {
                    h.btnBan.setBackgroundColor(ctx.getResources().getColor(R.color.green_500));
                    h.btnBan.setTextColor(ctx.getResources().getColor(android.R.color.white));
                } else {
                    h.btnBan.setBackgroundColor(ctx.getResources().getColor(R.color.red_500));
                    h.btnBan.setTextColor(ctx.getResources().getColor(android.R.color.white));
                }

                h.btnBan.setOnClickListener(v -> toggleBan(u));
            }

            // Set last login (placeholder)
            h.tvLastLogin.setText("Registered: Today");
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }
}