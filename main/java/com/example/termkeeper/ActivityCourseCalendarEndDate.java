package com.example.termkeeper;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class ActivityCourseCalendarEndDate extends AppCompatActivity {

    long timeInMillis;
    Button saveAlertButton;
    int selectedTermId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_end_date_calendar);
        setTitle("Course Alerts");

        //initialize variable from previous intent
        Bundle bundle = getIntent().getExtras();
        selectedTermId = bundle.getInt("selectedTermId");


        //initialize the calendar
        saveAlertButton = findViewById(R.id.buttonSaveAlert);
        CalendarView datePicker = findViewById(R.id.courseCalendar);
        datePicker.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int dayOfMonth) {
                Calendar c = Calendar.getInstance();
                c.set(year, month, dayOfMonth, 0, 0);
                timeInMillis = c.getTimeInMillis();
                System.out.println(timeInMillis);

            }
        });

        saveAlertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityCourseCalendarEndDate.this, MyReceiver.class);
                intent.putExtra("key", "Class End Date");
                intent.putExtra("key2", "You have a class ending today");
                PendingIntent sender = PendingIntent.getBroadcast(ActivityCourseCalendarEndDate.this, 0, intent, 0);
                AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, timeInMillis, sender);
                System.out.println(timeInMillis);

                //go back to previous screen
                Intent previousScreen = new Intent(ActivityCourseCalendarEndDate.this, DisplayTermActivity.class);
                previousScreen.putExtra("selectedTermId", selectedTermId);
                startActivity(previousScreen);
            }
        });



    }
}

