package com.example.datvexemphim.Activity.ForAll;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.datvexemphim.API.interceptor.TokenStorage;
import com.example.datvexemphim.Adapter.PhimFragment.GheNgoiAdapter;
import com.example.datvexemphim.Model.CreateOrder;
import com.example.datvexemphim.Model.Ghe;
import com.example.datvexemphim.Model.HoaDon;
import com.example.datvexemphim.Model.Phim;
import com.example.datvexemphim.Model.SuatChieu;
import com.example.datvexemphim.Model.Ve;
import com.example.datvexemphim.R;
import com.example.datvexemphim.Services.AuthenticationService;
import com.example.datvexemphim.Services.HoaDonService;
import com.example.datvexemphim.Services.VeService;
import com.example.datvexemphim.Setting.SpaceItemDecoration;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        //ZaloPay
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        ZaloPaySDK.init(2554, Environment.SANDBOX);

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
        VeService.getAllVe(listVe, gheAdapter);
    }

    public void taoHoaDon() {
        listHoaDon = new ArrayList<>();
        HoaDonService.getAllHoaDon(listHoaDon, gheAdapter);
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
        tvDate.setText(suat.getNgayChieu().substring(0,10));
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
                            double totalDouble = Double.parseDouble(tvTotal.getText().toString());
                            String total = String.valueOf((int) totalDouble);
                            JSONObject data = orderApi.createOrder(total);
                            String code = data.getString("return_code");
                            if (code.equals("1")) {
                                String token = data.getString("zp_trans_token");
                                ZaloPaySDK.getInstance().payOrder(ChonGhe.this, token, "demozpdk://app", new PayOrderListener() {
//                                    @Override
//                                    public void onPaymentSucceeded(String s, String s1, String s2) {
//                                        Toast.makeText(ChonGhe.this, "Thanh toán thành công!", Toast.LENGTH_SHORT).show();
////                                        dialog.dismiss();
//                                    }
//
//                                    @Override
//                                    public void onPaymentCanceled(String s, String s1) {
//                                        Toast.makeText(ChonGhe.this, "Huy thanh toan!", Toast.LENGTH_SHORT).show();
//
//                                    }
//
//                                    @Override
//                                    public void onPaymentError(ZaloPayError zaloPayError, String s, String s1) {
//                                        Toast.makeText(ChonGhe.this, "Thanh toan loi!", Toast.LENGTH_SHORT).show();
//
//                                    }
                                    @Override
                                    public void onPaymentSucceeded(final String transactionId, final String transToken, final String appTransID) {
                                        Toast.makeText(ChonGhe.this, "Thanh toán thành công!", Toast.LENGTH_SHORT).show();
//                                        for(Ghe ghe:gheChon){
//                                            HoaDon hd = new HoaDon(listHoaDon.size()+1, 1);
//                                            listHoaDon.add((hd));
//                                            Ve ve = new Ve(listVe.size()+1, ghe.getIdGhe(), suat.getIdSuatChieu(), hd.getIdhoadon());
//                                            listVe.add(ve);
//                                        }

                                        Map<String, Object> data = new HashMap<>();
                                        data.put("idGhe", getIdGheList(gheChon));
                                        data.put("suatChieu", suat.getIdSuatChieu());
                                        data.put("username", TokenStorage.getAccessToken());
                                        VeService.addVe(data);

                                        taoVe();
                                        taoHoaDon();

                                        gheAdapter.setData(listGhe, listVe, listHoaDon);
                                        btnBuy.setEnabled(false);
                                        gheChon.clear();
                                        gheAdapter.setSize();
                                        dialog.dismiss();
                                    }

                                    @Override
                                    public void onPaymentCanceled(String zpTransToken, String appTransID) {
                                        Toast.makeText(ChonGhe.this, "Thanh toán bị hủy!", Toast.LENGTH_SHORT).show();
                                        gheAdapter.setData(listGhe, listVe, listHoaDon);
                                        btnBuy.setEnabled(false);
                                        gheChon.clear();
                                        gheAdapter.setSize();
                                        dialog.dismiss();
                                    }

                                    @Override
                                    public void onPaymentError(ZaloPayError zaloPayError, String zpTransToken, String appTransID) {
                                        Toast.makeText(ChonGhe.this, "Thanh toán thất bại!", Toast.LENGTH_SHORT).show();
                                        gheAdapter.setData(listGhe, listVe, listHoaDon);
                                        btnBuy.setEnabled(false);
                                        gheChon.clear();
                                        gheAdapter.setSize();
                                        dialog.dismiss();
                                    }
                                });
                            } else{
                                Toast.makeText(ChonGhe.this, "Không thể khởi tạo đơn hàng, xin lỗi quy khách vì sự cố!!" + code, Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception ignored) {
                        }
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

//    private void paymentSuccess(String transactionId) {
//        if(viewModel.getIsRoundTrip().getValue()) {
//            viewModel.orderRoundTripTicket(transactionId);
//        }
//        else {
//            viewModel.orderOneWayTicket(transactionId);
//        }
//    }

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
            if(!gheChon.isEmpty()){
                btnBuy.setEnabled(true);
            }
        }else{
            System.out.println("hwllo");
        }
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ZaloPaySDK.getInstance().onResult(intent);
    }
    public List<Integer> getIdGheList(List<Ghe> gheList) {
        return gheList.stream()
                .map(Ghe::getIdGhe)
                .collect(Collectors.toList());
    }
}