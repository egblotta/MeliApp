//Instanciamos Retrofit.Builder usando la URL del dominio

package com.example.meliapp.helper;

import com.example.meliapp.api.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {


    //URL
    private static final String ROOT_URL = "https://api.mercadolibre.com/";

    //Instancia Retrofit
    private static Retrofit getRetrofitInstance(){

        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /*
     * Get API Service
     *
     * @return API Service
     */

    //Metodo estatico
    public static ApiService getApiService(){

        return getRetrofitInstance().create(ApiService.class);
    }
}
