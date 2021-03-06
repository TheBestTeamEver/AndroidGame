package com.thebestteamever.game.serviceapi;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by alexss8 on 30.05.2016.
 */
public class MeRatingProvider extends ContentProvider {
    final String LOG_TAG = "myLogs";

    // // Константы для БД
    // БД
    static final String DB_NAME = "mydb";
    static final int DB_VERSION = 1;

    // Таблица
    static final String RATING_TABLE = "rating";

    // Поля
    static final String USER_ID = "_id";
    static final String USER_NAME = "name";
    static final String USER_RATING = "rating";

    // Скрипт создания таблицы
    static final String DB_CREATE = "create table " + RATING_TABLE + "("
            + USER_ID + " integer primary key autoincrement, "
            + USER_NAME + " text, " + USER_RATING + " text" + ");";

    // // Uri
    // authority
    static final String AUTHORITY = "com.thebestteamever.game.Rating";

    // path
    static final String RATING_PATH = "rating";

    // Общий Uri
    public static final Uri RATING_CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + RATING_PATH);

    // Типы данных
    // набор строк
    static final String RATING_CONTENT_TYPE = "vnd.android.cursor.dir/vnd."
            + AUTHORITY + "." + RATING_PATH;

    // одна строка
    static final String RATING_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."
            + AUTHORITY + "." + RATING_PATH;

    //// UriMatcher
    // общий Uri
    static final int URI_RATING = 1;

    // Uri с указанным ID
    static final int URI_RATING_ID = 2;

    // описание и создание UriMatcher
    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, RATING_PATH, URI_RATING);
        uriMatcher.addURI(AUTHORITY, RATING_PATH + "/#", URI_RATING_ID);
    }

    DBHelper dbHelper;
    SQLiteDatabase db;

    public boolean onCreate() {
        Log.d(LOG_TAG, "onCreate");
        dbHelper = new DBHelper(getContext());
        return true;
    }

    // чтение
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Log.d(LOG_TAG, "query, " + uri.toString());
        // проверяем Uri
        switch (uriMatcher.match(uri)) {
            case URI_RATING: // общий Uri
                Log.d(LOG_TAG, "URI_RATING");
                // если сортировка не указана, ставим свою - по имени
                if (TextUtils.isEmpty(sortOrder)) {
                    sortOrder = USER_RATING + " DESC";
                }
                break;
            case URI_RATING_ID: // Uri с ID
                String id = uri.getLastPathSegment();
                Log.d(LOG_TAG, "URI_RATING_ID, " + id);
                // добавляем ID к условию выборки
                if (TextUtils.isEmpty(selection)) {
                    selection = USER_ID + " = " + id;
                } else {
                    selection = selection + " AND " + USER_ID + " = " + id + " ORDER BY RATING DESC";
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(RATING_TABLE, projection, selection,
                selectionArgs, null, null, sortOrder);
        // просим ContentResolver уведомлять этот курсор
        // об изменениях данных в CONTACT_CONTENT_URI
        cursor.setNotificationUri(getContext().getContentResolver(),
                RATING_CONTENT_URI);
        return cursor;
    }

    public Uri insert(Uri uri, ContentValues values) {
        Log.d(LOG_TAG, "insert, " + uri.toString());
        if (uriMatcher.match(uri) != URI_RATING)
            throw new IllegalArgumentException("Wrong URI: " + uri);

        db = dbHelper.getWritableDatabase();
        long rowID = db.insert(RATING_TABLE, null, values);
        Uri resultUri = ContentUris.withAppendedId(RATING_CONTENT_URI, rowID);
        // уведомляем ContentResolver, что данные по адресу resultUri изменились
        getContext().getContentResolver().notifyChange(resultUri, null);
        return resultUri;
    }

    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.d(LOG_TAG, "delete, " + uri.toString());
        switch (uriMatcher.match(uri)) {
            case URI_RATING:
                Log.d(LOG_TAG, "URI_RATING");
                break;
            case URI_RATING_ID:
                String id = uri.getLastPathSegment();
                Log.d(LOG_TAG, "URI_RATING_ID, " + id);
                if (TextUtils.isEmpty(selection)) {
                    selection = USER_ID + " = " + id;
                } else {
                    selection = selection + " AND " + USER_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getWritableDatabase();
        int cnt = db.delete(RATING_TABLE, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return cnt;
    }

    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        Log.d(LOG_TAG, "update, " + uri.toString());
        switch (uriMatcher.match(uri)) {
            case URI_RATING:
                Log.d(LOG_TAG, "URI_RATING");

                break;
            case URI_RATING_ID:
                String id = uri.getLastPathSegment();
                Log.d(LOG_TAG, "URI_RATING_ID, " + id);
                if (TextUtils.isEmpty(selection)) {
                    selection = USER_ID + " = " + id;
                } else {
                    selection = selection + " AND " + USER_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getWritableDatabase();
        int cnt = db.update(RATING_TABLE, values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return cnt;
    }

    public String getType(Uri uri) {
        Log.d(LOG_TAG, "getType, " + uri.toString());
        switch (uriMatcher.match(uri)) {
            case URI_RATING:
                return RATING_CONTENT_TYPE;
            case URI_RATING_ID:
                return RATING_CONTENT_ITEM_TYPE;
        }
        return null;
    }

    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}