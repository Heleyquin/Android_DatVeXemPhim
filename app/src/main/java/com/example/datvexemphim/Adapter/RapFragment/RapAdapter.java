package com.example.datvexemphim.Adapter.RapFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.datvexemphim.Model.Rap;
import com.example.datvexemphim.R;
import java.util.List;

public class RapAdapter extends RecyclerView.Adapter<RapAdapter.ItemViewHolder> {
    private List<Rap> data;
    private Context context;
    private ItemInterface itemInterface;
    public RapAdapter(ItemInterface itemInterface, Context context){
        this.itemInterface = itemInterface;
        this.context = context;
    }
    @NonNull
    @Override
    public RapAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_rap, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RapAdapter.ItemViewHolder holder, int position) {
        Rap rap = data.get(position);

        holder.tenRap.setText(rap.getTenRap());
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Rap> list){
        this.data = list;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    public Rap getItem(int position) {
        return data.get(position);
    }

    public interface ItemInterface {
        void onItemClick(View view, int position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView tenRap;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            tenRap = itemView.findViewById(R.id.tvTenRap);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemInterface.onItemClick(v, getAdapterPosition());
                }
            });
        }
    }
}
