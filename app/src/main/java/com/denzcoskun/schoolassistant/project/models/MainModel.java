package com.denzcoskun.schoolassistant.project.models;

import com.denzcoskun.schoolassistant.project.homework.models.HomeworkModel;
import com.denzcoskun.schoolassistant.project.weeklyschedule.models.DayModel;

import java.util.List;

/**
 * Created by Denx on 15.02.2018.
 */

public class MainModel {
    public List<DayModel> dayModels;
    public List<String> lessonsNames;
    public List<HomeworkModel> homeworkModels;
}
