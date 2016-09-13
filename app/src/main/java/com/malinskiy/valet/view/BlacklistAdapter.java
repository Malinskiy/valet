package com.malinskiy.valet.view;

import com.malinskiy.valet.R;
import com.malinskiy.valet.model.Blacklist;
import com.malinskiy.valet.model.Entry;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BlacklistAdapter extends RecyclerView.Adapter<BlacklistAdapter.ViewHolder> {

    private final List<Entry> list;

    public BlacklistAdapter(Blacklist blacklist) {
        this.list = blacklist.toList();
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_blacklist, parent, false);
        return new ViewHolder(view);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        holder.titleView.setText(list.get(position).packageName);
        holder.subtitleView.setText(list.get(position).description);
    }

    @Override public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title) TextView titleView;
        @BindView(R.id.subtitle) TextView subtitleView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
