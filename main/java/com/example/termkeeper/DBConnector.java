package com.example.termkeeper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;


public final class DBConnector extends SQLiteOpenHelper {
    //database name
    public static final String DATABASE_NAME = "Termkeeper.db";
    //term table
    public static final String TABLE_NAME_TERM = "Term_table";
    public static final String COL_1_term = "_id";
    public static final String COL_2_term = "title";
    public static final String COL_3_term = "start";
    public static final String COL_4_term = "endTEXT";
    //course table
    public static final String TABLE_NAME_COURSE = "Course_table";
    public static final String COL_1_course = "_id";
    public static final String COL_2_course = "title";
    public static final String COL_3_course = "start";
    public static final String COL_4_course = "endTEXT";
    public static final String COL_5_course = "status";
    public static final String COL_6_course = "mentor";
    public static final String COL_7_course = "term_id";
    // public static final String COL_8_course = "note";
    //note table
    public static final String TABLE_NAME_NOTE = "Note_table";
    public static final String COL_1_note = "note_id";
    public static final String COL_2_note = "note";
    public static final String COL_3_note = "course_id";
    //assessment table
    public static final String TABLE_NAME_ASSESSMENT = "Assessment_table";
    private static final String COL_1_assessment = "_id";
    private static final String COL_2_assessment = "title";
    private static final String COL_3_assessment = "notes";
    private static final String COL_4_assessment = "due_date";
    private static final String COL_5_assessment = "shareFeature";
    private static final String COL_6_assessment = "course_id";


    public DBConnector(Context context) {
        super(context, DATABASE_NAME, null, 1);
        // SQLiteDatabase myDb = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE " + TABLE_NAME_TERM + "(" +
                        COL_1_term + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COL_2_term + " TEXT NOT NULL," +
                        COL_3_term + " TEXT NOT NULL," +
                        COL_4_term + " TEXT NOT NULL);");

        sqLiteDatabase.execSQL(
                "CREATE TABLE " + TABLE_NAME_COURSE + "(" +
                        COL_1_course + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COL_2_course + " TEXT NOT NULL," +
                        COL_3_course + " TEXT NOT NULL," +
                        COL_4_course + " TEXT NOT NULL," +
                        COL_5_course + " TEXT NOT NULL," +
                        COL_6_course + " TEXT NOT NULL," +
                        COL_7_course + " INTEGER NOT NULL," +
                        // COL_8_course + " TEXT," +
                        "FOREIGN KEY(" + COL_7_course + ") " +
                        "REFERENCES " + TABLE_NAME_TERM + "(" + COL_1_term + "));");


        sqLiteDatabase.execSQL(
                "CREATE TABLE " + TABLE_NAME_NOTE + "(" +
                        COL_1_note + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COL_2_note + " TEXT NOT NULL," +
                        COL_3_note + " INTEGER NOT NULL," +
                        "FOREIGN KEY(" + COL_3_note + ") " +
                        "REFERENCES " + TABLE_NAME_COURSE + "(" + COL_1_course + "));");

        sqLiteDatabase.execSQL(
                "CREATE TABLE " + TABLE_NAME_ASSESSMENT + "(" +
                        COL_1_assessment + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COL_2_assessment + " TEXT NOT NULL," +
                        COL_3_assessment + " TEXT NOT NULL," +
                        COL_4_assessment + " TEXT NOT NULL," +
                        COL_5_assessment + " TEXT," +
                        COL_6_assessment + " INTEGER NOT NULL," +
                        "FOREIGN KEY(" + COL_6_assessment + ") " +
                        "REFERENCES " + TABLE_NAME_COURSE + "(" + COL_1_course + "));");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_TERM + ";");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_COURSE + ";");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_NOTE + ";");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ASSESSMENT + ";");
        onCreate(sqLiteDatabase);
    }

    public ArrayList<Term> getTermData() {
        ArrayList<Term> termList = new ArrayList<>();

        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor res = myDb.rawQuery("SELECT * FROM " + TABLE_NAME_TERM, null);
        res.moveToFirst();
        termList.clear();
        while (!res.isAfterLast()) {

            int termId = res.getInt(0);
            String termTitle = res.getString(1);
            String termStart = res.getString(2);
            String termEnd = res.getString(3);
            termList.add(new Term(termId, termTitle, termStart, termEnd));
            res.moveToNext();
        }
        res.close();
        return termList;
    }

    public Cursor getTermListData() {
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor res = myDb.rawQuery("SELECT * FROM " + TABLE_NAME_TERM, null);
        return res;
    }

    public boolean insertTerm(String title, String start, String endTEXT) {
        SQLiteDatabase myDb = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2_term, title);
        cv.put(COL_3_term, start);
        cv.put(COL_4_term, endTEXT);
        long result = myDb.insert(TABLE_NAME_TERM, null, cv);
        if (result == -1)
            return false;

        else return true;
    }

    public boolean insertCourse(String title, String start, String end, String status, String mentorInfo, int termId) {
        SQLiteDatabase myDb = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2_course, title);
        cv.put(COL_3_course, start);
        cv.put(COL_4_course, end);
        cv.put(COL_5_course, status);
        cv.put(COL_6_course, mentorInfo);
        cv.put(COL_7_course, termId);
        long result = myDb.insert(TABLE_NAME_COURSE, null, cv);
        if (result == -1)
            return false;

        else return true;
    }

    public boolean insertNote(int courseId, String note) {
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor res = myDb.rawQuery("SELECT * FROM Note_table WHERE course_id = '" + courseId + "' ", null);

        ContentValues cv = new ContentValues();
        cv.put(COL_2_note, note);
        cv.put(COL_3_note, courseId);

        if (res.getCount() == 0) {
            long result = myDb.insert(TABLE_NAME_NOTE, null, cv);
            myDb.close();
            if (result == -1)
                return false;
            else return true;
        } else {
            System.out.println("Note already exists for this course");
            return false;
        }

    }

    public Cursor getCourseData(int termId) {
        System.out.println(termId);
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor res = myDb.rawQuery("SELECT * FROM Course_table WHERE term_id = '" + termId + "' ", null);
        return res;
    }

 /*   public String getAssessmentsForCourse(int course_id){
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor res = myDb.rawQuery("SELECT * FROM Assessment_table WHERE course_id = '" + course_id + "' ", null);
        if(res.moveToFirst()){
        String assessmentData = res.toString();
        return assessmentData;}

        else {
            String assessmentData = "";
            System.out.println("no assessment data found for course");
            return assessmentData;

        }
    }*/

    public boolean insertAssessment(String title, String notes, String dueDate, String share, int courseId) {
        SQLiteDatabase myDb = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2_assessment, title);
        cv.put(COL_3_assessment, notes);
        cv.put(COL_4_assessment, dueDate);
        cv.put(COL_5_assessment, share);
        cv.put(COL_6_assessment, courseId);
        long result = myDb.insert(TABLE_NAME_ASSESSMENT, null, cv);
        if (result == -1)
            return false;

        else return true;
    }

    public Cursor getAssessmentData(int courseId) {
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor res = myDb.rawQuery("SELECT * FROM Assessment_table WHERE course_id = '" + courseId + "' ", null);
        return res;
    }


    public void deleteTerm(int termId) {
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor res = myDb.rawQuery("SELECT * FROM Course_table WHERE term_id = '" + termId + "' ", null);

        System.out.println(termId + "Is being sent to deleteTerm()");

        if (res.getCount() == 0) {
            System.out.println("TERM HAS NO COURSES");
            myDb.delete(TABLE_NAME_TERM, COL_1_term + " = " + termId, null);
            System.out.println("Term successfully deleted");
            res.close();

        } else {
            System.out.println("TERM HAS COURSES IN IT, NOT DELETING");
            res.close();

        }
    }

    public void deleteCourse(int courseId) {
        SQLiteDatabase myDb = this.getWritableDatabase();
        myDb.delete(TABLE_NAME_COURSE, COL_1_course + " = " + courseId, null);
        System.out.println("Course successfully deleted");
    }


    public Course selectCourse(int courseId) {

        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor res = myDb.rawQuery("SELECT * FROM Course_table WHERE _id = '" + courseId + "' ", null);
        Course modifiedCourse;

        res.moveToFirst();
        modifiedCourse = new Course(
                res.getInt(0),
                res.getString(1),
                res.getString(2),
                res.getString(3),
                res.getString(4),
                res.getString(5),
                res.getInt(6)
        );
        return modifiedCourse;
    }


    public boolean updateCourse(int courseId, String title, String start, String end, String status, String mentorInfo, int termId) {
        SQLiteDatabase myDb = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = "_id =" + courseId;
        cv.put(COL_1_course, courseId);
        cv.put(COL_2_course, title);
        cv.put(COL_3_course, start);
        cv.put(COL_4_course, end);
        cv.put(COL_5_course, status);
        cv.put(COL_6_course, mentorInfo);
        cv.put(COL_7_course, termId);
        long result = myDb.update(TABLE_NAME_COURSE, cv, whereClause, null);
        if (result == -1)
            return false;

        else return true;
    }

    public String selectNote(int courseId) {
        SQLiteDatabase dbHelper = this.getWritableDatabase();
        String error = "note selection failed";
        String selectedNote;

        Cursor res = dbHelper.rawQuery("SELECT note FROM Note_table WHERE course_id = '" + courseId + "' ", null);
        // String selectedNote = res.getString(res.getColumnIndex("note"));
        if (res.moveToFirst()) {
            selectedNote = res.getString(res.getColumnIndexOrThrow("note"));
            return selectedNote;
        }
        else{
            return error;
        }
    }


    public void deleteAssessment(int assessmentId) {
        SQLiteDatabase myDb = this.getWritableDatabase();
        myDb.delete(TABLE_NAME_ASSESSMENT, COL_1_course + " = " + assessmentId, null);
        System.out.println("Assessment successfully deleted");
    }

    public boolean updateAssessment(int assessmentId, String title, String notes, String dueDate, String share, int courseId) {
        SQLiteDatabase myDb = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String whereClause = "_id =" + assessmentId;
        cv.put(COL_1_assessment, assessmentId);
        cv.put(COL_2_assessment, title);
        cv.put(COL_3_assessment, notes);
        cv.put(COL_4_assessment, dueDate);
        cv.put(COL_5_assessment, share);
        cv.put(COL_6_assessment, courseId);
        long result = myDb.update(TABLE_NAME_ASSESSMENT, cv, whereClause, null);
        if (result == -1)
            return false;

        else return true;
    }

    public Assessments selectAssessment(int assessmentId) {

        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor res = myDb.rawQuery("SELECT * FROM Assessment_table WHERE _id = '" + assessmentId + "' ", null);
        Assessments modifiedAssessment;

        res.moveToFirst();
        modifiedAssessment = new Assessments(
                res.getInt(0),
                res.getString(1),
                res.getString(2),
                res.getString(3),
                res.getString(4),
                res.getInt(5)
        );
        return modifiedAssessment;
    }
}




