package com.example.datvexemphim.Activity.RapFragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.datvexemphim.Activity.ForAll.ChonGhe;
import com.example.datvexemphim.Adapter.RapFragment.ByRapNgayChieuAdapter;
import com.example.datvexemphim.Model.Ghe;
import com.example.datvexemphim.Model.Phim;
import com.example.datvexemphim.Model.Phong;
import com.example.datvexemphim.Model.Rap;
import com.example.datvexemphim.Model.SuatChieu;
import com.example.datvexemphim.R;
import com.example.datvexemphim.Setting.SpaceItemDecoration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ByRap_Movie_Detail extends AppCompatActivity implements ByRapNgayChieuAdapter.ItemInterface{
    private Phim phim;
    private TextView tvDecript, tvTitle, tvTime, tvNation, tvReleaseDate, tvAge;
    private ImageView ivImage;
    private RecyclerView rvLich;
    private List<SuatChieu> dsSuatChieu;
    private ByRapNgayChieuAdapter ngayAdapter;
    private List<Rap> dsRap;
    private List<Phong> dsPhong;
    private List<Ghe> dsGhe;
    private List<Phim> dsPhim;
    private Rap rap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_by_rap_movie_detail);

        ngayAdapter = new ByRapNgayChieuAdapter(this, getApplicationContext());

        setControl();
        getDataIntent();
        setAdapterrvRap();
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
        List<SuatChieu> suatOfPhim = new ArrayList<>();
        for(SuatChieu suat:dsSuatChieu){
            if(suat.getId_phim().getIdPhim() == phim.getIdPhim()){
                suatOfPhim.add(suat);
            }
        }
        ngayAdapter.setData(suatOfPhim, dsGhe, phim);
    }

    private void setControl() {
        tvDecript = findViewById(R.id.tvDecript);
        tvTitle = findViewById(R.id.tvTitle);
        tvTime = findViewById(R.id.tvTime);
        tvNation = findViewById(R.id.tvNation);
        tvReleaseDate = findViewById(R.id.tvReleaseDate);
        tvAge = findViewById(R.id.tvAge);
        ivImage = findViewById(R.id.ivImage);
        rvLich = findViewById(R.id.rvLich);
    }

    private void getDataIntent() {
        Intent intent = getIntent();

        dsPhong = (List<Phong>) intent.getSerializableExtra("phongs");
        phim = (Phim) intent.getSerializableExtra("movie");
        dsPhim = (List<Phim>) intent.getSerializableExtra("phims");
        dsGhe = (List<Ghe>) intent.getSerializableExtra("ghes");
        dsSuatChieu = (List<SuatChieu>) intent.getSerializableExtra("suats");
        rap = (Rap) intent.getSerializableExtra("rap");

    }
    private void setAdapterrvRap() {
        rvLich.addItemDecoration(new SpaceItemDecoration(200));
        rvLich.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        rvLich.setAdapter(ngayAdapter);
    }

    @Override
    public void onItemClick(SuatChieu suat, List<Ghe> listGhe, Phim phim) {
        Intent intent = new Intent(this, ChonGhe.class);

        intent.putExtra("suat", suat);
        intent.putExtra("phim", phim);
        intent.putExtra("ghe",(Serializable) listGhe);

        startActivity(intent);
    }

//    @Override
//    public void onItemClick(SuatChieu suat, List<Ghe> listGhe, Phim phim) {
//        Intent intent = new Intent(this, ChonGhe.class);

//        intent.putExtra("suat", suat);
//        intent.putExtra("phim", phim);
//        intent.putExtra("ghe",(Serializable) listGhe);

//        startActivity(intent);
//    }
}