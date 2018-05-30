package com.denzcoskun.schoolassistant.project.screens.homework.models;

/**
 * Created by Denx on 26.02.2018.
 */

public class HomeworkModel {
    private String homeworkName;
    private String homeworkSubject;
    private String homeworkDate;

    public HomeworkModel(String homeworkName, String homeworkSubject, String homeworkDate) {
        this.homeworkName = homeworkName;
        this.homeworkSubject = homeworkSubject;
        this.homeworkDate = homeworkDate;
    }

    public String getHomeworkName() {
        return homeworkName;
    }

    public void setHomeworkName(String homeworkName) {
        this.homeworkName = homeworkName;
    }

    public String getHomeworkSubject() {
        return homeworkSubject;
    }

    public void setHomeworkSubject(String homeworkSubject) {
        this.homeworkSubject = homeworkSubject;
    }

    public String getHomeworkDate() {
        return homeworkDate;
    }

    public void setHomeworkDate(String homeworkDate) {
        this.homeworkDate = homeworkDate;
    }
}
