package com.example.termkeeper;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddAssessmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assessment);
        setTitle("Add Assessment");


        //add listener to save button
        Button saveAssessmentBtn = findViewById(R.id.buttonAddAssessment);
        saveAssessmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSaveAssessment();
                Bundle bundle = getIntent().getExtras();
                int selectedCourseId = bundle.getInt("selectedCourseId");
                Intent intent = new Intent(AddAssessmentActivity.this, DisplayCourseActivity.class);
                intent.putExtra("selectedCourseId", selectedCourseId);
                System.out.println(selectedCourseId + "Is being passed back to DisplayCourseActivity from add assessment");
                startActivity(intent);
            }
        });

    }



    public void handleSaveAssessment(){
        //initialize passed variable
        Bundle bundle = getIntent().getExtras();
        final int selectedCourseId = bundle.getInt("selectedCourseId");
        System.out.println(selectedCourseId + "Is being passed into SaveAssessment()");

        DBConnector myDb = new DBConnector(this);


        EditText etTitle = findViewById(R.id.etNotes);
        EditText etEnd = findViewById(R.id.etStartDate);
        EditText etNotes = findViewById(R.id.etEndDate);
        EditText etShare = findViewById(R.id.etStatus);



        String assessmentTitle = etTitle.getText().toString();
        String assessmentDueDate = etEnd.getText().toString();
        String assessmentNotes = etNotes.getText().toString();
        String assessmentShare = etShare.getText().toString();


        if(etTitle != null || etEnd != null || etNotes != null){
            myDb.insertAssessment(assessmentTitle, assessmentNotes, assessmentDueDate, assessmentShare, selectedCourseId);
        }
        else{
            System.out.println("An error occurred while adding assessment");
        }
    }

}

