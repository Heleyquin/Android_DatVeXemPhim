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

import com.example.datvexemphim.Adapter.Setting.HoaDonAdapter;
import com.example.datvexemphim.Model.HoaDon;
import com.example.datvexemphim.Model.KhachHang;
import com.example.datvexemphim.R;

import java.util.List;

public class History extends AppCompatActivity {

    private HoaDonAdapter hdAdapter;
    private List<HoaDon> dsHoaDon;
    private RecyclerView rvHD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history);

        hdAdapter = new HoaDonAdapter(this);

        getDatIntent();
        setControl();
        setRV();

        hdAdapter.setData(dsHoaDon);

    }

    private void setRV() {
        rvHD.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rvHD.setAdapter(hdAdapter);
    }

    private void setControl() {
        rvHD = findViewById(R.id.rvHoadon);
    }

    private void getDatIntent() {
        Intent intent = getIntent();
        dsHoaDon = (List<HoaDon>) intent.getSerializableExtra("hd");
    }
}