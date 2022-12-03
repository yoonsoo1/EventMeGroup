package com.example.eventmegroup;

import static java.lang.Integer.parseInt;

public class EventDate {
    private int month;
    private int startDay;
    private int endDay;
    private int year;
    private int startHour;
    private int startMin;
    private int endHour;
    private int endMin;
    private int duration;

    EventDate(String date, String startTime, String duration) {
        this.duration = parseInt(duration);
        String[] dates = date.split("[/]+");
        this.month = parseInt(dates[0]);
        this.startDay = parseInt(dates[1]);
        this.year = parseInt(dates[2]);
        String[] time = startTime.split(" ");
        String[] hm = time[0].split("[:]+");
        if(hm.length > 1) {
            startMin = parseInt(hm[1]);
        }
        else {
            startMin = 0;
        }
        // Change the time to military time for easiness of comparison
        if(time[1].equals("AM")) {
            startHour = parseInt(hm[0]);
        }
        else {
            startHour = 12 + parseInt(hm[0]);
        }
        endHour = startHour + this.duration;
        if(endHour >= 24) {
            endHour -= 24;
            endDay = startDay + 1;
        }
        else {
            endDay = startDay;
        }
        endMin = startMin;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getDuration() {
        return duration;
    }

    public int getEndDay() {
        return endDay;
    }

    public int getEndHour() {
        return endHour;
    }

    public int getEndMin() {
        return endMin;
    }

    public int getStartDay() {
        return startDay;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getStartMin() {
        return startMin;
    }
}