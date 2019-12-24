package com.example.termkeeper;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.example.termkeeper.Adapters.AssessmentAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class DisplayCourseActivity extends AppCompatActivity {

    DBConnector dbHelper = new DBConnector(this);
    int selectedCourseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_course);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initialize variable from previous intent
        Bundle bundle = getIntent().getExtras();
        selectedCourseId = bundle.getInt("selectedCourseId");


        //initialize display content for listview
        final ListView listAssessmentData = findViewById(R.id.lvCourseInfo);
       // final TextView etAssessment = findViewById(R.id.assessmentTitle);
        registerForContextMenu(listAssessmentData);

        Cursor assessmentData = dbHelper.getAssessmentData(selectedCourseId);
        //etAssessment.setText(dbHelper.getAssessmentsForCourse(selectedCourseId));
        // System.out.println(selectedCourseId + "courses for term id are being passed");
        AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this, assessmentData);
        listAssessmentData.setAdapter(assessmentAdapter);



        //initialize the addfab
        FloatingActionButton fab = findViewById(R.id.addAssessFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = getIntent().getExtras();
                int selectedCourseId = bundle.getInt("selectedCourseId");
                Intent intent = new Intent(DisplayCourseActivity.this, AddAssessmentActivity.class);
                intent.putExtra("selectedCourseId", selectedCourseId);
                System.out.println(selectedCourseId + "Is being passed into AddAssessmentActivity");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.hold_menu_assessment, menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int selectedItemId = (int) info.id;

        switch (item.getItemId()) {

            case R.id.option_1: {
                dbHelper.deleteAssessment(selectedItemId);
                finish();
                startActivity(getIntent());
                System.out.println(info.id + "Assessment ID being deleted");
                System.out.println("Assessment was successfully deleted");
                return true;
            }

            case R.id.option_2:
                Intent intent = new Intent(DisplayCourseActivity.this, EditAssessmentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("selectedAssessmentId", selectedItemId);
                intent.putExtras(bundle);
                startActivity(intent);
                return true;

            case R.id.option_3:
                Intent intent2 = new Intent(DisplayCourseActivity.this, ActivityAssessmentGoalDate.class);
                Bundle bundle2 = new Bundle();
                bundle2.putInt("selectedAssessmentId", selectedItemId);
                intent2.putExtras(bundle2);
                intent2.putExtra("selectedCourseId", selectedCourseId);
                startActivity(intent2);
                return true;

            default:
                return super.onContextItemSelected(item);


        }

    }
    }