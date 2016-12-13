package com.example.isaac.mysurvey;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class StartActivity extends AppCompatActivity  {

    TextView tUsername;
    ImageButton bStart;
    private SharedPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        final String username = getIntent().getStringExtra("Username");

        tUsername = (TextView) findViewById(R.id.tUsername);
        tUsername.setText(username);
        bStart = (ImageButton) findViewById(R.id.bStart);
        userPreferences = getSharedPreferences(username+ "Prefs", Context.MODE_PRIVATE);

        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] question= {"gender", "entertain", "genre", "stream", "music", "device","deviceUse", "social", "uTubeUse", "appCount", "employ", "expenses"};
                final String[] classes= {"Question1Activity", "Question2Activity", "Question3Activity", "Question4Activity", "Question5Activity", "Question6Activity","Question7Activity", "Question8Activity", "Question9Activity", "Question10Activity", "Question11Activity", "Question12Activity"};
                String myPackage = "com.example.isaac.mysurvey.";
                Class<?> myClass = null;

                for (int i = 0; i <= question.length-1 ; i++)
                {
                    String gen = userPreferences.getString(question[i], "None");
                    if (gen == "None"){
                        //go to that page.
                        myPackage += classes[i];
                        break;
                    }

                    if(i == question.length - 1){
                        myPackage+= "FinishActivity";
                    }

                }
                try {
                    myClass = Class.forName(myPackage);
                    Intent intent = new Intent(StartActivity.this, myClass);
                    intent.putExtra("Username", username);
                    StartActivity.this.startActivity(intent);
                    finish();
                }
                catch (ClassNotFoundException e){
                    e.printStackTrace();
                }

            }
        });
    }

}
