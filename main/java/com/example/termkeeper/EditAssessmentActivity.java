package com.example.termkeeper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditAssessmentActivity extends AppCompatActivity {

    DBConnector dbHelper = new DBConnector(this);
    Assessments modifiedAssessment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_assessment);



        //initialize passed variable
        Bundle bundle = getIntent().getExtras();
        int selectedAssessmentId = bundle.getInt("selectedAssessmentId");



        //initialize textfields with modified course data
        modifiedAssessment = dbHelper.selectAssessment(selectedAssessmentId);
        Button addAssessmentButton = findViewById(R.id.buttonAddAssessment);

        final int assessment_id = selectedAssessmentId;
        final EditText etTitle = findViewById(R.id.etNotes);
        final EditText etStartDate = findViewById(R.id.etStartDate);
        final EditText etEndDate = findViewById(R.id.etEndDate);
        final EditText etStatus = findViewById(R.id.etStatus);


        etTitle.setText(modifiedAssessment.getTitle());
        etStartDate.setText(modifiedAssessment.getDueDate());
        etEndDate.setText(modifiedAssessment.getNotes());
        etStatus.setText(modifiedAssessment.getShareText());


        addAssessmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int course_id = modifiedAssessment.getCourse_id();
                String title = etTitle.getText().toString();
                String notes = etEndDate.getText().toString();
                String dueDate = etStartDate.getText().toString();
                String shareText = etStatus.getText().toString();



                dbHelper.updateAssessment(assessment_id, title, notes, dueDate, shareText, course_id);
                System.out.println("assessment was successfully updated");

                Bundle bundle = new Bundle();
                bundle.putInt("selectedCourseId", course_id);
                Intent intent = new Intent(EditAssessmentActivity.this, DisplayCourseActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
