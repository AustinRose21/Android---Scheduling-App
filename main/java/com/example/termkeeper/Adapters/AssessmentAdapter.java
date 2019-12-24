package com.example.termkeeper.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.termkeeper.DBConnector;
import com.example.termkeeper.R;



public class AssessmentAdapter extends CursorAdapter {



    public AssessmentAdapter(Context context, Cursor cursor){
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent){
        return LayoutInflater.from(context).inflate(R.layout.activity_assessment_adapter, parent, false);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor){
        //Find fields to populate the inflated template
        TextView tvAssessTitle = view.findViewById(R.id.assessmentTitle);
        TextView tvAssessDueDate = view.findViewById(R.id.assessmentDueDate);
        TextView tvAssessNotes = view.findViewById(R.id.assessmentNotes);
        TextView tvAssessSharing = view.findViewById(R.id.assessmentSharing);

        String assessTitle = cursor.getString(cursor.getColumnIndexOrThrow("title"));
        String assessDueDate = cursor.getString(cursor.getColumnIndexOrThrow("due_date"));
        String assessNotes = cursor.getString(cursor.getColumnIndexOrThrow("notes"));
        String assessSharing = cursor.getString(cursor.getColumnIndexOrThrow("shareFeature"));

        //populate fields with extracted properties
        tvAssessTitle.setText(assessTitle);
        tvAssessDueDate.setText(assessDueDate);
        tvAssessNotes.setText(assessNotes);
        tvAssessSharing.setText(assessSharing);
    }
}
