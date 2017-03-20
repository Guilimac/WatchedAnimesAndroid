package br.com.fiap.watchedanimesandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import br.com.fiap.watchedanimesandroid.dao.UserDAO;
import br.com.fiap.watchedanimesandroid.model.User;
import br.com.fiap.watchedanimesandroid.network.UserAPI;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SplashScreenActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3500;
    private final String KEY_LOGIN = "login";
    private User serviceUser;
    private UserDAO userDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        carregar();
    }

    private void carregar() {
        final RestAdapter restadapter = new RestAdapter.Builder()
                .setEndpoint("http://www.mocky.io/v2/58b9b1740f0000b614f09d2f")
                .build();
        userDAO = new UserDAO(this);
        if(userDAO.getAll().isEmpty()){
            UserAPI api = restadapter.create(UserAPI.class);

            api.getUSer(new Callback<User>() {
                @Override
                public void success(User user, Response response) {
                    serviceUser = user;
                    serviceUser.setIsLogged(0);
                    String result = userDAO.add(serviceUser);
                    if(result.isEmpty()){
                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Após o tempo definido irá executar a próxima tela
                if(isConectado()){
                    Intent intent = new Intent(SplashScreenActivity.this,
                            MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    SplashScreenActivity.this.finish();
                }else{
                    Intent intent = new Intent(SplashScreenActivity.this,
                            LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    SplashScreenActivity.this.finish();
                }

            }
        }, SPLASH_DISPLAY_LENGTH);
    }
    private boolean isConectado() {
        SharedPreferences shared = getSharedPreferences("info",MODE_PRIVATE);
        String login = shared.getString(KEY_LOGIN, "");
        if(login.equals(""))
            return false;
        else
            return true;
    }
}
