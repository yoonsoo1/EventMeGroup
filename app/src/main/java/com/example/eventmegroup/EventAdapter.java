package com.example.eventmegroup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class EventAdapter extends ArrayAdapter<Event>
{

    public EventAdapter(Context context, int resource, List<Event> eventList)
    {
        super(context,resource,eventList);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Event event = getItem(position);

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_cell, parent, false);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.eventName);

        tv.setText(event.getName());


        return convertView;
    }
}
