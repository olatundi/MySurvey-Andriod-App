package com.example.isaac.mysurvey;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private CheckBox loginCheckBox;
    public static final String PREFS_NAME = "loginPrefs";

    boolean saveLogin;
    EditText eUsername, ePassword;
    ImageButton registerLink, bLogin;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eUsername = (EditText) findViewById(R.id.eUsername);
        ePassword = (EditText) findViewById(R.id.ePassword);
        bLogin = (ImageButton) findViewById(R.id.bLogin);
        registerLink = (ImageButton) findViewById(R.id.tRegisterLink);
        loginCheckBox = (CheckBox) findViewById(R.id.checkBox);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true){
            eUsername.setText(loginPreferences.getString("username", ""));
            ePassword.setText(loginPreferences.getString("password",""));
            loginCheckBox.setChecked(true);

        }

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(registerIntent);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = eUsername.getText().toString();
                final String pass = ePassword.getText().toString();

                if (loginCheckBox.isChecked()) {
                    loginPrefsEditor.putBoolean("saveLogin", true);
                    loginPrefsEditor.putString("username", username);
                    loginPrefsEditor.putString("password", pass);
                    loginPrefsEditor.commit();
                } else {
                    loginPrefsEditor.clear();
                    loginPrefsEditor.commit();
                }

                String password = db.searchPassword(username);
                if (pass.equals(password)) {

                    Intent loginIntent = new Intent(MainActivity.this, StartActivity.class);
                    loginIntent.putExtra("Username", username);
                    MainActivity.this.startActivity(loginIntent);
                    finish();

                }
                else{
                    Toast invalid = Toast.makeText(MainActivity.this, "Usernamne and Password don't match!" , Toast.LENGTH_SHORT);
                    invalid.show();
                }


            }

        });
    }
}
