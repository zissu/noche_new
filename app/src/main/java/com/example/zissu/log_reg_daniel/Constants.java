package com.example.zissu.log_reg_daniel;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

/**
 * Created by zissu on 26/07/2017.
 */
public class Constants {
    public static String App_Name ="Noche";
    public static String User_Name ="username";
    public static String Password ="Password";
    public static String DefaultValue ="";
    public static String BaseURL= "http://193.106.55.121:8080/%s/";

    public static String CreateUrl(String funcName, List<String> params)
    {
        String url = String.format(BaseURL,funcName);
        for (String param : params)
        {
            url = url + param + "/";
        }
        return url;
    }
    public static void DeleteDataOnDevice(Activity activity) {
        SharedPreferences sharedPref = activity.getSharedPreferences(Constants.App_Name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.commit();
    }
    //if already has data it will be override
    public static void SaveDataOnDevice(Activity activity, String username, String password) {

        SharedPreferences sharedPref = activity.getSharedPreferences(Constants.App_Name, Context.MODE_PRIVATE);
        if (CheckOnDevice(sharedPref,Constants.User_Name, Constants.DefaultValue)) {
            return;
        }
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(Constants.User_Name, username);
        editor.putString(Constants.Password, password);
        editor.commit();
    }
    public static boolean CheckOnDevice(SharedPreferences sharedPref, String key, String defaultValue) {
        //check if already exists
        String s = sharedPref.getString(key,defaultValue);
        return sharedPref.getString(key,defaultValue) != defaultValue;
    }
    public static boolean Validate(String userName, String pass) {
        return (!userName.isEmpty() && !pass.isEmpty());

    }
    public static void StartPB(Activity act, final ProgressBar pb) {
        act.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pb.setVisibility(View.VISIBLE);
            }
        });
    }
    public static void StopPB(Activity act, final ProgressBar pb) {
        act.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pb.setVisibility(View.GONE);
            }
        });
    }
}
