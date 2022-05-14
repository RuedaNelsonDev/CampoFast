package com.pi1_202337120_16.campofast;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pi1_202337120_16.campofast.adapters.MenuProductsAdapters;
import com.pi1_202337120_16.campofast.modelos.Producto;
import com.pi1_202337120_16.campofast.modelos.SharedPreference;
import com.pi1_202337120_16.campofast.tools.Metodos;

import java.util.ArrayList;

public class Menu extends AppCompatActivity {
    ImageView ivRegisterVendedor;
    ImageView ivAddProductos;
    RecyclerView rvProductsMenu;
    SharedPreference sp;
    Metodos metodos;
    MenuProductsAdapters adapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ivRegisterVendedor = findViewById(R.id.ivRegisVendeMenu);
        ivAddProductos = findViewById(R.id.ivAddProductos);
        rvProductsMenu = findViewById(R.id.rvProductsMenu);


        sp = new SharedPreference(this);
        metodos = new Metodos(this);
        if (!metodos.validarUsuarioActivo(sp.getUsuarioActivo())) {
            ivRegisterVendedor.setVisibility(View.GONE);
        }

        ArrayList<Producto> listProduct = metodos.traerProductos();

        adapters = new MenuProductsAdapters(this, listProduct);
        rvProductsMenu.setAdapter(adapters);
        rvProductsMenu.setLayoutManager(new GridLayoutManager(this, 2));
        ivAddProductos.setOnClickListener(view -> {
            startActivity(new Intent(Menu.this, RegisterProduct.class));
            finish();
        });


        ivRegisterVendedor.setOnClickListener(view -> {
            startActivity(new Intent(Menu.this, RegisterUser.class));
            finish();
        });
    }


}