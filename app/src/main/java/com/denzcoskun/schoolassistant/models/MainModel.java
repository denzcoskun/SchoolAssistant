package com.denzcoskun.schoolassistant.models;


import com.denzcoskun.schoolassistant.screens.absence.models.AbsenceModel;
import com.denzcoskun.schoolassistant.screens.exams.models.ExamModel;
import com.denzcoskun.schoolassistant.screens.homework.models.HomeworkModel;
import com.denzcoskun.schoolassistant.screens.notes.models.NoteModel;
import com.denzcoskun.schoolassistant.screens.weeklyschedule.models.DayModel;

import java.util.List;

/**
 * Created by Denx on 15.02.2018.
 */

public class MainModel {
    public List<DayModel> dayModels;
    public List<String> lessonsNames;
    public List<HomeworkModel> homeworkModels;
    public List<ExamModel> examModels;
    public List<NoteModel> noteModels;
    public List<AbsenceModel> absenceModels;

}
