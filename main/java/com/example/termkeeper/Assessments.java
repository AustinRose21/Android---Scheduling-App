package com.example.termkeeper;

public class Assessments {

            private int _id;
            private String title;
            private String notes;
            private String dueDate;
            private String shareText;
            private int course_id;

            //full constructor
    public Assessments(int _id, String title, String notes, String dueDate, String shareText, int course_id) {
        this._id = _id;
        this.title = title;
        this.notes = notes;
        this.dueDate = dueDate;
        this.shareText = shareText;
        this.course_id = course_id;
    }


    //getters
    public int get_id() {
        return _id;
    }

    public String getTitle() {
        return title;
    }

    public String getNotes() {
        return notes;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getShareText() {
        return shareText;
    }

    public int getCourse_id() {
        return course_id;
    }

    //setters

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setShareText(String shareText) {
        this.shareText = shareText;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }
}
