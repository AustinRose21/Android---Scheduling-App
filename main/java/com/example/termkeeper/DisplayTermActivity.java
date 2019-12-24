package com.example.termkeeper;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.example.termkeeper.Adapters.CourseAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class DisplayTermActivity extends AppCompatActivity {

    DBConnector dbHelper = new DBConnector(this);
     int selectedTermId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_term);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //initialize passed variable
        Bundle bundle = getIntent().getExtras();
        selectedTermId = bundle.getInt("selectedTermId");



        //initialize display content for listview
        final ListView listCourseData = findViewById(R.id.lvTermCourses);
        registerForContextMenu(listCourseData);

        Cursor courseData = dbHelper.getCourseData(selectedTermId);
        System.out.println(selectedTermId + "courses for term id are being passed");
        CourseAdapter courseAdapter = new CourseAdapter(this, courseData);
        listCourseData.setAdapter(courseAdapter);


        //intent on action button to add courses
        FloatingActionButton fab = findViewById(R.id.courseAddFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DisplayTermActivity.this, AddCourseActivity.class);
                intent.putExtra("selectedTermId", selectedTermId);
                startActivity(intent);
            }
        });

        //assign listener when a course is clicked in the listview
        listCourseData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Cursor cursorAtPos = (Cursor)adapterView.getItemAtPosition(position);
                int selectedCourseId = cursorAtPos.getInt(0);
                Bundle bundle = new Bundle();
                bundle.putInt("selectedCourseId", selectedCourseId);
                Intent intent = new Intent(DisplayTermActivity.this, DisplayCourseActivity.class);
                intent.putExtras(bundle);
                System.out.println(selectedCourseId);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.hold_menu_course, menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int selectedItemId = (int) info.id;

        switch (item.getItemId()) {

            case R.id.option_1: {
                dbHelper.deleteCourse(selectedItemId);
                finish();
                startActivity(getIntent());
                System.out.println(info.id + "Course ID being deleted");
                System.out.println("Course was successfully deleted");
                return true;
            }

            case R.id.option_2:
                Intent intent = new Intent(DisplayTermActivity.this, EditCourseActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("selectedCourseId", selectedItemId);
                intent.putExtras(bundle);
                startActivity(intent);
                return true;

            case R.id.option_3:
                Intent alertIntent = new Intent(DisplayTermActivity.this, ActivityCourseCalendar.class);
                Bundle alertBundle = new Bundle();
                alertBundle.putInt("selectedTermId", selectedTermId);
                alertBundle.putInt("selectedCourseId", selectedItemId);
                alertIntent.putExtras(alertBundle);
                startActivity(alertIntent);
                return true;

            case R.id.option_4:
               Intent notificationIntent = new Intent();
               notificationIntent.setAction(Intent.ACTION_SEND);
               notificationIntent.putExtra(Intent.EXTRA_TEXT, dbHelper.selectNote(selectedItemId));

               notificationIntent.setType("text/plain");
               Intent shareIntent = Intent.createChooser(notificationIntent, null);
               startActivity(shareIntent);
               return true;

            case R.id.option_5:
                Intent intentNote = new Intent(DisplayTermActivity.this, AddNoteActivity.class);
                Bundle bundleNote = new Bundle();
                bundleNote.putInt("selectedCourseId", selectedItemId);
                bundleNote.putInt("selectedTermId", selectedTermId);
                intentNote.putExtras(bundleNote);
                startActivity(intentNote);
                return true;

            case R.id.option_6:
                Intent alertIntent2 = new Intent(DisplayTermActivity.this, ActivityCourseCalendarEndDate.class);
                Bundle alertBundle2 = new Bundle();
                alertBundle2.putInt("selectedTermId", selectedTermId);
                alertBundle2.putInt("selectedCourseId", selectedItemId);
                alertIntent2.putExtras(alertBundle2);
                startActivity(alertIntent2);
                return true;

            default:
                return super.onContextItemSelected(item);

        }
    }
}
