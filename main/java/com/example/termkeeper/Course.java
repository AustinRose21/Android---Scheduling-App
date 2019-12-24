package com.example.termkeeper;

import java.util.Date;

public class Course {

    private int _id;
    private String courseTitle;
    private String courseStartDate;
    private String courseEndDate;
    private String courseStatus;
    private String courseMentor;
    private int termId;

    //full constructor
    public Course (int _id, String courseTitle, String courseStartDate, String courseEndDate, String courseStatus, String courseMentor, int termId){
    this._id = _id;
    this.courseTitle = courseTitle;
    this.courseStartDate = courseStartDate;
    this.courseEndDate = courseEndDate;
    this.courseStatus = courseStatus;
    this.courseMentor = courseMentor;
    this.termId = termId;
    }

    //setters


    public void set_id(int _id) { this._id = _id; }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public void setCourseStartDate(String courseStartDate) { this.courseStartDate = courseStartDate; }

    public void setCourseEndDate(String courseEndDate) {
        this.courseEndDate = courseEndDate;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public void setCourseMentor(String courseMentor) {
        this.courseMentor = courseMentor;
    }

    public void setTermId(int termId){this.termId = termId;}


    //getters


    public int get_id() { return _id; }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getCourseStartDate() {
        return courseStartDate;
    }

    public String getCourseEndDate() {
        return courseEndDate;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public String getCourseMentor() {
        return courseMentor;
    }

    public int getTermId() {return termId;}
}
