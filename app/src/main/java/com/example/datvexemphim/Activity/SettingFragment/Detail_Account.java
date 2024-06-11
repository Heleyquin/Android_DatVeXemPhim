package com.example.datvexemphim.Activity.SettingFragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.datvexemphim.Model.Account;
import com.example.datvexemphim.Model.KhachHang;
import com.example.datvexemphim.R;

public class Detail_Account extends AppCompatActivity {
    private EditText username, oldPass, newPass, rePassword;
    private Button btnSave;
    private Account tk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_account);

        getDataIntent();
        setControl();
        setData();
        setEvent();

    }

    private void setEvent() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (oldPass.getText().toString().equals(tk.getMk())){
                    if(newPass.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Mật khẩu mới không được để trống", Toast.LENGTH_LONG).show();
                    }else{
                        if(newPass.getText().toString().equals(rePassword.getText().toString())){
                            showConfirmationDialogChangePass();
                        }else{
                            Toast.makeText(getApplicationContext(), "Mật khẩu mới không trùng nhau", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Mật khẩu cũ không hợp lệ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setData() {
        username.setText(tk.getTk());
    }

    private void setControl() {
        username = findViewById(R.id.username);
        oldPass = findViewById(R.id.oldPass);
        newPass = findViewById(R.id.newPass);
        rePassword = findViewById(R.id.rePassword);
        btnSave = findViewById(R.id.btnSave);
    }

    private void getDataIntent() {
        Intent intent = getIntent();
        tk = (Account) intent.getSerializableExtra("tk");
    }
    private void showConfirmationDialogChangePass() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Bạn có chắc chắn muốn đổi mật khẩu??");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "ĐỔI MẬT KHẨU",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        tk.setMk(newPass.getText().toString());
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Mật khẩu đã được đổi", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });

        builder1.setNegativeButton(
                "HUỶ",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}