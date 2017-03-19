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

import br.com.fiap.watchedanimesandroid.model.User;

public class UserDAO {
    private DBOpenHelper db;

    public UserDAO(Context context){
        db = new DBOpenHelper(context);
    }
    public static final String TABELA_USER = "user";

    public static final String COLUNA_ID = "id";
    public static final String COLUNA_USERNAME = "username";

    public List<User> getAll() {
        List<User> clubes = new LinkedList<>();

        String query = "SELECT  * FROM " + TABELA_USER;

        SQLiteDatabase readDb = db.getWritableDatabase();
        Cursor cursor = readDb.rawQuery(query, null);

        User user = null;

        if (cursor.moveToFirst()) {
            do {
                user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(COLUNA_ID)));
                user.setUsername(cursor.getString(cursor.getColumnIndex(COLUNA_USERNAME)));
                clubes.add(user);
            } while (cursor.moveToNext());
        }
        return clubes;
    }

    public User getBy(int id) {

        SQLiteDatabase readDb = db.getReadableDatabase();
        String colunas[] = { COLUNA_ID, COLUNA_USERNAME};
        String where = "id = " + id;
        Cursor cursor = readDb.query(true, TABELA_USER, colunas, where, null, null, null, null, null);

        User user = null;

        if(cursor != null)
        {
            cursor.moveToFirst();
            user = new User();
            user.setUsername(cursor.getString(cursor.getColumnIndex(COLUNA_USERNAME)));
            user.setId(cursor.getInt(cursor.getColumnIndex(COLUNA_ID)));
        }
        return user;
    }

    public String add(User user){
        long resultado;
        SQLiteDatabase writeDb = db.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUNA_USERNAME, user.getUsername());

        resultado = writeDb.insert(TABELA_USER,
                null,
                values);

        db.close();

        if(resultado == -1) {
            return "Erro ao inserir registro";
        } else {
            return "Registro inserido com sucesso";
        }
    }

}
