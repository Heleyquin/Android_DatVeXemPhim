package com.example.datvexemphim.Activity.RapFragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datvexemphim.Adapter.RapFragment.ByRapMoviesAdapter;
import com.example.datvexemphim.Model.Ghe;
import com.example.datvexemphim.Model.Phim;
import com.example.datvexemphim.Model.Phong;
import com.example.datvexemphim.Model.Rap;
import com.example.datvexemphim.Model.SuatChieu;
import com.example.datvexemphim.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PhimByRap extends AppCompatActivity implements ByRapMoviesAdapter.ItemInterface{

    private List<Phim> dsPhim, dsPhimFil;
    private List<SuatChieu> dsSuatChieu;
    private List<Ghe> dsGhe;
    private Rap rap;
    private List<Phong> dsPhong;
    private RecyclerView rvPhim;
    private TextView tvTenRap;
    private ByRapMoviesAdapter adapter;
    private SearchView searchBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_phim_by_rap);

        adapter = new ByRapMoviesAdapter(this, getApplicationContext());

        setDataIntent();
        setControl();
        setRV();

        adapter.setData(dsPhimFil, dsSuatChieu, dsGhe, dsPhong, rap);

        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
            @SuppressLint("NotifyDataSetChanged")
            private void filter(String s) {
                dsPhimFil.clear();
                dsPhimFil.addAll(dsPhim.stream()
                        .filter(phim -> phim.getTen().toLowerCase().contains(s.toLowerCase()))
                        .collect(Collectors.toList()));
                adapter.notifyDataSetChanged();
            }
        });

    }
    private void setDataIntent() {
        Intent intent = getIntent();
        dsPhim = (List<Phim>) intent.getSerializableExtra("phims");
        dsGhe = (List<Ghe>) intent.getSerializableExtra("ghes");
        dsSuatChieu = (List<SuatChieu>) intent.getSerializableExtra("suats");
        rap = (Rap) intent.getSerializableExtra("rap");
        dsPhimFil = new ArrayList<>();
        dsPhimFil.addAll(dsPhim);
    }
    private void setControl(){
        rvPhim = findViewById(R.id.rvPhim);
        tvTenRap = findViewById(R.id.tvTenRap);
        searchBar = findViewById(R.id.searchBar);

        tvTenRap.setText(rap.getTenRap());
    }
    private void setRV(){
        rvPhim.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rvPhim.setAdapter(adapter);

    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(view.getContext(), ByRap_Movie_Detail.class);

        intent.putExtra("movie", adapter.getItem(position));
        intent.putExtra("phims",(Serializable) dsPhim);
        intent.putExtra("suats",(Serializable) dsSuatChieu);
        intent.putExtra("ghes",(Serializable) dsGhe);
        intent.putExtra("phongs",(Serializable) dsPhong);
        intent.putExtra("rap", rap);
        startActivity(intent);
    }
}