package com.example.isaac.mysurvey;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Question12Activity extends AppCompatActivity {
    Button bFinish;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question12);
        setupWindowAnimations();

        username = getIntent().getStringExtra("Username");

        bFinish = (Button) findViewById(R.id.bFinish);

        bFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup rGroup = (RadioGroup)findViewById(R.id.radioGroup);

                if (rGroup.getCheckedRadioButtonId() == -1){
                    Toast.makeText(getApplicationContext(), "Please select Expenses", Toast.LENGTH_SHORT).show();
                }
                else {
                    String strExpense = ((RadioButton) findViewById(rGroup.getCheckedRadioButtonId())).getText().toString();

                    SharedPreferences answers = getSharedPreferences(Question1Activity.PREFS_NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = answers.edit();
                    editor.putString("expenses", strExpense);
                    editor.apply();

                    SharedPreferences userPreferences = getSharedPreferences(username + "Prefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = userPreferences.edit();
                    edit.putString("expenses", strExpense);
                    edit.apply();

                    Intent finishIntent = new Intent(Question12Activity.this, FinishActivity.class);
                    finishIntent.putExtra("username", username);
                    Question12Activity.this.startActivity(finishIntent);
                    finish();
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimations() {
        Fade fade = new Fade();
        fade.setDuration(1000);
        getWindow().setEnterTransition(fade);

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
            Intent logoutIntent = new Intent(Question12Activity.this, MainActivity.class);
            finishAffinity();
            logoutIntent.putExtra("username", username);
            Question12Activity.this.startActivity(logoutIntent);
            finish();
        }
        return super.onOptionsItemSelected(menu);
    }
}
