package com.ndroid.libraryapp;

import android.content.Context;
import android.media.Image;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerBookAdapter extends RecyclerView.Adapter<RecyclerBookAdapter.ViewHolder> {

    private ArrayList<Book> bookArrayList;

    private OnBookClickListener onBookClickListener;

    public interface OnBookClickListener {
        void onBookClick(int position);
    }

    public void setOnBookClickListener(OnBookClickListener onBookClickListener) {
        this.onBookClickListener = onBookClickListener;
    }

    public void setBookArrayList(ArrayList<Book> bookArrayList) {
        this.bookArrayList = bookArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Book book = bookArrayList.get(position);
        Picasso.get().load(book.getImage()).into(holder.ivCover);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBookClickListener.onBookClick(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return this.bookArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivCover;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCover = itemView.findViewById(R.id.ivCover);

        }


    }
}
