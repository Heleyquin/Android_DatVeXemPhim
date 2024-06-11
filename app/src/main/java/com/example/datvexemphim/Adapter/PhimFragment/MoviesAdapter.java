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
    public void tempData(){

        List<Phim> list = new ArrayList<>();
        PhimService.getAllPhim(list, this);
//        list.add(new Phim(1, "https://files.betacorp.vn/media%2fimages%2f2024%2f05%2f28%2f310524%2Dgarfield%2D150640%2D280524%2D95.jpg", "Phim1", "QuocGia", "29/06/2015","Trang thai", 102, "Mo ta", true, 1));
//        list.add(new Phim(2, "https://files.betacorp.vn/media%2fimages%2f2024%2f04%2f24%2f240524%2Ddraft%2Ddoraemon%2D170958%2D240424%2D90.png", "Phim2", "QuocGia", "03/05/2024", "Trang thai", 95, "Mo ta", true, 1));
//        list.add(new Phim(3, "https://files.betacorp.vn/media%2fimages%2f2024%2f05%2f27%2f400x633%2D7%2D151139%2D270524%2D46.jpg", "Phim3", "QuocGia", "23/04/2024", "Trang thai", 120, "Mo ta", false, 1));
//        list.add(new Phim(4, "https://files.betacorp.vn/media%2fimages%2f2024%2f05%2f24%2f400x633%2D6%2D103906%2D240524%2D41.jpg", "Phim4", "QuocGia", "22/04/2024", "Trang thai", 84, "Mo ta", true, 1));
//        list.add(new Phim(5, "https://files.betacorp.vn/media%2fimages%2f2024%2f05%2f28%2f070624%2Dsneak%2Dmong%2Dvuot%2D150957%2D280524%2D53.jpg", "Phim5", "QuocGia", "11/04/2024", "Trang thai", 99, "Mo ta", true, 1));
//        System.out.print(list.get(12).toString());
        setData(list);
    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ItemViewHolder(v);
    }
    public boolean phimCoSuat(int id_Phim){
        for(SuatChieu suat:dsSuat){
            if(suat.getId_phim() == id_Phim){
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
        holder.tvDecript.setText(phim.getMoTa());
        holder.tvTime.setText(String.valueOf(phim.getThoiLuong()));
//        Log.e("Cove", String.valueOf(phimCoSuat(phim.getIdPhim())));
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

