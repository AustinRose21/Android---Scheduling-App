package com.example.termkeeper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class EditCourseActivity extends AppCompatActivity {

    DBConnector dbHelper = new DBConnector(this);
    Course modifiedCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initialize passed variable
        Bundle bundle = getIntent().getExtras();
        final int selectedCourseId = bundle.getInt("selectedCourseId");

        //initialize textfields with modified course data
        modifiedCourse = dbHelper.selectCourse(selectedCourseId);
        Button addCourseButton = findViewById(R.id.buttonAddCourse);

        final EditText etTitle = findViewById(R.id.etNotes);
        final EditText etStartDate = findViewById(R.id.etStartDate);
        final EditText etEndDate = findViewById(R.id.etEndDate);
        final EditText etStatus = findViewById(R.id.etStatus);
        final EditText etMentor = findViewById(R.id.etCourseMentorInfo);

        etTitle.setText(modifiedCourse.getCourseTitle());
        etStartDate.setText(modifiedCourse.getCourseStartDate());
        etEndDate.setText(modifiedCourse.getCourseEndDate());
        etStatus.setText(modifiedCourse.getCourseStatus());
        etMentor.setText(modifiedCourse.getCourseMentor());

        addCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int course_id = selectedCourseId;
                String title = etTitle.getText().toString();
                String startDate = etStartDate.getText().toString();
                String endDate = etEndDate.getText().toString();
                String status = etStatus.getText().toString();
                String mentor = etMentor.getText().toString();
                int term_id = modifiedCourse.getTermId();

                dbHelper.updateCourse(course_id, title, startDate, endDate, status, mentor, term_id);
                System.out.println("course was successfully updated");

                Bundle bundle = new Bundle();
                bundle.putInt("selectedTermId", term_id);
                Intent intent = new Intent(EditCourseActivity.this, DisplayTermActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }
}
