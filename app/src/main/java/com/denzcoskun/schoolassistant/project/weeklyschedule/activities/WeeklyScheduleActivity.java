package com.denzcoskun.schoolassistant.project.weeklyschedule.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.denzcoskun.schoolassistant.R;
import com.denzcoskun.schoolassistant.base.activities.BaseActivity;
import com.denzcoskun.schoolassistant.project.activities.HomeActivity;
import com.denzcoskun.schoolassistant.project.weeklyschedule.adapters.LessonAdapter;
import com.denzcoskun.schoolassistant.project.weeklyschedule.adapters.TabsPagerAdapter;
import com.denzcoskun.schoolassistant.project.weeklyschedule.constants.LessonConstants;
import com.denzcoskun.schoolassistant.project.weeklyschedule.models.DayModel;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;

public class WeeklyScheduleActivity extends BaseActivity {

    @BindView(R.id.tablayout)
    TabLayout tabLayout;

    @BindView(R.id.pager)
    ViewPager viewPager;

    @BindView(R.id.lesson_add_button)
    FloatingActionButton floatingActionButton;

    public static LessonAdapter[] lessonAdapters = new LessonAdapter[7];

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        Objects.requireNonNull(getSupportActionBar()).setElevation(0);

        if (HomeActivity.mainModel.dayModels == null) {
            initDays();
        }

        TabsPagerAdapter tabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager(), getBaseContext(), HomeActivity.mainModel.dayModels);

        for (int i = 0; i < 7; i++) {
            tabLayout.addTab(tabLayout.newTab());
        }

        viewPager.setAdapter(tabsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        floatingActionButton.setOnClickListener(v -> {
            Intent i = new Intent(WeeklyScheduleActivity.this, AddLessonActivity.class);
            i.putExtra(LessonConstants.POSITION, tabLayout.getSelectedTabPosition());
            startActivity(i);
        });
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    public void initDays() {
        HomeActivity.mainModel.dayModels = new ArrayList<>();
        HomeActivity.mainModel.dayModels.add(new DayModel(getString(R.string.monday)));
        HomeActivity.mainModel.dayModels.add(new DayModel(getString(R.string.tuesday)));
        HomeActivity.mainModel.dayModels.add(new DayModel(getString(R.string.wednesday)));
        HomeActivity.mainModel.dayModels.add(new DayModel(getString(R.string.thursday)));
        HomeActivity.mainModel.dayModels.add(new DayModel(getString(R.string.friday)));
        HomeActivity.mainModel.dayModels.add(new DayModel(getString(R.string.saturday)));
        HomeActivity.mainModel.dayModels.add(new DayModel(getString(R.string.sunday)));

        HomeActivity.mainModel.lessonsNames = new ArrayList<>();
        HomeActivity.mainModel.lessonsNames.add(getString(R.string.science));
        HomeActivity.mainModel.lessonsNames.add(getString(R.string.mathematics));
        HomeActivity.mainModel.lessonsNames.add(getString(R.string.history));
    }
}
