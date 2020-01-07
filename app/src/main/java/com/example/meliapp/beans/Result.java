package com.example.meliapp.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("title")    //nombre de la llave en el objeto json
    @Expose                     //para habilitar la serializacion o deserializacion de dicho objeto, por defecto es true
    private String title;

    @SerializedName("price")
    @Expose
    private float price;

    @SerializedName("available_quantity")
    @Expose
    private Integer availableQuantity;

    @SerializedName("sold_quantity")
    @Expose
    private Integer soldQuantity;

    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;

    @SerializedName("shipping")
    @Expose
    private Shipping shipping;

    @SerializedName("address")
    @Expose
    private Address address;

    @SerializedName("installments")
    @Expose
    private Installments installments;

    @SerializedName("condition")
    @Expose
    private String condition;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {this.price = price;}

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public Integer getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(Integer soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Shipping getShipping() {return shipping;}

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public Address getAddress() {return address;}

    public void setAddress(Address address) {
        this.address = address;
    }

    public Installments getInstallments() { return installments; }

    public void setInstallments(Installments installments) {this.installments = installments;}

    public String getCondition() {return condition;}

    public void setCondition(String condition) {this.condition = condition;}
}