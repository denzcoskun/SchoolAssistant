package com.denzcoskun.schoolassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.denzcoskun.schoolassistant.adapters.TabsPagerAdapter;
import com.denzcoskun.schoolassistant.weeklyschedule.activities.AddLessonActivity;
import com.denzcoskun.schoolassistant.weeklyschedule.adapters.LessonAdapter;
import com.denzcoskun.schoolassistant.weeklyschedule.models.DayModel;

import java.util.ArrayList;
import java.util.List;

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
    public static List<DayModel> dayModels;
    public static LessonAdapter[] lessonAdapters = new LessonAdapter[7];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initDays();

        tabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager(), getBaseContext(), dayModels);

        for (int i = 0; i < 7; i++) {
            tabLayout.addTab(tabLayout.newTab());
        }

        viewPager.setAdapter(tabsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        floatingActionButton.setOnClickListener(v ->{
            Intent intent = new Intent(MainActivity.this, AddLessonActivity.class);
            intent.putExtra("position",tabLayout.getSelectedTabPosition());
            startActivity(intent);
        });
    }

    public void initDays() {
        dayModels = new ArrayList<>();
        dayModels.add(new DayModel(getString(R.string.monday)));
        dayModels.add(new DayModel(getString(R.string.tuesday)));
        dayModels.add(new DayModel(getString(R.string.wednesday)));
        dayModels.add(new DayModel(getString(R.string.thursday)));
        dayModels.add(new DayModel(getString(R.string.friday)));
        dayModels.add(new DayModel(getString(R.string.saturday)));
        dayModels.add(new DayModel(getString(R.string.sunday)));
    }
}
