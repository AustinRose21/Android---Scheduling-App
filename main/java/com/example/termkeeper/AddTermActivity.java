package com.example.termkeeper;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddTermActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_term);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button saveTermBtn = findViewById(R.id.buttonAddTerm);
        saveTermBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSaveTerm();
                Intent intent = new Intent(AddTermActivity.this, TermListActivity.class);
                startActivity(intent);
            }
        });


            }


    public void handleSaveTerm(){
        DBConnector myDb = new DBConnector(this);

        EditText tvTitle = findViewById(R.id.etNotes);
        EditText tvStart = findViewById(R.id.etStartDate);
        EditText tvEnd = findViewById(R.id.etEndDate);

        String title = tvTitle.getText().toString();
        String start = tvStart.getText().toString();
        String end = tvEnd.getText().toString();


            if(tvTitle != null && tvStart != null && tvEnd != null){
                myDb.insertTerm(title, start, end);

            }
            else{
                System.out.println("An error occurred adding term");
            }

            }

    }


