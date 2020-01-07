package com.example.meliapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.meliapp.R;
import com.example.meliapp.beans.Result;
import com.example.meliapp.beans.Shipping;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    //Almacena los resultados en un arreglo
    private List<Result> listResults;

    private Context mContext;
    private OnResultListener mOnResultListener;


    //Constructor parametrizado del adaptador
    public ProductAdapter(List<Result> listResults, Context mContext, OnResultListener mOnResultListener) {

        this.listResults = listResults;
        this.mContext = mContext;
        this.mOnResultListener=mOnResultListener;
    }

    //Metodo para inflar la vista y llenarla de datos
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list, parent, false);

        return new ViewHolder(itemView, mOnResultListener);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Result result = listResults.get(position);

        if (result.getCondition().equals("new")) {
            holder.condition.setText(R.string.condition_new);
        }
        else{
            holder.condition.setText(R.string.condition_used);
        }

        holder.title.setText(result.getTitle());
        holder.price.setText(String.valueOf(result.getPrice()));
        holder.available_quantity.setText(String.valueOf(result.getAvailableQuantity()));
        holder.sold_quantity.setText(String.valueOf(result.getSoldQuantity()));
        holder.city.setText(result.getAddress().getCityName());
        holder.state.setText(result.getAddress().getStateName());
        if(result.getInstallments().getQuantity()!=0)
        holder.installments.setText(String.valueOf(result.getInstallments().getQuantity()));
        holder.installments_amount.setText(String.valueOf(result.getInstallments().getAmount()));

        if(result.getShipping().getFreeShipping()) {

            holder.shipping.setText(R.string.free_shipping);
        }
        else {
                holder.shipping.setText(R.string.not_free_shipping);
        }


        //Carga la imagen en el imageView usando Picasso
        Picasso.with(mContext)
                .load(result.getThumbnail())
                .fit()
                .centerCrop()
                .into(holder.image);
    }

    //Determina el numero de items en la lista
    @Override
    public int getItemCount() {return listResults.size();}


    //Proporciona una referencia directa a cada una de las vistas dentro de un elemento de datos
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title, price, available_quantity,sold_quantity, shipping, city, state,
                installments, installments_amount, condition;

        ImageView image;

        ProductAdapter.OnResultListener onResultListener;

        //Almacena la vista en las variables declaradas
        ViewHolder(View view, OnResultListener onResultListener) {
            super(view);

            title = view.findViewById(R.id.title);
            price = view.findViewById(R.id.price);
            available_quantity = view.findViewById(R.id.available_quantity);
            sold_quantity = view.findViewById(R.id.sold_quantity);
            image=view.findViewById(R.id.image);
            shipping=view.findViewById(R.id.shipping);
            city=view.findViewById(R.id.city);
            state=view.findViewById(R.id.state);
            installments = view.findViewById(R.id.installments);
            installments_amount = view.findViewById(R.id.tv_amount);
            condition=view.findViewById(R.id.condition);

            this.onResultListener =  onResultListener;

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            //llamamos a OnResultListener
            onResultListener.onResultClick(getAdapterPosition()); //devuelve la posicion del objeto seleccionado en el adaptador

        }
    }

    //interfaz usada para detectar el click
    public interface OnResultListener{

        void onResultClick(int position);   //metodo usado para enviar la posicion del item seleccionado
    }
}