package com.example.termkeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddCourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        //initialize passed variable
        final Bundle bundle = getIntent().getExtras();
        final int selectedTermId = bundle.getInt("selectedTermId");

        //add listener to save button
        Button saveCourseBtn = findViewById(R.id.buttonAddCourse);
        saveCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSaveCourse();
                Intent intent = new Intent(AddCourseActivity.this, DisplayTermActivity.class);
                bundle.putInt("selectedTermId", selectedTermId);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }



    public void handleSaveCourse(){

        DBConnector myDb = new DBConnector(this);

        //initialize passed variable
        Bundle bundle = getIntent().getExtras();
        final int selectedTermId = bundle.getInt("selectedTermId");


        EditText etTitle = findViewById(R.id.etNotes);
        EditText etStart = findViewById(R.id.etStartDate);
        EditText etEnd = findViewById(R.id.etEndDate);
        EditText etStatus = findViewById(R.id.etStatus);
        EditText etMentorInfo = findViewById(R.id.etCourseMentorInfo);



        String courseTitle = etTitle.getText().toString();
        String courseStart = etStart.getText().toString();
        String courseEnd = etEnd.getText().toString();
        String courseStatus = etStatus.getText().toString();
        String mentorInfo = etMentorInfo.getText().toString();

        if(etTitle != null && etStart != null && etEnd != null && etStatus !=null){
            myDb.insertCourse(courseTitle, courseStart, courseEnd, courseStatus, mentorInfo, selectedTermId);

        }
        else{
            System.out.println("An error occurred while adding course");
        }
    }
}
