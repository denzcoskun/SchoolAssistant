package com.denzcoskun.schoolassistant.weeklyschedule.models;

import java.io.Serializable;

/**
 * Created by Denx on 12.02.2018.
 */

public class LessonModel implements Serializable {
    private String name;
    private String classroom;
    private String startTime;
    private String finishTime;

    public LessonModel(String name, String classroom, String startTime, String finishTime) {
        this.name = name;
        this.classroom = classroom;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }
}
