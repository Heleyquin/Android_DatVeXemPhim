package com.example.datvexemphim.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.example.datvexemphim.Model.HoaDon;
import com.example.datvexemphim.Model.Phim;
import com.example.datvexemphim.Model.SuatChieu;
import com.example.datvexemphim.Model.Ve;
import com.example.datvexemphim.R;
import com.example.datvexemphim.Setting.SpaceItemDecoration;

import java.util.ArrayList;
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
    private List<Ghe> gheChon;
    private List<Ve> listVe;
    private List<HoaDon> listHoaDon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chon_ghe);

        gheAdapter = new GheNgoiAdapter(this);
        gheChon = new ArrayList<>();

        taoVe();
        taoHoaDon();

        getDataIntent();
        setControl();
        setData();
        setAdapterRV();
        setEvent();
        gheAdapter.setData(listGhe, listVe, listHoaDon);

//        Toast.makeText(this, String.valueOf(gheAdapter.), Toast.LENGTH_LONG).show();
    }
    public void taoVe() {
        listVe = new ArrayList<>();
        listVe.add(new Ve(1, 1, 1, 1));
        listVe.add(new Ve(2, 2, 1, 1));
        listVe.add(new Ve(3, 13, 1, 2));
        listVe.add(new Ve(4, 4, 1, 3));
        listVe.add(new Ve(5, 10, 1, 4));
    }

    public void taoHoaDon() {
        listHoaDon = new ArrayList<>();
        listHoaDon.add(new HoaDon(1, 1));
        listHoaDon.add(new HoaDon(2, 2));
        listHoaDon.add(new HoaDon(3, 1));
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

        btnBuy.setEnabled(false);
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

    public void setEvent(){
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialog();
            }
        });
    }
    private void showConfirmationDialog() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Bạn có chắc chắn với lựa chọn của mình??");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Mua vé",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        for(Ghe ghe:gheChon){
                            HoaDon hd = new HoaDon(listHoaDon.size()+1, 1);
                            listHoaDon.add((hd));
                            Ve ve = new Ve(listVe.size()+1, ghe.getIdGhe(), suat.getIdSuatChieu(), hd.getIdhoadon());
                            listVe.add(ve);
                        }
                        gheAdapter.setData(listGhe, listVe, listHoaDon);
                        btnBuy.setEnabled(false);
                        gheChon.clear();
                        gheAdapter.setSize();
                        dialog.dismiss();
                    }
                });

        builder1.setNegativeButton(
                "Huỷ",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
    @Override
    public void onItemClick(int id_ghe, int status) {
        if(status == 0){
            double total = Double.parseDouble(tvTotal.getText().toString());
            Log.e("gia", String.valueOf(total));
            total = total - suat.getGia();
            tvTotal.setText(String.valueOf(total));
            for(Ghe ghe:listGhe){
                if (ghe.getIdGhe() == id_ghe){
                    gheChon.remove(ghe);
                }
            }
            if(gheChon.size() <= 0){
                btnBuy.setEnabled(false);
            }
        } else if (status == 1) {
            double total = Double.parseDouble(tvTotal.getText().toString());
            Log.e("gia", String.valueOf(total));
            total = total + suat.getGia();
            tvTotal.setText(String.valueOf(total));
            for(Ghe ghe:listGhe){
                if (ghe.getIdGhe() == id_ghe){
                    gheChon.add(ghe);
                }
            }
            if(gheChon.size() > 0){
                btnBuy.setEnabled(true);
            }
        }else{

        }
    }
}