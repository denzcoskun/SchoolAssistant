package com.denzcoskun.schoolassistant.project.screens.exams.models;

/**
 * Created by Denx on 30.05.2018.
 */
public class ExamModel {
    private String examName;
    private String examSubject;
    private String examDate;
    private String examTime;

    public ExamModel(String examName, String examSubject, String examDate, String examTime) {
        this.examName = examName;
        this.examSubject = examSubject;
        this.examDate = examDate;
        this.examTime = examTime;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getExamSubject() {
        return examSubject;
    }

    public void setExamSubject(String examSubject) {
        this.examSubject = examSubject;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getExamTime() {
        return examTime;
    }

    public void setExamTime(String examTime) {
        this.examTime = examTime;
    }
}
