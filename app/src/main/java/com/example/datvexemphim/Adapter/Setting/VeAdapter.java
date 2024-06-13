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
import com.example.datvexemphim.Model.Ve;
import com.example.datvexemphim.Model.Ve;
import com.example.datvexemphim.R;

import java.util.List;


public class VeAdapter extends RecyclerView.Adapter<VeAdapter.ItemViewHolder> {
    private List<Ve> dsVe;
    private Context context;
    public VeAdapter(Context context) {
        this.context = context;
    }
    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Ve> dsVe){
        this.dsVe = dsVe;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public VeAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hoadon, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VeAdapter.ItemViewHolder holder, int position) {
        Ve ve = dsVe.get(position);

        holder.tvMaHD.setText(String.valueOf(ve.getIdHoaDon().getIdhoadon()));
        holder.tvSoVe.setText(String.valueOf(ve.getIdVe()));
        holder.tvTenPhim.setText(ve.getIdSuatChieu().getId_phim().getTen());
        holder.tvTenRap.setText(ve.getId_ghe().getId_phong().getId_rap().getTenRap());
        holder.tvTien.setText(String.valueOf(ve.getIdSuatChieu().getGia()));

//        holder.tvTenRap.
//        holder.tvTien.setText();
    }

    @Override
    public int getItemCount() {
        if (dsVe != null) {
            return dsVe.size();
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
