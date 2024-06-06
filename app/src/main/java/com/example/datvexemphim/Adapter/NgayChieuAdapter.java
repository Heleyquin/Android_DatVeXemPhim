package com.example.datvexemphim.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datvexemphim.Model.Phim;
import com.example.datvexemphim.Model.SuatChieu;
import com.example.datvexemphim.R;

import java.time.temporal.Temporal;
import java.util.List;

public class NgayChieuAdapter extends RecyclerView.Adapter<NgayChieuAdapter.ItemViewHolder> {
    private final ItemInterface itemInterface;
    private List<SuatChieu> data;

    public NgayChieuAdapter(ItemInterface itemInterface) {
        this.itemInterface = itemInterface;
    }
    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<SuatChieu> list) {
        this.data = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public NgayChieuAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_date, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NgayChieuAdapter.ItemViewHolder holder, int position) {
        SuatChieu date =data.get(position);

        holder.tvDate.setText(date.getNgayChieu());
        holder.tvTime.setText(date.getGioBatDau());
        holder.tvSub.setText(date.getSub());
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

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        private final TextView tvDate;
        private final TextView tvTime;
        private final TextView tvSub;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDate = itemView.findViewById(R.id.tvDate);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvSub = itemView.findViewById(R.id.tvSub);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemInterface.onItemClick(v, getAdapterPosition());
                }
            });
        }
    }
}
