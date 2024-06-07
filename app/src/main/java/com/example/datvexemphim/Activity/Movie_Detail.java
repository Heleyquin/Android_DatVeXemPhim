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
import com.example.datvexemphim.Adapter.RapAdapter;
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
        setDataIntent();
        setAdapterrvRap();
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

//    private void setSuatChieuTheoPhim(){
//        List<SuatChieu> dsChieuTheoPhim = new ArrayList<>();
//        for(SuatChieu suat : dsSuatChieu){
//            if(suat.getId_phim() == phim.getIdPhim()){
//                dsChieuTheoPhim.add(suat);
//            }
//        }
//    }
    private void setDataAdapter() {
        List<SuatChieu> dsChieuTheoPhim = new ArrayList<>();
        for(SuatChieu suat : dsSuatChieu){
            if(suat.getId_phim() == phim.getIdPhim()){
                dsChieuTheoPhim.add(suat);
            }
        }
        rapAdapter.setData(dsRap, dsPhong, dsChieuTheoPhim, dsGhe, listPhim);
//        Toast.makeText(getApplicationContext(), String.valueOf(dsChieuTheoPhim.size()), Toast.LENGTH_LONG).show();
    }


    private void setAdapterrvRap() {
        rvRap.addItemDecoration(new SpaceItemDecoration(15));
        rvRap.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rvRap.setAdapter(rapAdapter);
    }

    public void taoRap(){
        dsRap = new ArrayList<>();
        dsRap.add(new Rap(1, "Rạp Hồ Chí Minh", "Q9, Tp.Thu Duc, TP.HCM"));
        dsRap.add(new Rap(2, "Rạp Hà Nội", "Q.Hoàng Mai, P.Hai Bà Trưng, Hà Nội"));
        dsRap.add(new Rap(3, "Rạp Thanh Hoá", "P.Tào Xuyên, Tp.Thanh Hoá, Tỉnh Thanh Hoá"));
    }
    public void taoPhong(){
        dsPhong = new ArrayList<>();
        dsPhong.add(new Phong(1, "1HCM", 1));
        dsPhong.add(new Phong(2, "2HCM", 1));
        dsPhong.add(new Phong(3, "3HCM", 1));
        dsPhong.add(new Phong(4, "1HN", 2));
        dsPhong.add(new Phong(5, "2HN", 2));
        dsPhong.add(new Phong(6, "3HN", 2));
        dsPhong.add(new Phong(7, "1TH", 3));
        dsPhong.add(new Phong(8, "2TH", 3));
        dsPhong.add(new Phong(9, "3TH", 3));
    }
    public void taoGhe(){
        dsGhe = new ArrayList<>();
        dsGhe.add(new Ghe(1, "D", "1", "1", 1));
        dsGhe.add(new Ghe(2, "D", "1", "2", 1));
        dsGhe.add(new Ghe(3, "D", "1", "3", 1));
        dsGhe.add(new Ghe(4, "D", "1", "4", 1));
        dsGhe.add(new Ghe(5, "D", "2", "5", 1));
        dsGhe.add(new Ghe(6, "D", "2", "6", 1));
        dsGhe.add(new Ghe(7, "D", "2", "7", 1));
        dsGhe.add(new Ghe(8, "D", "2", "8", 1));
        dsGhe.add(new Ghe(9, "D", "3", "9", 1));
        dsGhe.add(new Ghe(10, "D", "3", "10", 1));
        dsGhe.add(new Ghe(11, "D", "3", "11", 1));
        dsGhe.add(new Ghe(12, "D", "3", "12", 1));
        dsGhe.add(new Ghe(13, "D", "3", "13", 1));
        dsGhe.add(new Ghe(14, "D", "4", "14", 1));
        dsGhe.add(new Ghe(15, "D", "4", "15", 1));
        dsGhe.add(new Ghe(16, "D", "4", "16", 1));
        dsGhe.add(new Ghe(17, "D", "4", "17", 1));
        dsGhe.add(new Ghe(18, "D", "5", "18", 1));
        dsGhe.add(new Ghe(19, "D", "5", "19", 1));
        dsGhe.add(new Ghe(20, "D", "5", "20", 1));
        dsGhe.add(new Ghe(1, "D", "1", "1", 2));
    }
    public void taoSuatChieu(){
        dsSuatChieu = new ArrayList<>();
        dsSuatChieu.add(new SuatChieu(1, "10:30", "English", "05/06/2024", "VietSub", 100000, 1, 1, 1));
        dsSuatChieu.add(new SuatChieu(2, "12:30", "English", "05/06/2024", "VietSub", 100000, 1, 1, 1));
        dsSuatChieu.add(new SuatChieu(3, "13:30", "English", "06/06/2024", "VietSub", 100000, 1, 1, 1));
        dsSuatChieu.add(new SuatChieu(4, "10:30", "English", "05/06/2024", "VietSub", 100000, 1, 1,2));
        dsSuatChieu.add(new SuatChieu(5, "10:30", "English", "05/06/2024", "VietSub", 100000, 1, 3,2));
        dsSuatChieu.add(new SuatChieu(6, "16:30", "English", "06/06/2024", "VietSub", 100000, 1, 1, 3));
        dsSuatChieu.add(new SuatChieu(7, "10:30", "English", "07/06/2024", "VietSub", 100000, 1, 2,2));
        dsSuatChieu.add(new SuatChieu(8, "10:30", "English", "08/06/2024", "VietSub", 100000, 1, 1,2));
        dsSuatChieu.add(new SuatChieu(9, "10:30", "English", "09/06/2024", "VietSub", 100000, 1, 1,2));
        dsSuatChieu.add(new SuatChieu(10, "10:30", "English", "10/06/2024", "VietSub", 100000, 1, 1,2));
        dsSuatChieu.add(new SuatChieu(11, "10:30", "English", "10/06/2024", "VietSub", 100000, 1, 1,4));
    }

    private void setDataIntent() {
        Intent intent = getIntent();
        phim = (Phim) intent.getSerializableExtra("movie");
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
        startActivity(intent);
    }

}