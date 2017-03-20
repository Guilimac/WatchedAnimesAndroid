package br.com.fiap.watchedanimesandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import br.com.fiap.watchedanimesandroid.dao.AnimeDAO;
import br.com.fiap.watchedanimesandroid.model.Anime;

public class AnimeDetailsActivity extends AppCompatActivity {
    TextView tvDetAnimeName;
    TextView tvDetAnimeDescription;
    TextView tvDetAnimeRating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_details);
        tvDetAnimeName = (TextView) findViewById(R.id.tvDetAnimeName);
        tvDetAnimeDescription = (TextView) findViewById(R.id.tvDetAnimeDesc);
        tvDetAnimeRating = (TextView) findViewById(R.id.tvDetAnimeRating);

        if(getIntent()!= null){
            int id = getIntent().getIntExtra("animeId",0);
            AnimeDAO animeDAO = new AnimeDAO(this);
            Anime anime = animeDAO.getBy(id);

            if(anime != null){
                tvDetAnimeName.setText(anime.getName());
                tvDetAnimeDescription.setText(anime.getDescription());
                tvDetAnimeRating.setText(Double.toString(anime.getRating()));
            }
        }
    }
}
