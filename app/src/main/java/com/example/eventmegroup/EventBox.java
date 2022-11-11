package com.example.eventmegroup;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EventBox extends AppCompatActivity {
    private Button date, time, location, eventName;
    private TextView description;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState){  // show new, singular Event Box
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbox);

        Intent mIntent = getIntent();

        // SET ALL COMPONENTS OF EVENT BOX
        description = (TextView) findViewById(R.id.Description);
        date = (Button) findViewById(R.id.date);
        eventName = (Button) findViewById(R.id.ename);
        eventName.setText(mIntent.getStringExtra("Name"));
        date.setText(mIntent.getStringExtra("Date")+"  "+
                mIntent.getStringExtra("Time"));
        description.setText(mIntent.getStringExtra("Description"));
        description.setMovementMethod(new ScrollingMovementMethod());
        image = (ImageView) findViewById(R.id.imageView);

        String tempForImageRetreival = mIntent.getStringExtra("Date");

        // set function for retrieving images for event
        image.setAdjustViewBounds(true);
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    @Override
    protected void onDestroy(){  // when Event Box exited
        super.onDestroy();
    }


}
