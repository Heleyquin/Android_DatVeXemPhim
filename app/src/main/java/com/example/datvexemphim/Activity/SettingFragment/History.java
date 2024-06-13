package com.example.datvexemphim.Activity.SettingFragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datvexemphim.Adapter.Setting.VeAdapter;
import com.example.datvexemphim.Model.Ve;
import com.example.datvexemphim.Model.KhachHang;
import com.example.datvexemphim.R;

import java.util.List;

public class History extends AppCompatActivity {

    private VeAdapter veAdapter;
    private List<Ve> dsVe;
    private RecyclerView rvHD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history);

        veAdapter = new VeAdapter(this);

        getDatIntent();
        setControl();
        setRV();

        veAdapter.setData(dsVe);

    }

    private void setRV() {
        rvHD.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rvHD.setAdapter(veAdapter);
    }

    private void setControl() {
        rvHD = findViewById(R.id.rvHoadon);
    }

    private void getDatIntent() {
        Intent intent = getIntent();
        dsVe = (List<Ve>) intent.getSerializableExtra("ve");
    }
}