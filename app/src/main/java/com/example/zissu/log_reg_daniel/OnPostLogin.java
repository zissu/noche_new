package com.example.zissu.log_reg_daniel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by zissu on 26/07/2017.
 */
public class OnPostLogin implements IActionOnPost {

    String username,  password;
    LoginActivity currentActivity;
    Intent i;
    SharedPreferences sharedPref;
    public OnPostLogin(String username, String password,LoginActivity currentActivity,Intent i) {
        this.username = username;
        this.password = password;
        this.i = i; // next activity is login!!
        this.currentActivity = currentActivity;
        sharedPref = currentActivity.getSharedPreferences(Constants.App_Name, Context.MODE_PRIVATE);
    }

    @Override
    public void OnPostAction(String result) {
        try {
            // Extract the answer return true or false
            // Toast for failure
            // if true:
            // if failed Delete!!

            currentActivity.StopPB();
            if(result == null)
            {
                if (Constants.CheckOnDevice(sharedPref, Constants.User_Name, Constants.DefaultValue))
                {
                    Constants.DeleteDataOnDevice(currentActivity);
                }

            }
            else {
                if (!Constants.CheckOnDevice(sharedPref, Constants.User_Name, Constants.DefaultValue))
                {
                    SaveDataOnDevice();
                }
                currentActivity.startActivity(i);
            }


            //
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

       //if already has data it will be override
    private void SaveDataOnDevice() {
        SharedPreferences sharedPref = currentActivity.getSharedPreferences(Constants.App_Name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(Constants.User_Name, username);
        editor.putString(Constants.Password, password);
        editor.commit();
    }
}

