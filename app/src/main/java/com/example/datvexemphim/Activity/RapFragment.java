package com.example.datvexemphim.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.RecoverySystem;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datvexemphim.Adapter.RapFragment.RapAdapter;
import com.example.datvexemphim.Model.Ghe;
import com.example.datvexemphim.Model.Phim;
import com.example.datvexemphim.Model.Phong;
import com.example.datvexemphim.Model.Rap;
import com.example.datvexemphim.Model.SuatChieu;
import com.example.datvexemphim.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RapFragment extends Fragment implements RapAdapter.ItemInterface{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView rvRap;
    private RapAdapter rapAdapter;
    private List<Rap> dsRap;
    private List<Phim> dsPhim;
    private List<SuatChieu> dsSuatChieu;
    private List<Phong> dsPhong;
    private List<Ghe> dsGhe;

    public RapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RapFragment newInstance(String param1, String param2) {
        RapFragment fragment = new RapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rapAdapter = new RapAdapter(this, getContext());

        return inflater.inflate(R.layout.fragment_rap, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        taoRap();
        taoPhong();
        taoGhe();
        taoPhim();
        taoSuatChieu();

        rvRap = view.findViewById(R.id.rvRap);
        setData();
        rapAdapter.setData(dsRap);

    }

    private void setData() {
        rvRap.setLayoutManager(new LinearLayoutManager(getContext()));
        rvRap.setAdapter(rapAdapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(view.getContext(), PhimByRap.class);
        Rap rap = dsRap.get(position);
        List<SuatChieu> suatByRap = getSuatChieuByRap(rap.getIdRap());
        List<Phim> phimBySuat = getPhimBySuat(suatByRap);

        intent.putExtra("suats", (Serializable) suatByRap);
        intent.putExtra("ghes", (Serializable) suatByRap);
        intent.putExtra("phims",(Serializable) phimBySuat);
        intent.putExtra("rap", rapAdapter.getItem(position));

//        intent.putExtra("phim",(Serializable) adapter.getAll());
//        startActivity(intent);
        startActivity(intent);
    }

    private List<SuatChieu> getSuatChieuByRap(int idRap) {
        List<SuatChieu> listSuatChieuOfRap;
        List<Phong> phongOfRap = new ArrayList<>();
        for(Phong p : dsPhong){
            if(p.getId_rap() == idRap){
                phongOfRap.add(p);
            }
        }
        Set<Integer> phongIds = phongOfRap.stream()
                .map(Phong::getIdPhong)
                .collect(Collectors.toSet());
        listSuatChieuOfRap = dsSuatChieu.stream()
                .filter(suat -> phongIds.contains(suat.getId_phong()))
                .collect(Collectors.toList());
        return listSuatChieuOfRap;
    }
    private List<Phim> getPhimBySuat(@NonNull List<SuatChieu> suatByRap){
        List<Phim> phimByRap;
        Set<Integer> suatIdPhims = suatByRap.stream()
                .map(SuatChieu::getId_phim)
                .collect(Collectors.toSet());
        phimByRap = dsPhim.stream()
                .filter(phim -> suatIdPhims.contains(phim.getIdPhim()))
                .collect(Collectors.toList());
        return phimByRap;
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
    private void taoPhim() {
        dsPhim = new ArrayList<>();
        dsPhim.add(new Phim(1, "https://files.betacorp.vn/media%2fimages%2f2024%2f05%2f28%2f310524%2Dgarfield%2D150640%2D280524%2D95.jpg", "Phim1", "QuocGia", "29/06/2015","Trang thai", 102, "Mo ta", true, 1));
        dsPhim.add(new Phim(2, "https://files.betacorp.vn/media%2fimages%2f2024%2f04%2f24%2f240524%2Ddraft%2Ddoraemon%2D170958%2D240424%2D90.png", "Phim2", "QuocGia", "03/05/2024", "Trang thai", 95, "Mo ta", true, 1));
        dsPhim.add(new Phim(3, "https://files.betacorp.vn/media%2fimages%2f2024%2f05%2f27%2f400x633%2D7%2D151139%2D270524%2D46.jpg", "Phim3", "QuocGia", "23/04/2024", "Trang thai", 120, "Mo ta", false, 1));
        dsPhim.add(new Phim(4, "https://files.betacorp.vn/media%2fimages%2f2024%2f05%2f24%2f400x633%2D6%2D103906%2D240524%2D41.jpg", "Phim4", "QuocGia", "22/04/2024", "Trang thai", 84, "Mo ta", true, 1));
        dsPhim.add(new Phim(5, "https://files.betacorp.vn/media%2fimages%2f2024%2f05%2f28%2f070624%2Dsneak%2Dmong%2Dvuot%2D150957%2D280524%2D53.jpg", "Phim5", "QuocGia", "11/04/2024", "Trang thai", 99, "Mo ta", true, 1));
    }
    public void taoRap(){
        dsRap = new ArrayList<>();
        dsRap.add(new Rap(1, "Rạp Hồ Chí Minh", "Q9, Tp.Thu Duc, TP.HCM"));
        dsRap.add(new Rap(2, "Rạp Hà Nội", "Q.Hoàng Mai, P.Hai Bà Trưng, Hà Nội"));
        dsRap.add(new Rap(3, "Rạp Thanh Hoá", "P.Tào Xuyên, Tp.Thanh Hoá, Tỉnh Thanh Hoá"));
    }
}