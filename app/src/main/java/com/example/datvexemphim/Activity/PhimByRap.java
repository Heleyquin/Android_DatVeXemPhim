package com.example.datvexemphim.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datvexemphim.Adapter.MoviesAdapter;
import com.example.datvexemphim.Model.Ghe;
import com.example.datvexemphim.Model.Phim;
import com.example.datvexemphim.Model.Phong;
import com.example.datvexemphim.Model.Rap;
import com.example.datvexemphim.Model.SuatChieu;
import com.example.datvexemphim.R;

import java.util.List;

public class PhimByRap extends AppCompatActivity implements MoviesAdapter.ItemInterface{

    private List<Phim> dsPhim;
    private List<SuatChieu> dsSuatChieu;
    private List<Ghe> dsGhe;
    private Rap rap;
    private List<Phong> dsPhong;
    private RecyclerView rvPhim;
    private TextView tvTenRap;
    private MoviesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_phim_by_rap);

        adapter = new MoviesAdapter(this, getApplicationContext());

        setDataIntent();
        setControl();
        setRV();

        adapter.setData(dsPhim);

    }
    private void setDataIntent() {
        Intent intent = getIntent();
        dsPhim = (List<Phim>) intent.getSerializableExtra("phims");
        dsGhe = (List<Ghe>) intent.getSerializableExtra("ghes");
        dsSuatChieu = (List<SuatChieu>) intent.getSerializableExtra("suats");
        rap = (Rap) intent.getSerializableExtra("rap");
    }
    private void setControl(){
        rvPhim = findViewById(R.id.rvPhim);
        tvTenRap = findViewById(R.id.tvTenRap);

        tvTenRap.setText(rap.getTenRap());
    }
    private void setRV(){
        rvPhim.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rvPhim.setAdapter(adapter);

    }

    @Override
    public void onItemClick(View view, int position) {

    }
}