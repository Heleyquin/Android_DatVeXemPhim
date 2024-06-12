package com.example.datvexemphim.Activity.ForAll;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.datvexemphim.Adapter.PhimFragment.GheNgoiAdapter;
import com.example.datvexemphim.Model.Ghe;
import com.example.datvexemphim.Model.HoaDon;
import com.example.datvexemphim.Model.Phim;
import com.example.datvexemphim.Model.SuatChieu;
import com.example.datvexemphim.Model.Ve;
import com.example.datvexemphim.R;
import com.example.datvexemphim.Setting.SpaceItemDecoration;
import com.example.datvexemphim.zaloPay.Api.CreateOrder;
import com.example.datvexemphim.zaloPay.Constant.AppInfo;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;
import vn.zalopay.sdk.listeners.PayOrderListener;


public class ChonGhe extends AppCompatActivity implements GheNgoiAdapter.ItemInterface{

    private SuatChieu suat;
    private List<Ghe> listGhe;
    private Phim phim;
    private RecyclerView rvGhe;
    private ImageView ivImage;
    private TextView tvTenPhim, tvSub, tvDate, tvTime, tvTotal, tvPhong;
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

        //Zalo
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // ZaloPay SDK Init
        ZaloPaySDK.init(AppInfo.APP_ID, Environment.SANDBOX);

        taoVe();
        taoHoaDon();


        getDataIntent();
        setControl();
        setData();
        setAdapterRV();
        setEvent();
        gheAdapter.setData(listGhe, listVe, listHoaDon, suat);


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
        tvPhong = findViewById(R.id.tvPhong);
        btnBuy = findViewById(R.id.btnBuy);
        tvDate = findViewById(R.id.tvDate);

        btnBuy.setVisibility(View.GONE);
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
        tvPhong.setText(String.valueOf(suat.getId_phong()));
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
                        CreateOrder orderApi = new CreateOrder();

                        try {
                            double price = Double.parseDouble(tvTotal.getText().toString());
                            String total = String.valueOf((int)price);
                            JSONObject data = orderApi.createOrder(total);
                            String code = data.getString("return_code");
                            Toast.makeText(getApplicationContext(), "return_code: " + code, Toast.LENGTH_LONG).show();
                            if (code.equals("1")) {
                                String token = data.getString("zp_trans_token");
                                ZaloPaySDK.getInstance().payOrder(ChonGhe.this, token, "demozpdk://app", new PayOrderListener() {
                                    @Override
                                    public void onPaymentSucceeded(String s, String s1, String s2) {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                for (Ghe ghe : gheChon) {
                                                    HoaDon hd = new HoaDon(listHoaDon.size() + 1, 1);
                                                    listHoaDon.add((hd));
                                                    Ve ve = new Ve(listVe.size() + 1, ghe.getIdGhe(), suat.getIdSuatChieu(), hd.getIdhoadon());
                                                    listVe.add(ve);
                                                }
                                                gheAdapter.setData(listGhe, listVe, listHoaDon, suat);
                                                btnBuy.setVisibility(View.GONE);
                                                gheChon.clear();
                                                gheAdapter.setSize();
                                                tvTotal.setText("0");
                                                new AlertDialog.Builder(ChonGhe.this)
                                                        .setTitle("Thanh toán thành công")
                                                        .setMessage(String.format("TransactionId: %s - TransToken: %s", s, s1))
                                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                            }
                                                        })
                                                        .setNegativeButton("Cancel", null).show();
                                            }

                                        });
                                    }

                                    @Override
                                    public void onPaymentCanceled(String s, String s1) {
                                        new AlertDialog.Builder(ChonGhe.this)
                                                .setTitle("Thanh toán bị huỷ")
                                                .setMessage(String.format("zpTransToken: %s \n", s))
                                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                    }
                                                })
                                                .setNegativeButton("Cancel", null).show();
                                    }

                                    @Override
                                    public void onPaymentError(ZaloPayError zaloPayError, String s, String s1) {
                                        new AlertDialog.Builder(ChonGhe.this)
                                                .setTitle("Thanh toán thất bại")
                                                .setMessage(String.format("ZaloPayErrorCode: %s \nTransToken: %s", zaloPayError.toString(), s))
                                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                    }
                                                })
                                                .setNegativeButton("Cancel", null).show();
                                    }
                                });
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

        builder1.setNegativeButton(
                "Huỷ",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.e("tranID", "Huy");
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
                btnBuy.setVisibility(View.GONE);
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
            if(!gheChon.isEmpty()){
                btnBuy.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    protected void onNewIntent(@NonNull Intent intent) {
        super.onNewIntent(intent);
        ZaloPaySDK.getInstance().onResult(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}