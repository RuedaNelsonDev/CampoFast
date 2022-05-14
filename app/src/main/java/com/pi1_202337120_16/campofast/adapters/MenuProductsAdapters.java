package com.pi1_202337120_16.campofast.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pi1_202337120_16.campofast.R;
import com.pi1_202337120_16.campofast.modelos.Producto;

import java.util.ArrayList;

public class MenuProductsAdapters extends RecyclerView.Adapter<MenuProductsAdapters.MyViewHolder> {
    Context context;
    ArrayList<Producto> listProducts;

    public MenuProductsAdapters(Context context, ArrayList<Producto> listProducts) {
        this.context = context;
        this.listProducts = listProducts;
    }

    @NonNull
    @Override
    public MenuProductsAdapters.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_products, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuProductsAdapters.MyViewHolder holder, int position) {
        Producto producto = listProducts.get(position);
        holder.ivImagenProduct.setImageBitmap(byteToBitmap(producto.getImagen()));
        holder.tvNombreProductItem.setText(producto.getTitulo());
        holder.tvPrecioProductItem.setText(producto.getPrecio());
    }

    @Override
    public int getItemCount() {
        return listProducts.size();
    }

    private Bitmap byteToBitmap(byte[] bytes) {
        if (bytes == null) {

            return null;
        } else return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombreProductItem;
        TextView tvPrecioProductItem;
        ImageView ivImagenProduct;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreProductItem = itemView.findViewById(R.id.tvNombreProductItem);
            tvPrecioProductItem = itemView.findViewById(R.id.tvPrecioProductItem);
            ivImagenProduct = itemView.findViewById(R.id.ivImagenProduct);

        }
    }
}
