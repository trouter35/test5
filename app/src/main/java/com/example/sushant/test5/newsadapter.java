package com.example.sushant.test5;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.Collections;
import java.util.List;

public class newsadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<news> data= Collections.emptyList();

    // create constructor to initialize context and data sent from MainActivity
    public newsadapter(Context context, List<news> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.card_layout, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        MyHolder myHolder= (MyHolder) holder;
        news current=data.get(i);
        myHolder.title.setText(current.title);
        //myHolder.photo.setText("Username: " + current.photo);

        Glide.with(context).load(current.image).into(myHolder.image);

        //myHolder.username.setText("Username: " + current.username);
        //myHolder.email.setText("Email: " + current.email);
        myHolder.shortdesc.setText(current.shortdesc);
    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        TextView title, shortdesc;
        ImageView image;
        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.textViewTitle);
            image = (ImageView) itemView.findViewById(R.id.image);
            shortdesc = (TextView) itemView.findViewById(R.id.shortdesc);

        }

    }
}
