package com.example.isaac.mysurvey;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Isaac on 27/11/2016.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    SQLiteDatabase db;
    public DatabaseHandler(Context context){
        super(context, "contactDB", null, 7);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE contactTable( user_id integer primary key not null, "  +
                "name text , age integer, username text , password text );" );
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS contactTable";
        db.execSQL(query);
        this.onCreate(db);

    }
    public void addContact(Contact c) {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String query = "select * from contactTable";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        contentValues.put("user_id", count);
        contentValues.put("name", c.getName());
        contentValues.put("age", c.getAge());
        contentValues.put("username", c.getUsername());
        contentValues.put("password", c.getPassword());
        long result = db.insert("contactTable", null, contentValues);
        if (result > 0) {
            Log.d("Register Information", "inserted successfully");
        } else {
            Log.d("Register Information", "failed to insert");
        }
    }

    public String searchPassword(String username){
        db = this.getReadableDatabase();
        String query = "SELECT username, password from contactTable";
        Cursor cursor = db.rawQuery(query, null) ;
        String u, p;
        p = "Not Found";
        if (cursor.moveToFirst()) {
            do {
                u = cursor.getString(0);
                p = cursor.getString(1);
                if (u.equals(username)) {
                    p = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
            return p;
        }
}

