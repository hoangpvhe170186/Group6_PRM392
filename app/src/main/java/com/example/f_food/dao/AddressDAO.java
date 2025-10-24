package com.example.f_food.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.f_food.entity.Address;

import java.util.List;

@Dao
public interface AddressDAO {

    // Thêm địa chỉ mới
    @Insert
    void insert(Address address);


    // Lấy tất cả địa chỉ
    @Query("SELECT * FROM Address")
    List<Address> getAllAddresses();

    // Lấy địa chỉ theo ID người dùng
    @Query("SELECT User.FullName AS username, Address.address, Address.detailAddress, Address.isDefault, Address.addressType " +
            "FROM Address " +
            "INNER JOIN Users AS User ON Address.userId = User.UserID " +
            "WHERE Address.userId = :userId")
    List<AddressWithUser> getAddressesByUserId(int userId);

    // Xóa địa chỉ theo ID
    @Delete
    void delete(Address address);

    // Cập nhật địa chỉ
    @Update
    void update(Address address);

    // Xóa địa chỉ theo addressId
    @Query("DELETE FROM Address WHERE addressId = :id")
    void deleteById(int id);
    //Lấy địa chỉ default của user;
    @Query("SELECT * FROM Address WHERE userId = :userId AND isDefault = 1 LIMIT 1")
    Address getDefaultAddressForUser(int userId);
}
