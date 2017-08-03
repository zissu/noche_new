package com.example.zissu.log_reg_daniel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button btn;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        btn = (Button) findViewById(R.id.button);
        pb = (ProgressBar) findViewById(R.id.pb);

        pb.setVisibility(View.INVISIBLE);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        SharedPreferences sharedPref = this.getApplicationContext().getSharedPreferences(Constants.App_Name, Context.MODE_PRIVATE);
        if (Constants.CheckOnDevice(sharedPref,Constants.User_Name, Constants.DefaultValue)) {
            username.setText(sharedPref.getString(Constants.User_Name, Constants.DefaultValue));
            password.setText(sharedPref.getString(Constants.Password, Constants.DefaultValue));
            StopPB();
            login();
        }

    }

    private void login() {
        pb.setVisibility(View.VISIBLE);

        final String user = username.getText().toString();
        final String pass = password.getText().toString();

        if (!Constants.Validate(user, pass)) {
            Toast.makeText(this, "username or password is empty",Toast.LENGTH_LONG).show();

        } else {
            // run async
            List<String> params = new ArrayList<String>();
            params.add(user);
            params.add(pass);
            Intent i = new Intent(LoginActivity.this,SplashActivity.class);
            i.putExtra(Constants.User_Name,user);
            new AsyncTaskConnection(LoginActivity.this,new OnPostLogin(username.getText().toString(), password.getText().toString(),LoginActivity.this,i))
                    .execute(Constants.CreateUrl("login", params));
        }
    }

    public void StopPB() {
        Constants.StopPB(this, pb);
    }
 }
