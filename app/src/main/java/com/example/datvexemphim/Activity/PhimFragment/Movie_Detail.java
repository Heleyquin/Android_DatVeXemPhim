package com.example.datvexemphim.Activity.PhimFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.datvexemphim.Activity.ForAll.ChonGhe;
import com.example.datvexemphim.Adapter.PhimFragment.NgayChieuAdapter;
import com.example.datvexemphim.Adapter.PhimFragment.RapAdapter;
import com.example.datvexemphim.Model.Ghe;
import com.example.datvexemphim.Model.Phim;
import com.example.datvexemphim.Model.Phong;
import com.example.datvexemphim.Model.Rap;
import com.example.datvexemphim.Model.SuatChieu;
import com.example.datvexemphim.R;
import com.example.datvexemphim.Services.GheService;
import com.example.datvexemphim.Services.PhongService;
import com.example.datvexemphim.Services.RapService;
import com.example.datvexemphim.Services.SuatChieuService;
import com.example.datvexemphim.Setting.SpaceItemDecoration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Movie_Detail extends AppCompatActivity implements NgayChieuAdapter.ItemInterface{

    private Phim phim;
    private TextView tvDecript, tvTitle, tvTime, tvNation, tvReleaseDate, tvAge;
    private ImageView ivImage;
    private RecyclerView rvRap;
    private List<SuatChieu> dsSuatChieu;
    private RapAdapter rapAdapter;
    private List<Rap> dsRap;
    private List<Phong> dsPhong;
    private List<Ghe> dsGhe;
    private List<Phim> listPhim;
    private Rap rap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_movie_detail);

        rapAdapter = new RapAdapter(this);

        taoRap();
        taoPhong();
        taoSuatChieu();
        taoGhe();

        setControl();
        setAdapterrvRap();
        setDataIntent();
        setDataAdapter();
        setDataView();

    }

    private void setDataView() {
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
        rapAdapter.setData(dsRap, dsPhong, dsSuatChieu, dsGhe, listPhim);
    }


    private void setAdapterrvRap() {
        rvRap.addItemDecoration(new SpaceItemDecoration(200));
        rvRap.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rvRap.setAdapter(rapAdapter);
    }

    public void taoRap(){
        dsRap = new ArrayList<>();
        List<Rap> dsRapFil = new ArrayList<>();
        RapService.getAllRap(dsRap, rapAdapter,dsRapFil);
    }
    public void taoPhong(){
        dsPhong = new ArrayList<>();
        PhongService.getAllPhong(dsPhong, rapAdapter);
    }
    public void taoGhe(){
        dsGhe = new ArrayList<>();
        GheService.getAllGhe(dsGhe, rapAdapter);
    }
    public void taoSuatChieu(){
        dsSuatChieu = new ArrayList<>();
        Intent intent = getIntent();
        phim = (Phim) intent.getSerializableExtra("movie");
        SuatChieuService.getSuatChieuByPhim(dsSuatChieu,phim.getIdPhim(), rapAdapter);
    }

    private void setDataIntent() {
        Intent intent = getIntent();
        phim = (Phim) intent.getSerializableExtra("movie");
        System.out.println(phim.toString());
        listPhim = (List<Phim>) intent.getSerializableExtra("phim");
    }
    public void setControl(){
        tvDecript = findViewById(R.id.tvDecript);
        tvTitle = findViewById(R.id.tvTitle);
        tvTime = findViewById(R.id.tvTime);
        tvNation = findViewById(R.id.tvNation);
        tvReleaseDate = findViewById(R.id.tvReleaseDate);
        tvAge = findViewById(R.id.tvAge);
        ivImage = findViewById(R.id.ivImage);
        rvRap = findViewById(R.id.rvRap);
    }

    @Override
    public void onItemClick(SuatChieu suat, List<Ghe> listGhe, Phim phim) {
        Intent intent = new Intent(this, ChonGhe.class);
        intent.putExtra("suat", suat);
        intent.putExtra("phim", phim);
        intent.putExtra("ghe",(Serializable) listGhe);
        Log.e("tranID", String.valueOf(listGhe.size()));
        startActivity(intent);
    }

}