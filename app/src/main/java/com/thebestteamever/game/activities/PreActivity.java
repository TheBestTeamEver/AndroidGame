package com.thebestteamever.game.activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.thebestteamever.game.R;
import com.thebestteamever.game.serviceapi.DBHelper;
import com.thebestteamever.game.serviceapi.TopListMock;

public class PreActivity extends AppCompatActivity {

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre);

        dbHelper = new DBHelper(this);

        ContentValues cv = new ContentValues();
        TopListMock listTest = new TopListMock();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        for(int i = 0; i < 3; i++) {
            cv.put("login", listTest.testMap().get(i).get("login"));
            cv.put("name", listTest.testMap().get(i).get("name"));
            cv.put("rating", listTest.testMap().get(i).get("rating"));

            // вставляем запись и получаем ее ID
            long rowID = db.insert("mytable", null, cv);
        }

        Thread splash_time = new Thread() {
            public void run() {
                try {
                    sleep(2000); //общение с серваком в будущем
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(new Intent(PreActivity.this, RegistrationActivity.class));
                    finish();
                }
            }
        };

        splash_time.start();
        dbHelper.close();
    }
}
