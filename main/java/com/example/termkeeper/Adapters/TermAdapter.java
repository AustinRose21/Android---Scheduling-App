package com.example.termkeeper.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.termkeeper.R;

import java.util.List;


public class TermAdapter extends CursorAdapter{




    public TermAdapter(Context context, Cursor cursor){
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent){
        return LayoutInflater.from(context).inflate(R.layout.activity_termlist_adapter, parent, false);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor){
        //Find fields to populate the inflated template
        //TextView tvTermId = view.findViewById(R.id.termId);
        TextView tvTermName = (TextView)view.findViewById(R.id.termName);
        TextView tvTermStart = (TextView)view.findViewById(R.id.termStart);
        TextView tvTermEnd = (TextView)view.findViewById(R.id.termEnd);
        //extract properties from Cursor
        //int termId =cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
        String termTitle = cursor.getString(cursor.getColumnIndexOrThrow("title"));
        String termStart = cursor.getString(cursor.getColumnIndexOrThrow("start"));
        String termEnd = cursor.getString(cursor.getColumnIndexOrThrow("endTEXT"));
        //populate fields with extracted properties
        //tvTermId.setText(toString(termId));
        tvTermName.setText(termTitle);
        tvTermStart.setText(termStart);
        tvTermEnd.setText(termEnd);
    }













}