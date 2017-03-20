package br.com.fiap.watchedanimesandroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import br.com.fiap.watchedanimesandroid.dao.AnimeDAO;
import br.com.fiap.watchedanimesandroid.model.Anime;

public class AnimeDetailsActivity extends AppCompatActivity {
    TextView tvDetAnimeName;
    TextView tvDetAnimeDescription;
    TextView tvDetAnimeRating;

    Anime anime;
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
            anime = animeDAO.getBy(id);

            if(anime != null){
                tvDetAnimeName.setText(anime.getName());
                tvDetAnimeDescription.setText(anime.getDescription());
                tvDetAnimeRating.setText(Double.toString(anime.getRating()));
            }
        }
    }

    public void btnEdit(View v){
        Intent i = new Intent(AnimeDetailsActivity.this,AnimeEditActivity.class);
        i.putExtra("animeId",anime.getId());
        startActivity(i);
    }

    public void btnDelete(View v){

        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(R.string.deleteAnimeTitle)
                .setMessage(R.string.deleteAnimeMessage)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AnimeDAO animeDAO = new AnimeDAO(AnimeDetailsActivity.this);
                        animeDAO.delete(anime);
                        Intent i = new Intent(AnimeDetailsActivity.this,MainActivity.class);
                        startActivity(i);
                    }

                })
                .setNegativeButton(R.string.no, null)
                .show();


    }
}
