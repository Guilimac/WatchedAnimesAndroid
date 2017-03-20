package br.com.fiap.watchedanimesandroid.dao;

/**
 * Created by guilherme on 19/03/17.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

import br.com.fiap.watchedanimesandroid.model.Anime;

public class AnimeDAO {
    private DBOpenHelper db;

    public AnimeDAO(Context context) {
        db = new DBOpenHelper(context);
    }
    public static final String TABELA_ANIME = "anime";

    public static final String COLUNA_ID = "id";
    public static final String COLUNA_NAME = "name";
    public static final String COLUNA_DESCRIPTION = "description";
    public static final String COLUNA_RATING = "rating";
    public static final String COLUNA_USER_ID = "user_id";

    public String add(Anime anime){
        long resultado;
        SQLiteDatabase writeDb = db.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUNA_NAME, anime.getName());
        values.put(COLUNA_DESCRIPTION, anime.getDescription());
        values.put(COLUNA_RATING, anime.getRating());
        values.put(COLUNA_USER_ID, anime.getUserId());

        resultado = writeDb.insert(TABELA_ANIME,
                null,
                values);

        db.close();

        if(resultado == -1) {
            return "Erro ao inserir registro";
        } else {
            return "Registro inserido com sucesso";
        }
    }
    public List<Anime> getAll() {
        List<Anime> animes = new LinkedList<>();

        String query = "SELECT  * FROM " + TABELA_ANIME;

        SQLiteDatabase readDb = db.getWritableDatabase();
        Cursor cursor = readDb.rawQuery(query, null);

        Anime anime = null;

        if (cursor.moveToFirst()) {
            do {
                anime = new Anime();
                anime.setId(cursor.getInt(cursor.getColumnIndex(COLUNA_ID)));
                anime.setName(cursor.getString(cursor.getColumnIndex(COLUNA_NAME)));
                anime.setDescription(cursor.getString(cursor.getColumnIndex(COLUNA_DESCRIPTION)));
                anime.setRating(cursor.getDouble(cursor.getColumnIndex(COLUNA_RATING)));
                anime.setUserId(cursor.getInt(cursor.getColumnIndex(COLUNA_USER_ID)));

                animes.add(anime);
            } while (cursor.moveToNext());
        }
        return animes;
    }

    public Anime getBy(int id) {

        SQLiteDatabase readDb = db.getReadableDatabase();
        String colunas[] = { COLUNA_ID, COLUNA_NAME,COLUNA_DESCRIPTION,COLUNA_RATING,COLUNA_USER_ID};
        String where = "id = " + id;
        Cursor cursor = readDb.query(true, TABELA_ANIME, colunas, where, null, null, null, null, null);

        Anime anime = null;

        if(cursor != null)
        {
            cursor.moveToFirst();
            anime = new Anime();
            anime.setId(cursor.getInt(cursor.getColumnIndex(COLUNA_ID)));
            anime.setName(cursor.getString(cursor.getColumnIndex(COLUNA_NAME)));
            anime.setDescription(cursor.getString(cursor.getColumnIndex(COLUNA_DESCRIPTION)));
            anime.setRating(cursor.getDouble(cursor.getColumnIndex(COLUNA_RATING)));
            anime.setUserId(cursor.getInt(cursor.getColumnIndex(COLUNA_USER_ID)));
        }
        return anime;
    }

    public void edit(Anime anime){
        SQLiteDatabase writeDb = db.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUNA_NAME, anime.getName());
        values.put(COLUNA_DESCRIPTION, anime.getDescription());
        values.put(COLUNA_RATING, anime.getRating());
        String where = "id = " + anime.getId();

        writeDb.update(TABELA_ANIME,values,where,null);
        writeDb.close();

    }

    public void delete(Anime anime){
        SQLiteDatabase readDb = db.getReadableDatabase();
        String where = "id = " + anime.getId();
        readDb.delete(TABELA_ANIME,where,null);
        readDb.close();
    }

}
