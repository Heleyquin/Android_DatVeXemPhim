package com.example.datvexemphim.Adapter.RapFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.datvexemphim.Activity.RapFragment.ByRap_Movie_Detail;
import com.example.datvexemphim.Model.Ghe;
import com.example.datvexemphim.Model.Phim;
import com.example.datvexemphim.Model.Phong;
import com.example.datvexemphim.Model.Rap;
import com.example.datvexemphim.Model.SuatChieu;
import com.example.datvexemphim.R;

import java.io.Serializable;
import java.util.List;

public class ByRapMoviesAdapter extends RecyclerView.Adapter<ByRapMoviesAdapter.ItemViewHolder> {
    private final ItemInterface itemInterface;
    private List<Phim> data;
    private List<SuatChieu> dsSuat;
    private List<Ghe> dsGhe;
    private List<Phong> dsPhong;
    private Rap rap;
    private Context context;

    public ByRapMoviesAdapter(ItemInterface itemInterface, Context context) {
        this.itemInterface = itemInterface;
        this.context = context;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Phim> list, List<SuatChieu> dsSuat, List<Ghe> dsGhe, List<Phong> dsPhong, Rap rap) {
        this.data = list;
        this.dsSuat = dsSuat;
        this.dsGhe = dsGhe;
        this.dsPhong = dsPhong;
        this.rap = rap;
        notifyDataSetChanged();
    }
    public interface ItemInterface {
        void onItemClick(View view, int position);
    }
    public Phim getItem(int pos) {
        return data.get(pos);
    }
    public List<Phim> getAll(){
        return data;
    }
    @NonNull
    @Override
    public ByRapMoviesAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ByRapMoviesAdapter.ItemViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Phim phim = data.get(position);
        Glide.with(holder.ivImage).load(phim.getAnh()).into(holder.ivImage);
        holder.tvTitle.setText(phim.getTen().toUpperCase());
        holder.tvDecript.setText(phim.getMoTa());
        holder.tvTime.setText(String.valueOf(phim.getThoiLuong()) + " phút");
        holder.btnDatVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ByRap_Movie_Detail.class);
                intent.putExtra("movie", getItem(position));
                intent.putExtra("phims",(Serializable) data);
                intent.putExtra("suats",(Serializable) dsSuat);
                intent.putExtra("ghes",(Serializable) dsGhe);
                intent.putExtra("phongs",(Serializable) dsPhong);
                intent.putExtra("rap", rap);
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
