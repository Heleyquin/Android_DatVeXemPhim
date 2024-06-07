package com.example.datvexemphim.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.datvexemphim.Adapter.GheNgoiAdapter;
import com.example.datvexemphim.Model.Ghe;
import com.example.datvexemphim.Model.Phim;
import com.example.datvexemphim.Model.SuatChieu;
import com.example.datvexemphim.R;
import com.example.datvexemphim.Setting.SpaceItemDecoration;

import java.util.List;

public class ChonGhe extends AppCompatActivity implements GheNgoiAdapter.ItemInterface{

    private SuatChieu suat;
    private List<Ghe> listGhe;
    private Phim phim;
    private RecyclerView rvGhe;
    private ImageView ivImage;
    private TextView tvTenPhim, tvSub, tvDate, tvTime, tvTotal;
    private Button btnBuy;
    private GheNgoiAdapter gheAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chon_ghe);

        gheAdapter = new GheNgoiAdapter(this);

        getDataIntent();
        setControl();
        setData();
        setAdapterRV();

        gheAdapter.setData(listGhe);

//        Toast.makeText(this, String.valueOf(gheAdapter.), Toast.LENGTH_LONG).show();



    }
    public void setControl(){
        rvGhe = findViewById(R.id.rvGhe);
        ivImage = findViewById(R.id.ivImage);
        tvTenPhim = findViewById(R.id.tvTenPhim);
        tvSub = findViewById(R.id.tvSub);
        tvTime = findViewById(R.id.tvTime);
        tvTotal = findViewById(R.id.tvTotal);
        btnBuy = findViewById(R.id.btnBuy);
        tvDate = findViewById(R.id.tvDate);
    }
    private void getDataIntent() {
        Intent intent = getIntent();
        suat = (SuatChieu) intent.getSerializableExtra("suat");
        listGhe = (List<Ghe>) intent.getSerializableExtra("ghe");
        phim = (Phim) intent.getSerializableExtra("phim");

    }
    public void setData(){
        Glide.with(ivImage).load(phim.getAnh()).into(ivImage);
        tvTenPhim.setText(phim.getTen());
        tvSub.setText(suat.getSub());
        tvTime.setText(String.valueOf(phim.getThoiLuong()));
        tvTotal.setText("0");
        tvDate.setText(suat.getNgayChieu());
    }
    public void setAdapterRV(){
        rvGhe.addItemDecoration(new SpaceItemDecoration(15));
        rvGhe.setLayoutManager(new GridLayoutManager(this, 4));
        rvGhe.setAdapter(gheAdapter);
    }

    @Override
    public void onItemClick(SuatChieu suat, List<Ghe> listGhe, Phim phim) {

    }
}