package com.example.datvexemphim.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.datvexemphim.R;
import com.example.datvexemphim.Services.AuthenticationService;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginButton;
    TextView signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        signUp = findViewById(R.id.signUp);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> login = new HashMap<>();
                login.put("username", username.getText().toString().trim());
                login.put("password", password.getText().toString().trim());
                AuthenticationService.login(login, MainActivity.this);
            }
        });
        signUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent register_layout = new Intent(MainActivity.this, SignUp.class);
                startActivity(register_layout);
                finish();
            }
        });
    }
}