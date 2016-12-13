package com.example.isaac.mysurvey;

import android.content.Intent;
import android.os.Bundle;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static android.R.attr.name;
import static android.R.attr.password;

/**
 * Created by Isaac on 21/11/2016.
 */

public class RegisterActivity extends AppCompatActivity  {

    ImageButton bRegister;
    EditText eName, eAge, eUsername, ePassword, eConfirmPassword;
    DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        eName = (EditText) findViewById(R.id.eName);
        eAge = (EditText) findViewById(R.id.eAge);
        eUsername = (EditText) findViewById(R.id.eUsername);
        ePassword = (EditText) findViewById(R.id.ePassword);
        eConfirmPassword = (EditText) findViewById(R.id.eConfirmPassword);
        bRegister = (ImageButton) findViewById(R.id.bRegister);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name =eName.getText().toString();
                int age = Integer.parseInt(eAge.getText().toString());
                String username = eUsername.getText().toString();
                String password = ePassword.getText().toString();
                String confirmPassword = eConfirmPassword.getText().toString();

                if(username.replaceAll(" ","").isEmpty() | password.replaceAll(" ","").isEmpty()){
                    Toast pass = Toast.makeText(RegisterActivity.this, "Username/Password cannot be empty!" , Toast.LENGTH_SHORT);
                    pass.show();
                }
                else if (!password.equals(confirmPassword))
                {
                    //popup message
                    Toast pass = Toast.makeText(RegisterActivity.this, "Passwords don't match!" , Toast.LENGTH_SHORT);
                    pass.show();
                }
                else
                {
                    //insert details in database
                    Contact c = new Contact();
                    c.setName(name);
                    c.setAge(age);
                    c.setUsername(username);
                    c.setPassword(password);
                    db.addContact(c);


                    Intent loginIntent = new Intent(RegisterActivity.this, MainActivity.class);
                    RegisterActivity.this.startActivity(loginIntent);
                    finish();
                }

            }
        });


        }
        }