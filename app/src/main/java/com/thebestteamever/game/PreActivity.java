package com.thebestteamever.game;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.thebestteamever.game.top_adapter.DBHelper;
import com.thebestteamever.game.top_adapter.TopListTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PreActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre);

        dbHelper = new DBHelper(this);

        ContentValues cv = new ContentValues();
        TopListTest listTest = new TopListTest();

        // подключаемся к БД
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Log.d(LOG_TAG, "--- Insert in mytable: ---");

        for(int i = 0; i < 3; i++) {
            cv.put("login", listTest.testMap().get(i).get("login"));
            cv.put("name", listTest.testMap().get(i).get("name"));
            cv.put("rating", listTest.testMap().get(i).get("rating"));

            // вставляем запись и получаем ее ID
            long rowID = db.insert("mytable", null, cv);
            Log.d(LOG_TAG, "row inserted, ID = " + rowID);
        }


        //чтение данных
        Log.d(LOG_TAG, "--- Rows in mytable: ---");
        // делаем запрос всех данных из таблицы mytable, получаем Cursor
        Cursor c = db.query("mytable", null, null, null, null, null, null);

        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
        if (c.moveToFirst()) {

            // определяем номера столбцов по имени в выборке
            int idColIndex = c.getColumnIndex("id");
            int loginColIndex = c.getColumnIndex("login");
            int nameColIndex = c.getColumnIndex("name");
            int ratingColIndex = c.getColumnIndex("rating");

            do {
                // получаем значения по номерам столбцов и пишем все в лог
                Log.d(LOG_TAG,
                        "ID = " + c.getInt(idColIndex) +
                                ", login = " + c.getString(loginColIndex) +
                                ", name = " + c.getString(nameColIndex) +
                                ", rating = " + c.getString(ratingColIndex));
                // переход на следующую строку
                // а если следующей нет (текущая - последняя), то false - выходим из цикла
            } while (c.moveToNext());
        } else
            Log.d(LOG_TAG, "0 rows");
        c.close();

        Log.d(LOG_TAG, "--- Clear mytable: ---");
        // удаляем все записи
        int clearCount = db.delete("mytable", null, null);
        Log.d(LOG_TAG, "deleted rows count = " + clearCount);

        dbHelper.close();

        Thread splash_time = new Thread() {
            public void run() {
                try {
                    sleep(2000); //общение с серваком в будущем
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(new Intent(PreActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };

        splash_time.start();
    }


}
