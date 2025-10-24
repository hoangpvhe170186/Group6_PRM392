package com.example.f_food.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class CartItem implements Parcelable {
    private Food product;
    private int quantity;
    private boolean isSelected;

    public CartItem(Food product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.isSelected = false;
    }

    protected CartItem(Parcel in) {
        product = in.readParcelable(Food.class.getClassLoader());
        quantity = in.readInt();
        isSelected = in.readByte() != 0;
    }

    public static final Creator<CartItem> CREATOR = new Creator<CartItem>() {
        @Override
        public CartItem createFromParcel(Parcel in) {
            return new CartItem(in);
        }

        @Override
        public CartItem[] newArray(int size) {
            return new CartItem[size];
        }
    };

    public Food getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable((Parcelable) product, flags);
        dest.writeInt(quantity);
        dest.writeByte((byte) (isSelected ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
