package com.example.termkeeper.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.termkeeper.R;

public class CourseAdapter extends CursorAdapter {

    public CourseAdapter(Context context, Cursor cursor){
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent){
        return LayoutInflater.from(context).inflate(R.layout.activity_course_list_adapter, parent, false);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor){
        //Find fields to populate the inflated template
        TextView tvCourseName = (TextView)view.findViewById(R.id.courseTitle);
        TextView tvCourseStart = (TextView)view.findViewById(R.id.courseStart);
        TextView tvCourseEnd = (TextView)view.findViewById(R.id.courseEnd);
        TextView tvCourseAssessments = (TextView)view.findViewById(R.id.courseAssessments);
        //extract properties from Cursor
        String courseTitle = cursor.getString(cursor.getColumnIndexOrThrow("title"));
        String courseStart = cursor.getString(cursor.getColumnIndexOrThrow("start"));
        String courseEnd = cursor.getString(cursor.getColumnIndexOrThrow("endTEXT"));
        String courseAssessment = cursor.getString(cursor.getColumnIndexOrThrow("mentor"));

        //populate fields with extracted properties
        tvCourseName.setText(courseTitle);
        tvCourseStart.setText(courseStart);
        tvCourseEnd.setText(courseEnd);
        tvCourseAssessments.setText(courseAssessment);
    }


}
