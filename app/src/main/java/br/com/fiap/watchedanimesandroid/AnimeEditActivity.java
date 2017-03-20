package br.com.fiap.watchedanimesandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.fiap.watchedanimesandroid.dao.AnimeDAO;
import br.com.fiap.watchedanimesandroid.model.Anime;

public class AnimeEditActivity extends AppCompatActivity {

    private EditText edtEditName;
    private EditText edtEditDescription;
    private EditText edtEditRating;

    private Anime anime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_edit);

        edtEditName = (EditText) findViewById(R.id.edtEditName);
        edtEditDescription = (EditText)findViewById(R.id.edtEditDescription);
        edtEditRating = (EditText)findViewById(R.id.edtEditRating);

        if(getIntent()!= null){
            int id = getIntent().getIntExtra("animeId",0);
            AnimeDAO animeDAO = new AnimeDAO(this);
            anime = animeDAO.getBy(id);

            if(anime != null){
                edtEditName.setText(anime.getName());
                edtEditDescription.setText(anime.getDescription());
                edtEditRating.setText(Double.toString(anime.getRating()));
            }
        }
    }

    public void save(View v){
        if(edtEditRating.getText().toString().isEmpty() || edtEditDescription.getText().toString().isEmpty() || edtEditName.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), R.string.errNewAnimeFields, Toast.LENGTH_SHORT).show();
        }else {
            anime.setName(edtEditName.getText().toString());
            anime.setDescription(edtEditDescription.getText().toString());
            try {
                double rating = Double.parseDouble(edtEditRating.getText().toString());
                anime.setRating(rating);
            } catch (NumberFormatException ex) {
                Toast.makeText(getApplicationContext(), R.string.errratingParse, Toast.LENGTH_SHORT).show();
            }

            AnimeDAO animeDAO = new AnimeDAO(this);
            animeDAO.edit(anime);
            Intent i = new Intent(AnimeEditActivity.this,
                    MainActivity.class);
            startActivity(i);
            AnimeEditActivity.this.finish();
        }
    }
}
