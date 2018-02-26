package com.denzcoskun.schoolassistant.weeklyschedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.denzcoskun.schoolassistant.R;
import com.denzcoskun.schoolassistant.activities.HomeActivity;
import com.denzcoskun.schoolassistant.weeklyschedule.adapters.TabsPagerAdapter;
import com.denzcoskun.schoolassistant.helpers.DataHelper;
import com.denzcoskun.schoolassistant.models.MainModel;
import com.denzcoskun.schoolassistant.weeklyschedule.activities.AddLessonActivity;
import com.denzcoskun.schoolassistant.weeklyschedule.adapters.LessonAdapter;
import com.denzcoskun.schoolassistant.weeklyschedule.constants.LessonConstants;
import com.denzcoskun.schoolassistant.weeklyschedule.models.DayModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeeklyScheduleActivity extends AppCompatActivity {

    @BindView(R.id.tablayout)
    TabLayout tabLayout;

    @BindView(R.id.pager)
    ViewPager viewPager;

    @BindView(R.id.lesson_add_button)
    FloatingActionButton floatingActionButton;

    private TabsPagerAdapter tabsPagerAdapter = null;
    public static LessonAdapter[] lessonAdapters = new LessonAdapter[7];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().setElevation(0);

        if (HomeActivity.mainModel.dayModels == null) {
            initDays();
        }

        tabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager(), getBaseContext(), HomeActivity.mainModel.dayModels);

        for (int i = 0; i < 7; i++) {
            tabLayout.addTab(tabLayout.newTab());
        }

        viewPager.setAdapter(tabsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(WeeklyScheduleActivity.this, AddLessonActivity.class);
            intent.putExtra(LessonConstants.POSITION, tabLayout.getSelectedTabPosition());
            startActivity(intent);
        });
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
