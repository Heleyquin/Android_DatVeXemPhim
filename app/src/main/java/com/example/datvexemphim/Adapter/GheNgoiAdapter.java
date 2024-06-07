package com.example.datvexemphim.Adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datvexemphim.Model.Ghe;
import com.example.datvexemphim.Model.HoaDon;
import com.example.datvexemphim.Model.Phim;
import com.example.datvexemphim.Model.Phong;
import com.example.datvexemphim.Model.SuatChieu;
import com.example.datvexemphim.Model.Ve;
import com.example.datvexemphim.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GheNgoiAdapter extends RecyclerView.Adapter<GheNgoiAdapter.ItemViewHolder> {
    List<Ghe> listGhe;
    List<Ve> listVe;
    List<HoaDon> listHoaDon;
    private ItemInterface itemInterface;

    public GheNgoiAdapter(ItemInterface itemInterface) {
        this.itemInterface = itemInterface;
    }

    @NonNull
    @Override
    public GheNgoiAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_seat, parent, false);
        return new ItemViewHolder(v);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Ghe> list) {
        this.listGhe = list;
        taoVe();
        taoHoaDon();
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull GheNgoiAdapter.ItemViewHolder holder, int position) {
        Ghe ghe = listGhe.get(position);
        Set<Integer> temp = listVe.stream()
                .map(Ve::getId_ghe)
                .collect(Collectors.toSet());
        if (temp.contains(ghe.getIdGhe())) {
            holder.ivGhe.setBackgroundResource(R.drawable.baseline_event_seat_24_sold);
        } else {
            holder.ivGhe.setBackgroundResource(R.drawable.baseline_event_seat_24_none);
        }
//        holder.ivGhe.setBackgroundResource(R.drawable.baseline_event_seat_24_none);
        holder.tvGhe.setText(String.valueOf(ghe.getIdGhe()));

    }

    public Ghe getItem(int pos){
        return listGhe.get(pos);
    }
    @Override
    public int getItemCount() {
        if(listGhe != null){
            return listGhe.size();
        }
        return 0;
    }

    public interface ItemInterface {
        void onItemClick(SuatChieu suat, List<Ghe> listGhe, Phim phim);
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

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvGhe;
        private final ImageView ivGhe;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            tvGhe = itemView.findViewById(R.id.tvGhe);
            ivGhe = itemView.findViewById(R.id.ivGhe);

            ivGhe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });


        }
    }
}
