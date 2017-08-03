package com.example.zissu.log_reg_daniel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class NavigationMainActivity extends AppCompatActivity {
ImageButton bar, club, res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_main);

        bar = (ImageButton)findViewById(R.id.bar);
        club = (ImageButton)findViewById(R.id.club);
        res = (ImageButton)findViewById(R.id.res);

        club.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NavigationMainActivity.this, ClubActivity.class));
            }
        });
        bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NavigationMainActivity.this, BarActivity.class));
            }
        });
        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NavigationMainActivity.this, ResActivity.class));
            }
        });
    }
}
