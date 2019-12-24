package com.example.termkeeper;

import java.util.Date;

public class Term {

    private int termId;
    private String termTitle;
    private String termStartDate;
    private String termEndDate;



    //full constructor
    public Term(int termId,String termTitle, String termStartDate, String termEndDate) {
        this.termId = termId;
        this.termTitle = termTitle;
        this.termStartDate = termStartDate;
        this.termEndDate = termEndDate;

    }


    //setters
    public void setTermId(int termId) {this.termId = termId;}

    public void setTermTitle(String termTitle) {
        this.termTitle = termTitle;
    }

    public void setTermStartDate(String termStartDate) {
        this.termStartDate = termStartDate;
    }

    public void setTermEndDate(String termEndDate) {
        this.termEndDate = termEndDate;
    }

    //getters
    public int getTermId() {return termId;}

    public String getTermTitle() {
        return termTitle;
    }

    public String getTermStartDate() {
        return termStartDate;
    }

    public String getTermEndDate() {
        return termEndDate;
    }
}
