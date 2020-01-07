package com.example.meliapp.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Installments {

    @SerializedName("quantity")
    @Expose
    private int quantity;

    @SerializedName("amount")
    @Expose
    private float amount;

    @SerializedName("rate")
    @Expose
    private float rate;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
