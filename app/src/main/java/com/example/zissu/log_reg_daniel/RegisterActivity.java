package com.example.zissu.log_reg_daniel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    EditText name,etEmail, password, age;
    Button btn;
    ProgressBar pb;
    Spinner genders,inters;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = (EditText)  findViewById(R.id.username);
        etEmail = (EditText) findViewById(R.id.etEmail);
        age = (EditText)  findViewById(R.id.etAge);
        genders = (Spinner) findViewById(R.id.genders);
        inters = (Spinner) findViewById(R.id.interests);
        password = (EditText) findViewById(R.id.password);
        btn = (Button) findViewById(R.id.button);
        pb = (ProgressBar) findViewById(R.id.pb);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.gender,android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.interests,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        genders.setAdapter(adapter);
        inters.setAdapter(adapter2);
        pb.setVisibility(View.INVISIBLE);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }

            private void register() {
                String user = name.getText().toString();
                String agei = (age.getText().toString());
                String gender = genders.getSelectedItem().toString();
                String intrest = inters.getSelectedItem().toString();
                String userName = etEmail.getText().toString();
                String pass = password.getText().toString();

                if (Constants.Validate(userName, pass))
                {
                    Constants.StartPB(RegisterActivity.this, pb);
                    List<String> params = new ArrayList<String>();
                    params.add(user);
                    params.add(userName);
                    params.add(pass);
                    params.add(agei+"");
                    params.add(gender);
                    params.add(intrest);
                    // run async
                    new AsyncTaskConnection(RegisterActivity.this,new OnPostRegister(userName, pass,RegisterActivity.this,new Intent(RegisterActivity.this,LoginActivity.class))).execute(Constants.CreateUrl("createUser",params));
                } else {
                    Toast.makeText(RegisterActivity.this, "username or password nvalid",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public void StopPB() {
        Constants.StopPB(this, pb);
    }
}
