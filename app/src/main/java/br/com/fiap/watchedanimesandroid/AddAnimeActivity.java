package br.com.fiap.watchedanimesandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.fiap.watchedanimesandroid.dao.AnimeDAO;
import br.com.fiap.watchedanimesandroid.model.Anime;

public class AddAnimeActivity extends AppCompatActivity {
    private EditText edtNewName;
    private EditText edtNewDescription;
    private EditText edtNewRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_anime);
        edtNewName = (EditText) findViewById(R.id.edtNewName);
        edtNewDescription = (EditText)findViewById(R.id.edtNewDescription);
        edtNewRating = (EditText)findViewById(R.id.edtNewRating);
    }

    public void add(View v){
        Anime anime = new Anime();

        if(edtNewRating.getText().toString().isEmpty() || edtNewDescription.getText().toString().isEmpty() || edtNewName.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), R.string.errNewAnimeFields, Toast.LENGTH_SHORT).show();
        }else {
            anime.setName(edtNewName.getText().toString());
            anime.setDescription(edtNewDescription.getText().toString());
            try {
                double rating = Double.parseDouble(edtNewRating.getText().toString());
                anime.setRating(rating);
            } catch (NumberFormatException ex) {
                Toast.makeText(getApplicationContext(), R.string.errratingParse, Toast.LENGTH_SHORT).show();
            }

            AnimeDAO animeDAO = new AnimeDAO(this);
            animeDAO.add(anime);

            Intent intent = new Intent(AddAnimeActivity.this,
                    MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            AddAnimeActivity.this.finish();
        }

    }
}
