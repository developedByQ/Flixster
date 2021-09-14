package com.example.flixster.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Parcel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.R;
import com.example.flixster.DetailActivity;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>  {


        Context context;
        List<Movie> movieList;

        public MovieAdapter(Context context, List<Movie> movieList) {
            this.context = context;
            this.movieList = movieList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View movieView = LayoutInflater.from(context).inflate(R.layout.movie_cell, parent, false);
            return new ViewHolder(movieView);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Movie movie = movieList.get(position);
            holder.bind(movie);
        }

        @Override
        public int getItemCount() {
            return movieList.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder {

            RelativeLayout container;
            TextView titleTextView;
            TextView overviewTextView;
            ImageView movieImageView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                titleTextView = itemView.findViewById(R.id.titleTextView);
                overviewTextView = itemView.findViewById(R.id.overviewTextView);
                movieImageView = itemView.findViewById(R.id.movieImageView);
                container = itemView.findViewById(R.id.container);
            }

            public void bind(Movie movie) {
                titleTextView.setText(movie.getTitle());
                overviewTextView.setText(movie.getDescription());
                String imagePath;
                if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    imagePath = movie.getBackDropPath();
                }else{
                    imagePath = movie.getImagepath();
                }
                Glide.with(context).load(imagePath).into(movieImageView);
                container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(context, DetailActivity.class);
                        i.putExtra("title", movie.getTitle());
                        i.putExtra("movie", Parcels.wrap(movie));
                        context.startActivity(i);
                    }
                });
            }
        }


}
