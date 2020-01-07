package com.example.meliapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ProductDetail extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);


        getIncomingIntent();
    }

    private void getIncomingIntent(){

        if(getIntent().hasExtra("image_url") && getIntent().hasExtra("title")
                && getIntent().hasExtra("condition") && getIntent().hasExtra("price")
                && getIntent().hasExtra("quantity") && getIntent().hasExtra("sold_quantity")
                && getIntent().hasExtra("city") && getIntent().hasExtra("state")
                && getIntent().hasExtra("shipping") && getIntent().hasExtra("installments")
                && getIntent().hasExtra("installments_amount")){

            String imageUrl = getIntent().getStringExtra("image_url");
            String title = getIntent().getStringExtra("title");
            String condition = getIntent().getStringExtra("condition");
            float price = getIntent().getFloatExtra("price",.0f);
            Integer quantity = getIntent().getIntExtra("quantity",0);
            Integer soldQuantity = getIntent().getIntExtra("sold_quantity",0);
            String city = getIntent().getStringExtra("city");
            String state = getIntent().getStringExtra("state");
            Boolean shipping = getIntent().getBooleanExtra("shipping",true);
            Integer installments = getIntent().getIntExtra("installments",0);
            float installments_amount = getIntent().getFloatExtra("installments_amount",.0f);

            setData(title, condition, price,quantity,soldQuantity,city,state,shipping, imageUrl, installments, installments_amount);

        }
    }

    private void setData(String Title, String Condition, float Price, Integer Quantity, Integer soldQuantity, String pCity,
                         String pState, Boolean pShipping, String ImageUrl, Integer Installments, float Amount ){


        TextView title, condition, price, available_quantity, sold_quantity, shipping, city, state,
                installments, installments_amount;

        ImageView image;

        title=findViewById(R.id.title);
        condition=findViewById(R.id.condition);
        price=findViewById(R.id.price);
        available_quantity=findViewById(R.id.available_quantity);
        sold_quantity=findViewById(R.id.sold_quantity);
        city=findViewById(R.id.city);
        state=findViewById(R.id.state);
        shipping=findViewById(R.id.shipping);
        image=findViewById(R.id.image);
        installments=findViewById(R.id.installments);
        installments_amount=findViewById(R.id.tv_amount);


        title.setText(Title);

        if (Condition.equals("new")) {
            condition.setText(R.string.condition_new);
        }
        else{
            condition.setText(R.string.condition_used);
        }

        price.setText(String.valueOf(Price));
        available_quantity.setText(String.valueOf(Quantity));
        sold_quantity.setText(String.valueOf(soldQuantity));
        city.setText(pCity);
        state.setText(pState);

        if(pShipping){
            shipping.setText(R.string.free_shipping);
        }else{
            shipping.setText(R.string.not_free_shipping);
        }

        installments.setText(String.valueOf(Installments));
        installments_amount.setText(String.valueOf(Amount));

        Picasso.with(this)
                .load(ImageUrl)
                .fit()
                .centerCrop()
                .into(image);

    }
}
