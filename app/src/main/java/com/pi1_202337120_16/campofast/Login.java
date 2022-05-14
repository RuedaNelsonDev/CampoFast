package com.pi1_202337120_16.campofast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pi1_202337120_16.campofast.modelos.SharedPreference;
import com.pi1_202337120_16.campofast.modelos.User;
import com.pi1_202337120_16.campofast.tools.Metodos;

public class Login extends AppCompatActivity {

    EditText etCorreo;
    EditText etContrasena;
    Button btnLogin;
    Button btnRegister;

    User user;
    Metodos metodos;
    SharedPreference sp;

    String correo;
    String contrasena;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        etCorreo = findViewById(R.id.etCorreoLogin);
        etContrasena = findViewById(R.id.etContraLogin);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegisterLogin);

        user = new User();
        metodos = new Metodos(this);
        sp = new SharedPreference(this);



        btnLogin.setOnClickListener(view -> {
            correo = etCorreo.getText().toString().trim();
            contrasena = etContrasena.getText().toString().trim();
            if(correo.isEmpty()||contrasena.isEmpty()){
                Toast.makeText(getApplicationContext(), "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
            }else {
                user.setCorreo(correo);
                user.setContrasena(contrasena);
                if(metodos.validarUser(user)){
                    sp.setUsuarioActivo(correo);
                    Toast.makeText(getApplicationContext(), "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, Menu.class));
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), "Datos incorrectos, valide nuevamente", Toast.LENGTH_SHORT).show();

                }
            }
        });
        btnRegister.setOnClickListener(view -> {
            startActivity(new Intent(Login.this, RegisterUser.class));
            finish();
        });
    }
}