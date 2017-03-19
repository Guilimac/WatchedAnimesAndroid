package br.com.fiap.watchedanimesandroid.network;

/**
 * Created by guilherme on 19/03/17.
 */
import br.com.fiap.watchedanimesandroid.model.User;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface UserAPI {
    @GET("/")
    void getUSer(Callback<User> user);
}
