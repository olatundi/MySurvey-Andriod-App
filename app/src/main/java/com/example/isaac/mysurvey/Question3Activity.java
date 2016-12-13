package com.example.isaac.mysurvey;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Question3Activity extends AppCompatActivity {
    ImageButton bNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question3);
        final String username = getIntent().getStringExtra("Username");

        bNext = (ImageButton) findViewById(R.id.bNext);

        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup rGroup = (RadioGroup)findViewById(R.id.radioGroup);

                if (rGroup.getCheckedRadioButtonId() == -1){
                    Toast.makeText(getApplicationContext(), "Please select Genre", Toast.LENGTH_SHORT).show();
                }
                else {
                    String strGenre = ((RadioButton) findViewById(rGroup.getCheckedRadioButtonId())).getText().toString();


                    SharedPreferences answers = getSharedPreferences(Question1Activity.PREFS_NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = answers.edit();
                    editor.putString("genre", strGenre);
                    editor.commit();

                    SharedPreferences userPreferences = getSharedPreferences(username + "Prefs",Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = userPreferences.edit();
                    edit.putString("genre", strGenre);
                    edit.commit();

                    Intent quizIntent = new Intent(Question3Activity.this, Question4Activity.class);
                    quizIntent.putExtra("Username", username);
                    Question3Activity.this.startActivity(quizIntent);
                    finish();
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menu){
        int id = menu.getItemId();
        if (id == R.id.logout){
            Intent logoutIntent = new Intent(Question3Activity.this, MainActivity.class);
            finishAffinity();
            Question3Activity.this.startActivity(logoutIntent);
            finish();
        }
        return super.onOptionsItemSelected(menu);
    }
}
