package com.logixspace.anish.newsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class DetailedActivity extends AppCompatActivity {
    TextView tv1, tv2, tv3;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detailed);
        tv3 = (TextView) findViewById(R.id.DateTime);
        tv1 = (TextView) findViewById(R.id.title);
        tv2 = (TextView) findViewById(R.id.desc);
        iv = (ImageView) findViewById(R.id.detailedImageView);

        Intent n = getIntent();
        String titleToDisplay = n.getStringExtra("Title");
        String DescToDisplay = n.getStringExtra("Desc");

        String ImageToDisplay = n.getStringExtra("Image");
        String DateToDisplay = n.getStringExtra("Date");

        tv1.setText(titleToDisplay);
        tv2.setText(DescToDisplay);
        tv3.setText(DateToDisplay);
        Picasso.with(this)
                .load(ImageToDisplay)
                .into(iv);

    }
}
