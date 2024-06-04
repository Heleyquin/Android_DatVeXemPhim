package com.example.datvexemphim.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.datvexemphim.Model.Account;
import com.example.datvexemphim.R;
import com.example.datvexemphim.Services.AuthenticationService;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    EditText name, cccd, address, mail, username, password, rePassword;
    Button signUpButton;
    TextView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        setButton();
        setEvent();
    }
    void setButton(){
        name = findViewById(R.id.name);
        cccd = findViewById(R.id.cccd);
        address = findViewById(R.id.address);
        mail = findViewById(R.id.mail);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        rePassword = findViewById(R.id.rePassword);
        signUpButton = findViewById(R.id.signUpButton);
        back = findViewById(R.id.back);
    }
    void setEvent(){
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().trim().isEmpty()){
                    Toast.makeText(SignUp.this, "Name cannot empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(cccd.getText().toString().trim().isEmpty()){
                    Toast.makeText(SignUp.this, "CCCD cannot empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(address.getText().toString().trim().isEmpty()){
                    return;
                }
                if(mail.getText().toString().trim().isEmpty()){
                    Toast.makeText(SignUp.this, "Mail cannot empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(username.getText().toString().trim().isEmpty()){
                    Toast.makeText(SignUp.this, "Username cannot empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.getText().toString().trim().isEmpty()){
                    Toast.makeText(SignUp.this, "Password cannot empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(rePassword.getText().toString().trim().isEmpty()){
                    Toast.makeText(SignUp.this, "Retyped password cannot empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!password.getText().toString().trim().equals(rePassword.getText().toString().trim())){
                    Toast.makeText(SignUp.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
                else{
                    Map<String, String> data = new HashMap<>();
                    data.put("username", username.getText().toString().trim());
                    data.put("password", password.getText().toString().trim());
                    data.put("email", mail.getText().toString().trim());
                    data.put("ten", name.getText().toString().trim());
                    data.put("cccd", cccd.getText().toString().trim());
                    data.put("diaChi", address.getText().toString().trim());
                    data.put("gioiTinh", "nam");
                    AuthenticationService.register(data, SignUp.this);
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register_layout = new Intent(SignUp.this, MainActivity.class);
                startActivity(register_layout);
                finish();
            }
        });
    }
}