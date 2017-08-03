package com.example.zissu.log_reg_daniel;

import android.app.Activity;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zissu on 26/07/2017.
 */

//get list of strings, do nothing on preload, return string on post (response)
public class AsyncTaskConnection extends AsyncTask<String, Void, String> {

    Activity activity;
    IActionOnPost onPost = null;



    public AsyncTaskConnection(Activity activity, IActionOnPost onPost) {
        this.activity = activity;
        this.onPost = onPost;
    }

    @Override
    protected String doInBackground(String... lists) {

         // Connect!!
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            //String s = "http://193.106.55.121:8080/login/l_y_c@gmail.com/123456/";
            URL url = new URL(lists[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();

            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            return buffer.toString();
        } catch(Exception e) {
            e.printStackTrace();
        }



        return null;

    }

    @Override
    protected void onPostExecute(String s) {
        if (onPost != null) {
            onPost.OnPostAction(s);
        }
    }
}