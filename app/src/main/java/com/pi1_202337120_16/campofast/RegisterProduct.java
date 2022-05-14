package com.pi1_202337120_16.campofast;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pi1_202337120_16.campofast.modelos.Producto;
import com.pi1_202337120_16.campofast.modelos.SharedPreference;
import com.pi1_202337120_16.campofast.modelos.User;
import com.pi1_202337120_16.campofast.tools.Metodos;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class RegisterProduct extends AppCompatActivity {
    EditText etNameproductAdd;
    EditText etDescripProductAdd;
    EditText etPrecioProductAdd;
    Button btnAddImageProduct;
    Button btnRegisterProduct;
    Button btnCancelarProduct;
    ImageView ivAddProductos;

    Producto producto;
    User user;
    SharedPreference sp;
    Metodos metodos;
    WallpaperManager wallpaperManager;


    String nombreProduct;
    String descripcionProduct;
    String precioProduct;
    Bitmap imagenProduct;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_product);
        ivAddProductos = findViewById(R.id.ivAddProductos);
        etNameproductAdd = findViewById(R.id.etNameproductAdd);
        etDescripProductAdd = findViewById(R.id.etDescripProductAdd);
        etPrecioProductAdd = findViewById(R.id.etPrecioProductAdd);
        btnAddImageProduct = findViewById(R.id.btnAddImageProduct);
        btnRegisterProduct = findViewById(R.id.btnRegisterProduct);
        btnCancelarProduct = findViewById(R.id.btnCancelarProduct);

        producto = new Producto();
        user = new User();
        sp = new SharedPreference(this);
        metodos = new Metodos(this);


        //imagenProduct = etNameproductAdd.getText().toString().trim();
        //wallpaperManager = WallpaperManager.getInstance(requireContext().getApplicationContext());
        btnRegisterProduct.setOnClickListener(view -> {
            nombreProduct = etNameproductAdd.getText().toString().trim();
            descripcionProduct = etDescripProductAdd.getText().toString().trim();
            precioProduct = etPrecioProductAdd.getText().toString().trim();
            if (nombreProduct.isEmpty() || descripcionProduct.isEmpty() || precioProduct.isEmpty()) {

                Toast.makeText(getApplicationContext(), "Debe ingresar toda la información correspondiente!!!", Toast.LENGTH_SHORT).show();

            } else {
                producto.setTitulo(nombreProduct);
                producto.setDescripcion(descripcionProduct);
                producto.setPrecio(precioProduct);
                producto.setCorreo(sp.getUsuarioActivo());

                if (metodos.addProduct(producto)) {
                    Toast.makeText(getApplicationContext(), "PRODUCTO REGISTRADO EXITOSAMENTE", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(getApplicationContext(), "ERROR AL REGISTRAR PRODUCTO", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnAddImageProduct.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/");
            startActivityForResult(intent.createChooser(intent,"Seleccione la aplicación"),10);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode ==RESULT_OK){
            Uri path = data.getData();
            ivAddProductos.setImageURI(path);
            InputStream iStream = null;
            try {
                iStream = getContentResolver().openInputStream(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                byte[] inputData = getBytes(iStream);
                producto.setImagen(inputData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

}