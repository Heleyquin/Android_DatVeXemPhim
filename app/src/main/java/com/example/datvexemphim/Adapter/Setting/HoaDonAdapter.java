package com.example.datvexemphim.Adapter.Setting;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datvexemphim.Adapter.RapFragment.ByRapMoviesAdapter;
import com.example.datvexemphim.Model.HoaDon;
import com.example.datvexemphim.Model.Ve;
import com.example.datvexemphim.R;

import java.util.List;


public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ItemViewHolder> {
    private List<HoaDon> dsHoaDon;
    private Context context;
    public HoaDonAdapter(Context context) {
        this.context = context;
    }
    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<HoaDon> dsHoaDon){
        this.dsHoaDon = dsHoaDon;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public HoaDonAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hoadon, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonAdapter.ItemViewHolder holder, int position) {
        HoaDon hd = dsHoaDon.get(position);

        holder.tvMaHD.setText(String.valueOf(hd.getIdhoadon()));
//        holder.tvTenRap.
//        holder.tvTien.setText();
    }

    @Override
    public int getItemCount() {
        if (dsHoaDon != null) {
            return dsHoaDon.size();
        }
        return 0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView tvMaHD, tvSoVe, tvTenPhim, tvTenRap, tvTien;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMaHD = itemView.findViewById(R.id.tvMaHD);
            tvSoVe = itemView.findViewById(R.id.tvSoVe);
            tvTenPhim = itemView.findViewById(R.id.tvTenPhim);
            tvTenRap = itemView.findViewById(R.id.tvTenRap);
            tvTien = itemView.findViewById(R.id.tvTien);

        }
    }
}
