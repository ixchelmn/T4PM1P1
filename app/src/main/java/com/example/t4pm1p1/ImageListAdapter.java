package com.example.t4pm1p1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ImageListAdapter extends ArrayAdapter<Image> {

    private Context mContext;

    public ImageListAdapter(Context context, List<Image> imageList) {
        super(context, 0, imageList);
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.image_item, parent, false);
        }

        Image image = getItem(position);

        ImageView imageView = view.findViewById(R.id.image_view);
        TextView descriptionTextView = view.findViewById(R.id.description_text_view);

        if (image != null) {
            // Load the image from the file path
            Bitmap bitmap = BitmapFactory.decodeFile(image.getImagePath());
            imageView.setImageBitmap(bitmap);

            // Set the image description
            descriptionTextView.setText(image.getDescription());
        }

        return view;
    }
}

