package br.com.fiap.watchedanimesandroid.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.fiap.watchedanimesandroid.R;
import br.com.fiap.watchedanimesandroid.model.Anime;

/**
 * Created by guilherme on 20/03/17.
 */

public class AnimeAdapter extends RecyclerView.Adapter {
    private final List<Anime> animes;
    private Context context;
    private AnimeOnclickListener animeOnclickListener;
    public AnimeAdapter(List<Anime> animes, Context context, AnimeOnclickListener animeOnclickListener){
        this.animes = animes;
        this.context = context;
        this.animeOnclickListener = animeOnclickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_anime, parent, false);
        return new AnimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        AnimeViewHolder animeHolder = (AnimeViewHolder) holder;
        Anime anime = animes.get(position);
        animeHolder.tvAnimeName.setText(anime.getName());
        animeHolder.tvAnimeRating.setText(Double.toString(anime.getRating()));

        if(animeOnclickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    animeOnclickListener.onClickAnime(holder.itemView,position);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return animes.size();
    }

    public interface AnimeOnclickListener{
        public void onClickAnime(View v,int index);
    }
}

