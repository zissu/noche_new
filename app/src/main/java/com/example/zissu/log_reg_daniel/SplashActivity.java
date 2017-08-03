package com.example.zissu.log_reg_daniel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
TextView textView;
    Button button, checkAlone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        textView = (TextView)findViewById(R.id.helloMsg) ;
        button = (Button)findViewById(R.id.logout);
        checkAlone = (Button)findViewById(R.id.checkAlone);

        checkAlone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplashActivity.this, NavigationMainActivity.class));
            }
        });

        String username = getIntent().getExtras().getString(Constants.User_Name);
        textView.setText(textView.getText().toString() + username);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.DeleteDataOnDevice(SplashActivity.this);
                //pass with intent to main
            }
        });
    }
}
