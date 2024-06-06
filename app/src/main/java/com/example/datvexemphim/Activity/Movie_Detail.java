package com.example.datvexemphim.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.datvexemphim.Adapter.NgayChieuAdapter;
import com.example.datvexemphim.Model.Phim;
import com.example.datvexemphim.Model.SuatChieu;
import com.example.datvexemphim.R;
import com.example.datvexemphim.Setting.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class Movie_Detail extends AppCompatActivity implements NgayChieuAdapter.ItemInterface{

    private Phim phim;
    private TextView tvDecript, tvTitle, tvTime, tvNation, tvReleaseDate, tvAge;
    private ImageView ivImage;
    private RecyclerView rvNgayChieu;
    private List<SuatChieu> dsSuatChieu;
    private NgayChieuAdapter dateAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_movie_detail);

        dateAdapter = new NgayChieuAdapter(this);
        taoSuatChieu();
        setControl();
        setDataIntent();
        setAdapterrvNgayChieu();
        setDataAdapter();

        Glide.with(ivImage).load(phim.getAnh()).into(ivImage);
        tvTitle.setText(phim.getTen());
        tvDecript.setText(phim.getMoTa());
        tvTime.setText(String.valueOf(phim.getThoiLuong()));
        tvNation.setText(phim.getQuocGia());
        tvReleaseDate.setText(phim.getNamPhatHanh());
        if(phim.isDoTuoi()){
            tvAge.setText("18+");
        }
        else{
            tvAge.setText("Mọi lứa tuổi");
        }

    }

    private void setDataAdapter() {
        List<SuatChieu> dsChieuTheoPhim = new ArrayList<>();
        for(SuatChieu suat : dsSuatChieu){
            if(suat.getId_phim() == phim.getIdPhim()){
                dsChieuTheoPhim.add(suat);
            }
        }
        dateAdapter.setData(dsChieuTheoPhim);
//        Toast.makeText(getApplicationContext(), String.valueOf(dsChieuTheoPhim.size()), Toast.LENGTH_LONG).show();
    }


    private void setAdapterrvNgayChieu() {
        rvNgayChieu.addItemDecoration(new SpaceItemDecoration(15));
        rvNgayChieu.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        rvNgayChieu.setAdapter(dateAdapter);
    }

    public void taoSuatChieu(){
        dsSuatChieu = new ArrayList<>();
        dsSuatChieu.add(new SuatChieu(1, "10:30", "English", "05/06/2024", "VietSub", 100000, 1, 1));
        dsSuatChieu.add(new SuatChieu(2, "12:30", "English", "05/06/2024", "VietSub", 100000, 1, 1));
        dsSuatChieu.add(new SuatChieu(3, "13:30", "English", "06/06/2024", "VietSub", 100000, 1, 1));
        dsSuatChieu.add(new SuatChieu(4, "10:30", "English", "05/06/2024", "VietSub", 100000, 1, 2));
        dsSuatChieu.add(new SuatChieu(5, "10:30", "English", "05/06/2024", "VietSub", 100000, 1, 3));
        dsSuatChieu.add(new SuatChieu(6, "16:30", "English", "06/06/2024", "VietSub", 100000, 1, 1));
    }


    private void setDataIntent() {
        Intent intent = getIntent();
        phim = (Phim) intent.getSerializableExtra("movie");
    }
    public void setControl(){
        tvDecript = findViewById(R.id.tvDecript);
        tvTitle = findViewById(R.id.tvTitle);
        tvTime = findViewById(R.id.tvTime);
        tvNation = findViewById(R.id.tvNation);
        tvReleaseDate = findViewById(R.id.tvReleaseDate);
        tvAge = findViewById(R.id.tvAge);
        ivImage = findViewById(R.id.ivImage);
        rvNgayChieu = findViewById(R.id.rvNgayChieu);
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}