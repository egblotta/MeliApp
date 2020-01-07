package com.example.meliapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meliapp.adapters.ProductAdapter;
import com.example.meliapp.api.ApiService;
import com.example.meliapp.beans.Paging;
import com.example.meliapp.beans.ProductResult;
import com.example.meliapp.beans.Result;
import com.example.meliapp.helper.RetroClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements ProductAdapter.OnResultListener {


    private ArrayList<Result> results;
    private Paging paging;

    private ProductAdapter pAdapter;

    private ProgressBar pBar;
    private RecyclerView recyclerView;
    private EditText etBuscar;
    private TextView tvResultsNumber;

    Integer counter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pBar=findViewById(R.id.progressbar);

        etBuscar = findViewById(R.id.et_buscar);
        tvResultsNumber = findViewById(R.id.tv_result_number);
        Button btnBuscar = findViewById(R.id.btn_buscar);
        ImageView imagenSc = findViewById(R.id.imagen_sinconexion);
        ConstraintLayout constraintLayout = findViewById(R.id.constraint_buscar);

        imagenSc.setVisibility(View.INVISIBLE);
        pBar.setVisibility(View.INVISIBLE);

        // Java code sample
        if (ConnectivityHelper.isConnectedToNetwork(this)) {

            btnBuscar.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    counter=1;
                    pBar.setVisibility(View.VISIBLE);
                    pBar.setMax(10);
                    pBar.setIndeterminate(true);
                    pBar.setProgress(1);

                    Buscar();
                    hideKeyboard();

                }
            });

        } else {
            hideKeyboard();
            constraintLayout.setVisibility(View.INVISIBLE);
            imagenSc.setVisibility(View.VISIBLE);
            Toast.makeText(this, "No hay conexión a internet", Toast.LENGTH_LONG).show();
        }

    }

    public void Buscar(){

        // Crea un objeto de la interfaz de la Api
        ApiService api = RetroClient.getApiService();


                //Metodo call de la api
                Call<ProductResult> call = api.getMyJSON(etBuscar.getText().toString());

                //Envia de manera asincrona la peticion y notifica con un callback cuando hay una respuesta
                call.enqueue(new Callback<ProductResult>() {

                    @Override
                    public void onResponse(Call<ProductResult> call, Response<ProductResult> response) {

                        //Oculta ProgressBar
                        pBar.setVisibility(View.GONE);

                        if(!response.isSuccessful()) {

                            Toast.makeText(MainActivity.this, "Code:" + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        //Valida que la variable no sea null
                        if (response.body() != null) {
                            results = response.body().getResults();
                            paging = response.body().getPaging();
                        }

                        recyclerView = findViewById(R.id.recycler_view);

                        LinearLayout linearLayout = findViewById(R.id.ll_result);
                        linearLayout.setVisibility(View.VISIBLE);
                        tvResultsNumber.setText(String.valueOf(paging.getTotal()));

                            pAdapter = new ProductAdapter(results,MainActivity.this, MainActivity.this);


                            //Posicionamiento de los items en modo de grilla
                        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2,
                                StaggeredGridLayoutManager.VERTICAL);

                            recyclerView.setLayoutManager(gridLayoutManager);


                            //Optimizacion en el scrolling del recycler cuando los items son estaticos
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());

                            recyclerView.setAdapter(pAdapter);

                    }

                    @Override
                    public void onFailure(Call<ProductResult> call, Throwable t) {

                        pBar.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }


    //metodo para navegar hacia otra activity pasando los valores de los campos
    @Override
    public void onResultClick(int position) {

        Intent intent = new Intent(this, ProductDetail.class);

        intent.putExtra("image_url",  results.get(position).getThumbnail());
        intent.putExtra("title",  results.get(position).getTitle());
        intent.putExtra("condition",  results.get(position).getCondition());
        intent.putExtra("price", (results.get(position).getPrice()));
        intent.putExtra("quantity",  results.get(position).getAvailableQuantity());
        intent.putExtra("sold_quantity",  results.get(position).getSoldQuantity());
        intent.putExtra("city",  results.get(position).getAddress().getCityName());
        intent.putExtra("state",  results.get(position).getAddress().getStateName());
        intent.putExtra("shipping",  results.get(position).getShipping().getFreeShipping());
        intent.putExtra("installments",  results.get(position).getInstallments().getQuantity());
        intent.putExtra("installments_amount",  results.get(position).getInstallments().getAmount());

        startActivity(intent);

    }

    //Metodo para ocultar el teclado
    private void hideKeyboard(){

        View view = this.getCurrentFocus();

        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

            if( imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    //Metodo para comprobar si hay conexion a internet
    public static class ConnectivityHelper {

        static boolean isConnectedToNetwork(Context context) {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            boolean isConnected = false;
            if (connectivityManager != null) {
                NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
                isConnected = (activeNetwork != null) && (activeNetwork.isConnectedOrConnecting());
            }

            return isConnected;
        }
    }

}
