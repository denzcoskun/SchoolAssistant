package com.denzcoskun.schoolassistant.weeklyschedule.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Denx on 12.02.2018.
 */

public class DayModel implements Serializable {
    private String name;
    private List<LessonModel> lessons;

    public DayModel(String name) {
        this.name = name;
        lessons = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LessonModel> getLessons() {
        return lessons;
    }

    public void setLessons(List<LessonModel> lessons) {
        this.lessons = lessons;
    }
}
