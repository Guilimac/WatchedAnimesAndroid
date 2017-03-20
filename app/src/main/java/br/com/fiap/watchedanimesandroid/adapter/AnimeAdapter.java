package br.com.fiap.watchedanimesandroid.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.fiap.watchedanimesandroid.R;
import br.com.fiap.watchedanimesandroid.model.Anime;

/**
 * Created by guilherme on 20/03/17.
 */

public class AnimeAdapter extends BaseAdapter {
    private final List<Anime> animes;
    private Activity fromActivity;
    public AnimeAdapter(List<Anime> animes, Activity activity){
        this.animes = animes;
        fromActivity = activity;
    }
    @Override
    public int getCount() {
        return animes.size();
    }

    @Override
    public Object getItem(int position) {
        return animes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return animes.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = fromActivity.getLayoutInflater().inflate(R.layout.item_anime,parent,false);
        Anime anime = animes.get(position);

        TextView tvAnimeName = (TextView)v.findViewById(R.id.tvAnimeName);
        TextView tvAnimeRating = (TextView)v.findViewById(R.id.tvAnimeRating);

        tvAnimeName.setText(anime.getName());
        tvAnimeRating.setText(String.valueOf(anime.getRating()));


        return v;
    }
}
