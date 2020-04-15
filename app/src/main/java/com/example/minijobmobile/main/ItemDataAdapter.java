package com.example.minijobmobile.main;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minijobmobile.R;



import java.util.ArrayList;

public class ItemDataAdapter extends RecyclerView.Adapter<ItemDataAdapter.ItemDataViewHolder> {

    private ArrayList<Item> items = new ArrayList<>();
    private Context context;

    public void setOnNoteListener(OnNoteListener onNoteListener) {
        this.onNoteListener = onNoteListener;
    }

    private OnNoteListener onNoteListener = null;

    @NonNull
    @Override
    public ItemDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_adapter, parent, false);
        return new ItemDataViewHolder(itemView, onNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemDataViewHolder holder, int position) {
        Item currentItem = items.get(position);
        holder.title.setText(currentItem.getName());
        holder.title.setMaxWidth(500);
        holder.title.setSingleLine(false);
        holder.address.setText((currentItem.getAddress()));
        holder.address.setMaxWidth(500);
        holder.address.setSingleLine(false);
        StringBuilder sb = new StringBuilder();
        for (String str : currentItem.getKeywords()) {
            sb.append(str).append(" ");
        }
        sb.setLength(40);
        holder.keywords.setText(sb.append("...").toString() );
        if (currentItem.isFavorite()) {
            holder.favButton.setBackground(context.getResources().getDrawable(R.drawable.ic_fav_red));
        }
        Item.loadImage(holder.imageView, currentItem.getImage_url());
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public Item getItem(int pos) {
        return items.get(pos);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    class ItemDataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView title;
        private TextView keywords;
        private TextView address;
        private ImageView imageView;
        private Button favButton;
        OnNoteListener onNoteListener;

        public ItemDataViewHolder(View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            title = itemView.findViewById(R.id.txtView_title);
            keywords = itemView.findViewById(R.id.txtView_keywords);
            address = itemView.findViewById(R.id.txtView_location);
            imageView = itemView.findViewById(R.id.imgView_icon);
            favButton = itemView.findViewById(R.id.fav_button);
            favButton.setOnClickListener( v -> {
                onNoteListener.onHeartClick(getAdapterPosition(),
                        ItemDataAdapter.this, favButton);
            });
            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition(), ItemDataAdapter.this);
        }
    }

    public interface OnNoteListener {
        void onNoteClick(int position,  ItemDataAdapter adapter);
        void onHeartClick(int position, ItemDataAdapter adapter, Button heart);
    }
}
