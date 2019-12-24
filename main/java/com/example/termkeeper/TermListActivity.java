package com.example.termkeeper;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.termkeeper.Adapters.TermAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TermListActivity extends AppCompatActivity {


    DBConnector dbHelper = new DBConnector(this);

    public TermAdapter termAdapter;
    ListView listTerms;





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.showOverflowMenu();






        //can be used in portrait or landscape depending on which is set
        Button toggleLandscapeBtn = findViewById(R.id.buttonToggleLandscape);
        Button togglePortraitBtn = findViewById(R.id.buttonTogglePortrait);

        toggleLandscapeBtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View arg0){
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
        });

        togglePortraitBtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View arg0){
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        });


        //Arraylist of term data
        ArrayList termList = dbHelper.getTermData();
        ArrayAdapter arrayAdapter;



        final Cursor termData = dbHelper.getTermListData();
        listTerms = findViewById(R.id.list_terms);
        termAdapter  = new TermAdapter(this, termData);
        listTerms.setAdapter(termAdapter);


        registerForContextMenu(listTerms);


        initializeDisplayContent();


        //initialize the FAB w/ listener
        FloatingActionButton addFab = findViewById(R.id.termAddFab);
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TermListActivity.this, AddTermActivity.class);
                startActivity(intent);

            }
        }
        );
    }

    //action bar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.addTerm){
            Intent intent = new Intent(TermListActivity.this, AddTermActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.hold_menu_term, menu);

    }



    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int selectedItemId = (int)info.id;

        System.out.println(info.id);




        switch (item.getItemId()) {
            case R.id.option_1:
                    System.out.println(selectedItemId + " position on context method");

                   { dbHelper.deleteTerm(selectedItemId);

                   //refreshing activity to update listview
                   finish();
                   startActivity(getIntent());
                   return true;
                    }
            default:
                return super.onContextItemSelected(item);

        }

    }




    private void initializeDisplayContent(){
        final ListView listTerms = findViewById(R.id.list_terms);
        Cursor termData = dbHelper.getTermListData();
        TermAdapter termAdapter = new TermAdapter(this, termData);
        listTerms.setAdapter(termAdapter);




        listTerms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Cursor cursorAtPos = (Cursor)adapterView.getItemAtPosition(position);
                int selectedTermId = cursorAtPos.getInt(0);
                Intent intent = new Intent(TermListActivity.this, DisplayTermActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("selectedTermId", selectedTermId);
                intent.putExtras(bundle);
                startActivity(intent);
            }

        });
    }
}
