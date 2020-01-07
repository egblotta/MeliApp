//Define los posibles endpoints y sus respectivos metodos HTTP

package com.example.meliapp.api;

import com.example.meliapp.beans.ProductResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    //Peticion GET
    @GET("sites/MLA/search")
    Call<ProductResult> getMyJSON(@Query("q") String query);
}
