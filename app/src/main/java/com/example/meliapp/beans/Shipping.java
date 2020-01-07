package com.example.meliapp.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Shipping {

    @SerializedName("free_shipping")
    @Expose
    private Boolean freeShipping;

    public Boolean getFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(Boolean freeShipping) {this.freeShipping = freeShipping; }

}