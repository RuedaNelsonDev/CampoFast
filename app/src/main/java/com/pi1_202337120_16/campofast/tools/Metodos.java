package com.pi1_202337120_16.campofast.tools;

import static com.pi1_202337120_16.campofast.tools.Defines.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pi1_202337120_16.campofast.db.DbHelper;
import com.pi1_202337120_16.campofast.modelos.Producto;
import com.pi1_202337120_16.campofast.modelos.SharedPreference;
import com.pi1_202337120_16.campofast.modelos.User;

import java.util.ArrayList;

public class Metodos {

    Context context;
    DbHelper db;

    public Metodos(Context context) {
        this.context = context;
        db = new DbHelper(context);
    }
    public boolean addUser(User user) {
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID_USER, user.getId());
        cv.put(COLUMN_NOMBRE_USER, user.getNombre());
        cv.put(COLUMN_DOCUMENTO_USER, user.getCedula());
        cv.put(COLUMN_CELULAR_USER, user.getCelular());
        cv.put(COLUMN_DIRECCIÓN_USER, user.getDirecccion());
        cv.put(COLUMN_CORREO_USER, user.getCorreo());
        cv.put(COLUMN_CONTRASENA_USER, user.getContrasena());
        long result = sqLiteDatabase.insert(TABLE_USER, null, cv);

        if (result != -1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean addVendedor(User user) {
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID_VENDEDOR, user.getId());
        cv.put(COLUMN_NOMBRE_VENDEDOR, user.getNombre());
        cv.put(COLUMN_DOCUMENTO_VENDEDOR, user.getCedula());
        cv.put(COLUMN_CORREO_VENDEDOR, user.getCelular());
        cv.put(COLUMN_CONTRASENA_VENDEDOR, user.getDirecccion());
        cv.put(COLUMN_CELULAR_VENDEDOR, user.getCorreo());
        cv.put(COLUMN_DIRECCIÓN_VENDEDOR, user.getContrasena());
        long result = sqLiteDatabase.insert(TABLE_VENDEDOR, null, cv);

        if (result != -1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean addProduct(Producto producto) {
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITULO_PRODUCTO, producto.getTitulo());
        cv.put(COLUMN_DESCRIPCION_PRODUCTO, producto.getDescripcion());
        cv.put(COLUMN_PRECIO_PRODUCTO, producto.getPrecio());
        cv.put(COLUMN_IMAGEN_PRODUCTO, producto.getImagen());
        cv.put(COLUMN_CORREO_PRODUCTO, producto.getCorreo());
        long result = sqLiteDatabase.insert(TABLE_PRODUCTO, null, cv);

        if (result != -1) {
            return true;
        } else {
            return false;
        }
    }
    public boolean validarUser(User user) {
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_CORREO_USER + " = '" + user.getCorreo() +
                "' and " + COLUMN_CONTRASENA_USER + " = '" + user.getContrasena() + "' ";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.getCount() != 0) {
            return true;
        }
        return false;
    }

    public boolean validarUsuarioActivo(String correo) {
        SQLiteDatabase sqLiteDatabase = db.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_VENDEDOR + " WHERE " + COLUMN_CORREO_VENDEDOR + " = '" + correo + "' ";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.getCount() != 0) {
            return true;
        }
        return false;
    }
    public ArrayList<Producto> traerProductos() {

        ArrayList<Producto> listProduct = new ArrayList<>();
        SharedPreference sharedPreference;
        sharedPreference = new SharedPreference(context);
        Cursor cursor;
        SQLiteDatabase sql = db.getWritableDatabase();

        cursor = sql.rawQuery("SELECT * FROM " + TABLE_PRODUCTO + " WHERE " + COLUMN_CORREO_PRODUCTO + " = '" + sharedPreference.getUsuarioActivo() + "' ", null);


        if (cursor.getCount() == 0) {
        } else {
            while (cursor.moveToNext()) {
                Producto producto = new Producto();
                producto.setId(cursor.getString(0));
                producto.setTitulo(cursor.getString(1));
                producto.setDescripcion(cursor.getString(2));
                producto.setPrecio(cursor.getString(3));
                producto.setImagen(cursor.getBlob(4));

                listProduct.add(producto);
            }

        }
        return listProduct;

    }
}
