package com.example.android.challenge.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.challenge.DetailActivity;
import com.example.android.challenge.FinderActivity;
import com.example.android.challenge.Models.Developer;
import com.example.android.challenge.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.R.attr.start;

/**
 * Created by adeyemogabriel on 8/30/17.
 */

public class FinderAdapter extends RecyclerView.Adapter<FinderAdapter.FinderViewHolder> {
    private List<Developer> developerList;
    public FinderAdapter (List<Developer> developers) {
        this.developerList = developers;
    }

    @Override
    public FinderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new FinderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FinderViewHolder  holder, int position) {
        //to set Text and Image
        final Developer currentDeveloper = developerList.get(position);
        final Context context = holder.itemView.getContext();



        Picasso.with(context)
                .load(currentDeveloper.getAvatar_url())
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(holder.imageView);
        holder.textView.setText(currentDeveloper.getLogin());
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("developer_select", currentDeveloper);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return developerList.size();
    }

    public class FinderViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;

        public FinderViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.username_text_view);
            imageView = (ImageView) itemView.findViewById(R.id.avatar_image_view);
        }

    }
}
