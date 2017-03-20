package br.com.fiap.watchedanimesandroid.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.fiap.watchedanimesandroid.R;
import br.com.fiap.watchedanimesandroid.model.Anime;

public class AnimeViewHolder extends RecyclerView.ViewHolder{
    final TextView tvAnimeName;
    final TextView tvAnimeRating;
    public AnimeViewHolder(View itemView) {
        super(itemView);
        tvAnimeName = (TextView)itemView.findViewById(R.id.tvAnimeName);
        tvAnimeRating = (TextView)itemView.findViewById(R.id.tvAnimeRating);

    }
}
