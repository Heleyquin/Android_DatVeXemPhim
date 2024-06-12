package com.example.datvexemphim.Activity.PhimFragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datvexemphim.Adapter.PhimFragment.MoviesAdapter;
import com.example.datvexemphim.Model.Phim;
import com.example.datvexemphim.Model.SuatChieu;
import com.example.datvexemphim.R;
import com.example.datvexemphim.Services.PhimService;
import com.example.datvexemphim.Services.SuatChieuService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements MoviesAdapter.ItemInterface{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView rvMainDisplay;
    private MoviesAdapter adapter;
    private List<Phim> dataFul, dataFil;
    private List<SuatChieu> dsSuat;
    private SearchView svSearch;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        adapter = new MoviesAdapter(this, getContext());
        return inflater.inflate(R.layout.fragment_phim, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        svSearch = view.findViewById(R.id.SearchBar);
        rvMainDisplay = view.findViewById(R.id.rvMainDisplay);

        tempData();
        setData();
        adapter.setData(dataFul, dsSuat);
        svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
                dataFul.clear();
                dataFul.addAll(dataFil.stream()
                        .filter(phim -> phim.getTen().toLowerCase().contains(s.toLowerCase()))
                        .collect(Collectors.toList()));
                adapter.notifyDataSetChanged();
            }
        });

    }
    private void setData() {
        rvMainDisplay.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMainDisplay.setAdapter(adapter);
    }
    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(view.getContext(), Movie_Detail.class);

        intent.putExtra("movie", adapter.getItem(position));
        intent.putExtra("phim",(Serializable) adapter.getAll());
        startActivity(intent);
    }
    public void tempData(){
        dataFil = new ArrayList<>();
        dataFul = new ArrayList<>();
        PhimService.getAllPhim(dataFil, adapter, dataFul);
        dsSuat = new ArrayList<>();
//        dsSuat.add(new SuatChieu(1, "10:30", "English", "05/06/2024", "VietSub", 100000, 1, 1, 1));
//        dsSuat.add(new SuatChieu(2, "12:30", "English", "05/06/2024", "VietSub", 100000, 1, 1, 1));
//        dsSuat.add(new SuatChieu(3, "13:30", "English", "06/06/2024", "VietSub", 100000, 1, 1, 1));
//        dsSuat.add(new SuatChieu(4, "10:30", "English", "05/06/2024", "VietSub", 100000, 1, 1, 2));
//        dsSuat.add(new SuatChieu(5, "10:30", "English", "05/06/2024", "VietSub", 100000, 1, 3, 2));
//        dsSuat.add(new SuatChieu(6, "16:30", "English", "06/06/2024", "VietSub", 100000, 1, 1, 3));
//        dsSuat.add(new SuatChieu(7, "10:30", "English", "07/06/2024", "VietSub", 100000, 1, 2, 2));
//        dsSuat.add(new SuatChieu(8, "10:30", "English", "08/06/2024", "VietSub", 100000, 1, 1, 2));
//        dsSuat.add(new SuatChieu(9, "10:30", "English", "09/06/2024", "VietSub", 100000, 1, 1, 2));
//        dsSuat.add(new SuatChieu(10, "10:30", "English", "10/06/2024", "VietSub", 100000, 1, 1, 2));
//        dsSuat.add(new SuatChieu(11, "10:30", "English", "10/06/2024", "VietSub", 100000, 1, 1, 4));
        SuatChieuService.getAllSuatChieu(dsSuat, adapter);
    }
}