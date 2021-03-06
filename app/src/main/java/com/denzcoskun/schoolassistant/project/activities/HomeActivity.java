package com.denzcoskun.schoolassistant.project.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.denzcoskun.schoolassistant.R;
import com.denzcoskun.schoolassistant.base.activities.BaseActivity;
import com.denzcoskun.schoolassistant.project.helpers.DataHelper;
import com.denzcoskun.schoolassistant.project.models.MainModel;
import com.denzcoskun.schoolassistant.project.screens.exams.activities.ExamsActivity;
import com.denzcoskun.schoolassistant.project.screens.homework.activities.HomeworkActivity;
import com.denzcoskun.schoolassistant.project.screens.weeklyschedule.activities.WeeklyScheduleActivity;

import butterknife.BindView;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.linearlayout_calendar)
    LinearLayout linearLayoutCalendar;

    @BindView(R.id.linearlayout_homework)
    LinearLayout linearLayoutHomeworks;

    @BindView(R.id.linearlayout_exams)
    LinearLayout linearLayoutExams;

    public static MainModel mainModel = new MainModel();

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        DataHelper dataHelper = new DataHelper(HomeActivity.this);

        if (dataHelper.getModel() != null) {
            mainModel = dataHelper.getModel();
        }

        linearLayoutCalendar.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, WeeklyScheduleActivity.class)));
        linearLayoutHomeworks.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, HomeworkActivity.class)));
        linearLayoutExams.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this,ExamsActivity.class)));
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_home;
    }

}
