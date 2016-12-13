package com.example.isaac.mysurvey;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class FinishActivity extends AppCompatActivity {
    Button bClose;
    String gen;
    String etr;
    String genr;
    String stm;
    String mus;
    String dev;
    String devUs;
    String soc;
    String uTub;
    String app ;
    String emp ;
    String exp ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        TextView gender = (TextView) findViewById(R.id.genderValue);
        TextView etrtain = (TextView) findViewById(R.id.etrValue);
        TextView genre = (TextView) findViewById(R.id.genreValue);
        TextView stream = (TextView) findViewById(R.id.stmValue);
        TextView music = (TextView) findViewById(R.id.musicValue);
        TextView device = (TextView) findViewById(R.id.deviceValue);
        TextView devUse = (TextView) findViewById(R.id.devUseValue);
        TextView social = (TextView) findViewById(R.id.socialValue);
        TextView uTube = (TextView) findViewById(R.id.uTubeValue);
        TextView mobApp = (TextView) findViewById(R.id.mobileValue);
        TextView employ = (TextView) findViewById(R.id.employValue);
        TextView expense = (TextView) findViewById(R.id.expenseValue);


        SharedPreferences answers = getSharedPreferences(Question1Activity.PREFS_NAME, getApplicationContext().MODE_PRIVATE);
        gen = answers.getString("gender", "None");
        etr = answers.getString("entertain", "None");
        genr = answers.getString("genre", "None");
        stm = answers.getString("stream", "None");
        mus = answers.getString("music", "None");
        dev = answers.getString("device", "None");
        devUs = answers.getString("deviceUse","None");
        soc = answers.getString("social", "None");
        uTub = answers.getString("uTubeUse", "None");
        app = answers.getString("appCount", "None");
        emp = answers.getString("employ", "None");
        exp = answers.getString("expenses", "None");

        gender.setText(gen);
        etrtain.setText(etr);
        genre.setText(genr);
        stream.setText(stm);
        music.setText(mus);
        device.setText(dev);
        devUse.setText(devUs);
        social.setText(soc);
        uTube.setText(uTub);
        mobApp.setText(app);
        employ.setText(emp);
        expense.setText(exp);

        bClose =(Button) findViewById(R.id.bClose);

        bClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.finish, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menu){
        int id = menu.getItemId();
        if (id == R.id.share){
            Intent ShareIntent = new Intent();
            //Gets the text to send
            String share = "This is my Survey Summary:\n" +
                    "Gender: " +gen+"\nEntertainment: " + etr + "\nGenre: "+ genr + "\nStream/Dowmload: " + stm + "\nMusic: " + mus
                    + "\nDevice: " + dev + "\nDevice Use: " + devUs + "\nSocial: " +soc + "\nYouTube Use: " + uTub +
                    "\nMobile Apps Amount: " + app + "\nEmployment: " + emp + "\nExpenses: " + exp;
            //Sets the action to send the data
            ShareIntent.setAction(Intent.ACTION_SEND);
            //It gets the information to be shared through the action
            ShareIntent.putExtra(Intent.EXTRA_TEXT,share);
            ShareIntent.setType("text/plain");
            startActivity(Intent.createChooser(ShareIntent, "Send Summary To..."));
        }
        return super.onOptionsItemSelected(menu);
    }
}
