package com.example.newsapi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import java.util.List;

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private int size;
    private List<News> items;
    public RecyclerAdapter(int size,List<News>items) {
        this.size= size;
        this.items=items;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.element_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder viewHolder, int i) {
        viewHolder.title.setText(items.get(i).getTitle());
        viewHolder.description.setText(items.get(i).getDescriprion());
        Picasso.get().load(items.get(i).getUrl()).resize(325,264).centerCrop().into(viewHolder.photo);
    }

    @Override
    public int getItemCount() {
        return size;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, description;
        private ImageView photo;
        public ViewHolder(View view) {
            super(view);
            title=view.findViewById(R.id.tv_title);
            description=view.findViewById(R.id.tv_desription);
            photo=view.findViewById(R.id.img_place);
        }
    }
}
