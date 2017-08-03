package com.example.zissu.log_reg_daniel;

import android.content.Intent;
import android.widget.Toast;

/**
 * Created by zissu on 26/07/2017.
 */

public class OnPostRegister implements IActionOnPost {

    String username,  password;
    RegisterActivity activity;
    Intent nextStop;

    public OnPostRegister(String username, String password, RegisterActivity activity, Intent nextStop) {
        this.username = username;
        this.password = password;
        this.activity = activity;
        this.nextStop = nextStop;
    }

    @Override
    public void OnPostAction(String serverResponse) {
        try{
            activity.StopPB();
            if (serverResponse != "") {
                Toast.makeText(activity.getApplicationContext(), "Server Declined your regstration",Toast.LENGTH_LONG).show();
            } else {
                Constants.SaveDataOnDevice(activity,username,password);
                activity.startActivity(nextStop);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
