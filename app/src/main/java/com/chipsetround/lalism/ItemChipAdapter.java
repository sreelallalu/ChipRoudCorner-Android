package com.chipsetround.lalism;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chipsetround.lalism.data.Chip;
import com.chipsetround.lalism.data.ChipChangedObserver;
import com.chipsetround.lalism.data.ChipDataSource;

/**
 * Copyright © 2017 Tyler Suehr
 *
 * @author Tyler Suehr
 * @version 1.0
 */
class ItemChipAdapter extends RecyclerView.Adapter<ItemChipAdapter.Holder> implements ChipChangedObserver {
    private final OnContactClickListener listener;
    private ChipDataSource chipDataSource;


    ItemChipAdapter(OnContactClickListener listener) {
        this.listener = listener;
        Log.e("clickedchip ","click");
    }

    @Override
    public int getItemCount() {
        return chipDataSource == null ? 0 : chipDataSource.getFilteredChips().size();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.adapter_filtereable_item, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Chip chip = chipDataSource.getFilteredChips().get(position);
        holder.text.setText(chip.getTitle());
        holder.subtitle.setText(chip.getSubtitle());
        if (chip.getAvatarUri() != null) {
            holder.image.setImageURI(chip.getAvatarUri());
        } else if (chip.getAvatarDrawable() != null) {
            holder.image.setImageDrawable(chip.getAvatarDrawable());
        } else {
            holder.image.setImageDrawable(null);
        }
    }

    @Override
    public void onChipDataSourceChanged() {

        notifyDataSetChanged();
    }

    void setChipDataSource(ChipDataSource chipDataSource) {
        this.chipDataSource = chipDataSource;

        notifyDataSetChanged();
    }

    interface OnContactClickListener {
        void onContactClicked(ChipItems chip);
    }


    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView text, subtitle;
        ImageView image;

        Holder(View v) {
            super(v);
            this.text = v.findViewById(R.id.title);
            this.subtitle = v.findViewById(R.id.subtitle);
            this.image = v.findViewById(R.id.image);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            final Chip clickedChip = chipDataSource.getFilteredChip(getAdapterPosition());

        }
    }
}