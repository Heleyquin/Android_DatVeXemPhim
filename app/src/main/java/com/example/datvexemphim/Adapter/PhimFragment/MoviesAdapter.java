package com.example.datvexemphim.Adapter.PhimFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.datvexemphim.Activity.PhimFragment.Movie_Detail;
import com.example.datvexemphim.Model.Phim;
import com.example.datvexemphim.Model.Phong;
import com.example.datvexemphim.Model.SuatChieu;
import com.example.datvexemphim.R;
import com.example.datvexemphim.Services.PhimService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ItemViewHolder> {
    private final ItemInterface itemInterface;
    private List<Phim> data;
    private List<SuatChieu> dsSuat;
    private Context context;

    public MoviesAdapter(ItemInterface itemInterface, Context context) {
        this.itemInterface = itemInterface;
        this.context = context;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Phim> list, List<SuatChieu> dsSuat) {
        this.data = list;
        this.dsSuat = dsSuat;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ItemViewHolder(v);
    }
    public boolean phimCoSuat(int id_Phim){
        for(SuatChieu suat:dsSuat){
            if(suat.getId_phim().getIdPhim() == id_Phim){
                return true;
            }
        }
        return false;

    }
    public Phim getItem(int pos) {
        return data.get(pos);
    }
    public List<Phim> getAll(){
        return data;
    }
    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Phim phim = data.get(position);
        Glide.with(holder.ivImage).load(phim.getAnh()).into(holder.ivImage);
        holder.tvTitle.setText(phim.getTen().toUpperCase());
        if(phim.getMoTa().equals(""))
            holder.tvDecript.setText("Phim này không có mô tả");
        else
            holder.tvDecript.setText(phim.getMoTa());
        holder.tvTime.setText(String.valueOf(phim.getThoiLuong()) + " phút");
        if(!phimCoSuat(phim.getIdPhim())){
            holder.btnDatVe.setVisibility(View.GONE);
        }
        holder.btnDatVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Movie_Detail.class);
                intent.putExtra("movie", getItem(position));
                intent.putExtra("phim",(Serializable) getAll());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }
    public interface ItemInterface {
        void onItemClick(View view, int position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivImage;
        private final TextView tvTitle, tvDecript, tvTime;
        private final Button btnDatVe;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.ivImage = itemView.findViewById(R.id.ivImage);
            this.tvTitle = itemView.findViewById(R.id.tvTitle);
            this.tvDecript = itemView.findViewById(R.id.tvDecript);
            this.tvTime = itemView.findViewById(R.id.tvTime);
            this.btnDatVe = itemView.findViewById(R.id.btnDatVe);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemInterface.onItemClick(v, getAdapterPosition());
                }
            });
        }
    }
}

