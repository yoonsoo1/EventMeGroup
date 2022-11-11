package com.example.eventmegroup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity
{
    Event selectedEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSelectedShape();
        setValues();

    }

    private void getSelectedShape()
    {
        Intent previousIntent = getIntent();
        String parsedStringID = previousIntent.getStringExtra("id");
        selectedEvent = Explore.events.get(Integer.valueOf(parsedStringID));
    }

    private void setValues()
    {
        TextView tv = (TextView) findViewById(R.id.shapeName);
        tv.setText(selectedEvent.getName());
    }
}
