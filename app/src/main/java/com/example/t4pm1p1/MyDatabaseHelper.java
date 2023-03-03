package com.example.t4pm1p1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "my_database";
    private static final int DATABASE_VERSION = 1;

    // Table name and column names for the image table
    private static final String TABLE_IMAGE = "images";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_IMAGE_PATH = "image_path";
    private static final String COLUMN_DESCRIPTION = "description";

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the image table
        String CREATE_IMAGE_TABLE = "CREATE TABLE " + TABLE_IMAGE +
                "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_IMAGE_PATH + " TEXT," +
                COLUMN_DESCRIPTION + " TEXT" +
                ")";
        db.execSQL(CREATE_IMAGE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Upgrade the database schema if necessary
        if (oldVersion < newVersion) {
            // Drop the image table and create a new one
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_IMAGE);
            onCreate(db);
        }
    }

    // Add a new image record to the database
    public long addImage(String imagePath, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_IMAGE_PATH, imagePath);
        values.put(COLUMN_DESCRIPTION, description);
        long result = db.insert(TABLE_IMAGE, null, values);
        db.close();
        return result;
    }

    // Get a list of all image records in the database
    public List<Image> getAllImages() {
        List<Image> imageList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_IMAGE, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Image image = new Image();
                image.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
                image.setImagePath(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_PATH)));
                image.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
                imageList.add(image);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return imageList;
    }
}
