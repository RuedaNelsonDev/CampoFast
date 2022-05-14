package com.pi1_202337120_16.campofast;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pi1_202337120_16.campofast.modelos.User;
import com.pi1_202337120_16.campofast.tools.Metodos;

public class RegisterUser extends AppCompatActivity {

    Metodos metodos;
    User user;

    private EditText etNombre;
    private EditText etCedula;
    private EditText etCelular;
    private EditText etDireccion;
    private EditText etCorreo;
    private EditText etContrasena;
    private Button btnRegister;
    private Button btnCancel;

    private String nombre;
    private String cedula;
    private String celular;
    private String direcccion;
    private String correo;
    private String contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        etNombre = findViewById(R.id.etNombreRegister);
        etCedula = findViewById(R.id.etDocumentoRegister);
        etCelular = findViewById(R.id.etCelularRegister);
        etDireccion = findViewById(R.id.etDireccionRegister);
        etCorreo = findViewById(R.id.etCorreoRegister);
        etContrasena = findViewById(R.id.etContrasenaRegister);

        metodos = new Metodos(this);
        user = new User();

        btnRegister = findViewById(R.id.btnRegistraseUser);
        btnCancel = findViewById(R.id.btn_cancelar);



        btnRegister.setOnClickListener(view -> {
            nombre = etNombre.getText().toString().trim();
            cedula = etCedula.getText().toString().trim();
            celular = etCelular.getText().toString().trim();
            direcccion = etDireccion.getText().toString().trim();
            correo = etCorreo.getText().toString().trim();
            contrasena = etContrasena.getText().toString().trim();

            if (nombre.isEmpty() || cedula.isEmpty() || celular.isEmpty() || direcccion.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {

                Toast.makeText(getApplicationContext(), "Debe llenar todos los campos para el registro", Toast.LENGTH_SHORT).show();

            } else {

                user.setNombre(nombre);
                user.setCedula(cedula);
                user.setCelular(celular);
                user.setDirecccion(direcccion);
                user.setCorreo(correo);
                user.setContrasena(contrasena);
                if( metodos.addUser(user)){
                    Toast.makeText(getApplicationContext(), "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterUser.this, Login.class));
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), "Error al registrar usuario, valide los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(view -> {
            startActivity(new Intent(RegisterUser.this, Login.class));
            finish();
        });

    }

}
