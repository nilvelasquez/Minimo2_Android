package com.example.juegodsarest3.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.juegodsarest3.R;

public class InicialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        configureLoginButton();
        configureRegisterButton();
    }

    private void configureLoginButton(){
        Button BotonLogear = (Button) findViewById(R.id.loginBtn);
        BotonLogear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InicialActivity.this, LoginActivity.class));
            }
        });
    }

    private void configureRegisterButton(){
        Button BotonRegistrar = (Button) findViewById(R.id.registerBtn);
        BotonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InicialActivity.this, RegisterActivity.class));
            }
        });
    }

}