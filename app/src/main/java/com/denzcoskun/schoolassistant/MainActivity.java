package com.denzcoskun.schoolassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.denzcoskun.schoolassistant.adapters.TabsPagerAdapter;
import com.denzcoskun.schoolassistant.helpers.DataHelper;
import com.denzcoskun.schoolassistant.models.MainModel;
import com.denzcoskun.schoolassistant.weeklyschedule.activities.AddLessonActivity;
import com.denzcoskun.schoolassistant.weeklyschedule.adapters.LessonAdapter;
import com.denzcoskun.schoolassistant.weeklyschedule.models.DayModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tablayout)
    TabLayout tabLayout;

    @BindView(R.id.pager)
    ViewPager viewPager;

    @BindView(R.id.lesson_add_button)
    FloatingActionButton floatingActionButton;

    private TabsPagerAdapter tabsPagerAdapter = null;
    public static MainModel mainModel = new MainModel();
    public static LessonAdapter[] lessonAdapters = new LessonAdapter[7];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().setElevation(0);
        DataHelper dataHelper = new DataHelper(MainActivity.this);

        if ((MainModel) dataHelper.getModel() == null) {
            initDays();
        }else {
            mainModel = (MainModel) dataHelper.getModel();
        }
        tabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager(), getBaseContext(), mainModel.dayModels);

        for (int i = 0; i < 7; i++) {
            tabLayout.addTab(tabLayout.newTab());
        }

        viewPager.setAdapter(tabsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddLessonActivity.class);
            intent.putExtra("position", tabLayout.getSelectedTabPosition());
            startActivity(intent);
        });
    }

    public void initDays() {
        mainModel.dayModels = new ArrayList<>();
        mainModel.dayModels.add(new DayModel(getString(R.string.monday)));
        mainModel.dayModels.add(new DayModel(getString(R.string.tuesday)));
        mainModel.dayModels.add(new DayModel(getString(R.string.wednesday)));
        mainModel.dayModels.add(new DayModel(getString(R.string.thursday)));
        mainModel.dayModels.add(new DayModel(getString(R.string.friday)));
        mainModel.dayModels.add(new DayModel(getString(R.string.saturday)));
        mainModel.dayModels.add(new DayModel(getString(R.string.sunday)));

        mainModel.lessonsNames = new ArrayList<>();
        mainModel.lessonsNames.add(getString(R.string.science));
        mainModel.lessonsNames.add(getString(R.string.mathematics));
        mainModel.lessonsNames.add(getString(R.string.history));
    }
}
