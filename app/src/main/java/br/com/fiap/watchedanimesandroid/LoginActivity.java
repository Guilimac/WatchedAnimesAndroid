package br.com.fiap.watchedanimesandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import br.com.fiap.watchedanimesandroid.dao.UserDAO;
import br.com.fiap.watchedanimesandroid.model.User;

public class LoginActivity extends AppCompatActivity {

    private final String KEY_LOGIN = "login";
    private final String KEY_APP_PREFERENCES = "Enter";

    private EditText edtUserName;
    private EditText edtPassword;
    private CheckBox cbKeepLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUserName = (EditText)findViewById(R.id.edtUserName);
        edtPassword = (EditText)findViewById(R.id.edtPassword);
        cbKeepLogin = (CheckBox)findViewById(R.id.cbKeepLogin);

        if(isConectado())
            iniciarApp();
    }
    public void logar(View v){
        if(isLoginValido()){
            if(cbKeepLogin.isChecked()){
                manterConectado();
            }
            iniciarApp();
        }
    }

    private boolean isConectado() {
        SharedPreferences shared = getSharedPreferences("info",MODE_PRIVATE);
        String login = shared.getString(KEY_LOGIN, "");
        if(login.equals(""))
            return false;
        else
            return true;
    }
    private void iniciarApp() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private boolean isLoginValido() {
        UserDAO userDAO = new UserDAO(this);

        List<User> users = userDAO.getAll();
        if(!users.isEmpty()){
            User user = users.get(0);
            String login = edtUserName.getText().toString();
            String senha = edtPassword.getText().toString();
            if(login.equals(user.getUsername())
                    && senha.equals(user.getPassword())) {
                return true;
            } else {
                Toast.makeText(getApplicationContext(), R.string.errLogin, Toast.LENGTH_SHORT).show();
                return false;
            }
        }else{
            return false;
        }
    }

    private void manterConectado(){
        String login = edtUserName.getText().toString();
        SharedPreferences pref = getSharedPreferences(KEY_APP_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(KEY_LOGIN, login);
        editor.apply();
    }
}
