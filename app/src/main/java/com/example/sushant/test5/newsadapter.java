package com.example.sushant.test5;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;


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
        final news current=data.get(i);
        myHolder.title.setText(current.title);
        //myHolder.photo.setText("Username: " + current.photo);

        if(current.media_type.contentEquals("image")){
            myHolder.image.setVisibility(View.VISIBLE);
            myHolder.video.setVisibility(View.GONE);
            Glide.with(context).load(current.image).into(myHolder.image);
        }
        else{
            myHolder.image.setVisibility(View.GONE);
            myHolder.video.setVisibility(View.VISIBLE);
            Log.d("MyTag","This is the video code:"+current.image);

            FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();

            YouTubePlayerSupportFragment youtubeFragment = (YouTubePlayerSupportFragment)
                    manager.findFragmentById(R.id.youtube_player_view);
            youtubeFragment.initialize(Constants.API_KEY,
                    new YouTubePlayer.OnInitializedListener() {
                        @Override
                        public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                            YouTubePlayer youTubePlayer, boolean b) {
                            // do any work here to cue video, play video, etc.
                            youTubePlayer.cueVideo(current.image);
                        }
                        @Override
                        public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                            YouTubeInitializationResult youTubeInitializationResult) {
                            Toast.makeText(context, "video link isn't working", Toast.LENGTH_SHORT).show();
                        }
                    });

        }


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
        YouTubePlayerView video;
        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.textViewTitle);
            video = (YouTubePlayerView) itemView.findViewById(R.id.youtube_player_view);
            image = (ImageView) itemView.findViewById(R.id.image);
            shortdesc = (TextView) itemView.findViewById(R.id.shortdesc);

        }

    }
}