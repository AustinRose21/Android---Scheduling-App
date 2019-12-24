package com.example.termkeeper;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNoteActivity extends AppCompatActivity {

    DBConnector dbHelper = new DBConnector(this);
    EditText etNotes;
    String addedNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //initialize passed variable
        final Bundle bundle = getIntent().getExtras();
        final int selectedCourseId = bundle.getInt("selectedCourseId");
        final int selectedTermId = bundle.getInt("selectedTermId");

        etNotes = findViewById(R.id.etNotes);



        //add listener to save button
        Button saveNoteBtn = findViewById(R.id.buttonAddNote);
        saveNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(AddNoteActivity.this, DisplayTermActivity.class);

                    addedNote = etNotes.getText().toString();
                    dbHelper.insertNote(selectedCourseId, addedNote);
                    bundle.putInt("selectedTermId", selectedTermId);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
        });}
}

