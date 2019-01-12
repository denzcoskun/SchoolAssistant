package com.denzcoskun.schoolassistant.screens.absence.models;

/**
 * Created by Denx on 24.06.2018.
 */
public class AbsenceModel {
    private String courseName;
    private boolean status;
    private boolean kind;
    private String time;

    public AbsenceModel(String courseName, boolean status, boolean kind, String time) {
        this.courseName = courseName;
        this.status = status;
        this.kind = kind;
        this.time = time;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isKind() {
        return kind;
    }

    public void setKind(boolean kind) {
        this.kind = kind;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
