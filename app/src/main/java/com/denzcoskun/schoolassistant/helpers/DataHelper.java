package com.denzcoskun.schoolassistant.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.denzcoskun.schoolassistant.activities.HomeActivity;
import com.denzcoskun.schoolassistant.models.MainModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by Denx on 15.02.2018.
 */

public class DataHelper {
    private Context context;

    public DataHelper(Context context) {
        this.context = context;
    }

    public void setModel(MainModel mainModel){
        SharedPreferences sharedPrefs = context.getSharedPreferences("Application Data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mainModel);
        editor.putString("Student Data", json);
        editor.commit();
    }
    public void setMainModel(){
        SharedPreferences sharedPrefs = context.getSharedPreferences("Application Data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(HomeActivity.mainModel);
        editor.putString("Student Data", json);
        editor.commit();
    }
    public MainModel getModel(){
        SharedPreferences sharedPrefs = context.getSharedPreferences("Application Data", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPrefs.getString("Student Data", null);
        Type type = new TypeToken<MainModel>() {}.getType();
        try {
            if(gson.fromJson(json, type)!=null) {
                MainModel mainModel = gson.fromJson(json, type);
                return mainModel;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }
}
