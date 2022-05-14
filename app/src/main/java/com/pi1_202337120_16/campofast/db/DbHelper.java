package com.pi1_202337120_16.campofast.db;


import static com.pi1_202337120_16.campofast.tools.Defines.*;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final String name = "campoFast.db";
    private static final int version = 1;
    private Context context;

    public DbHelper(@Nullable Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryUser =
                "Create table " + TABLE_USER + "(" +
                        COLUMN_ID_USER + " INTEGER primary key, " +
                        COLUMN_NOMBRE_USER + " TEXT," +
                        COLUMN_DOCUMENTO_USER + " TEXT," +
                        COLUMN_CORREO_USER + " TEXT," +
                        COLUMN_CONTRASENA_USER + " TEXT," +
                        COLUMN_CELULAR_USER + " TEXT," +
                        COLUMN_DIRECCIÓN_USER + " TEXT);";
        db.execSQL(queryUser);

        String queryVendedor =
                "Create table " + TABLE_VENDEDOR + "(" +
                        COLUMN_ID_VENDEDOR + " INTEGER primary key, " +
                        COLUMN_NOMBRE_VENDEDOR + " TEXT," +
                        COLUMN_DOCUMENTO_VENDEDOR + " TEXT," +
                        COLUMN_CORREO_VENDEDOR + " TEXT," +
                        COLUMN_CONTRASENA_VENDEDOR + " TEXT," +
                        COLUMN_CELULAR_VENDEDOR + " TEXT," +
                        COLUMN_DIRECCIÓN_VENDEDOR + " TEXT);";
        db.execSQL(queryVendedor);

        String queryProducto =
                "Create table " + TABLE_PRODUCTO + "(" +
                        COLUMN_ID_PRODUCTO + " INTEGER primary key, " +
                        COLUMN_TITULO_PRODUCTO + " TEXT," +
                        COLUMN_DESCRIPCION_PRODUCTO + " TEXT," +
                        COLUMN_PRECIO_PRODUCTO + " TEXT," +
                        COLUMN_IMAGEN_PRODUCTO + " BLOB," +
                        COLUMN_CORREO_PRODUCTO + " TEXT);";
        db.execSQL(queryProducto);

        String queryMensaje =
                "Create table " + TABLE_MENSAJES + "(" +
                        COLUMN_ID_MENSAJE + " INTEGER primary key, " +
                        COLUMN_REMITENTE_MENSAJE + " TEXT," +
                        COLUMN_DESTINATARIO_MENSAJE + " TEXT," +
                        COLUMN_HORA_MENSAJE + " TEXT," +
                        COLUMN_FECHA_MENSAJES + " TEXT," +
                        COLUMN_CONTENIDO_MENSAJE + " TEXT);";
        db.execSQL(queryMensaje);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VENDEDOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENSAJES);
    }
}