package com.denzcoskun.schoolassistant.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.denzcoskun.schoolassistant.R;
import com.denzcoskun.schoolassistant.helpers.DataHelper;
import com.denzcoskun.schoolassistant.homework.activities.HomeworkActivity;
import com.denzcoskun.schoolassistant.models.MainModel;
import com.denzcoskun.schoolassistant.weeklyschedule.activities.WeeklyScheduleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.linearlayout_calendar)
    LinearLayout linearLayoutCalendar;

    @BindView(R.id.linearlayout_homework)
    LinearLayout linearLayoutHomeworks;

    @BindView(R.id.linearlayout_exams)
    LinearLayout linearLayoutExams;

    public static MainModel mainModel = new MainModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);


        DataHelper dataHelper = new DataHelper(HomeActivity.this);

        if (dataHelper.getModel() != null) {
            mainModel = dataHelper.getModel();
        }

        linearLayoutCalendar.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this,WeeklyScheduleActivity.class)));
        linearLayoutHomeworks.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this,HomeworkActivity.class)));
        //linearLayoutCalendar.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this,.class)));

    }
}
