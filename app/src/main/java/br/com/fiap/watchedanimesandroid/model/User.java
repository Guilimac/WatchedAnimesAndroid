package br.com.fiap.watchedanimesandroid.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by guilherme on 19/03/17.
 */

public class User {
    private int id;
    @SerializedName("usuario")
    private String username;
    @SerializedName("senha")
    private String password;
    private int isLogged;

    public int getIsLogged() {
        return isLogged;
    }

    public void setIsLogged(int isLogged) {
        this.isLogged = isLogged;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



}
