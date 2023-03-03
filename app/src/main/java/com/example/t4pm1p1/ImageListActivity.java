package com.example.t4pm1p1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ImageListActivity extends AppCompatActivity {
    private ListView listView;
    private List<Image> imageList;
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        listView = findViewById(R.id.listView);
        dbHelper = new MyDatabaseHelper(this);

        // Load images from the database
        imageList = dbHelper.getAllImages();

        // Set up the list view with the images and descriptions
        ImageListAdapter adapter = new ImageListAdapter(this, imageList);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.close();
    }
}
