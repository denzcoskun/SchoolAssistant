package com.denzcoskun.schoolassistant;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.denzcoskun.schoolassistant.adapters.TabsPagerAdapter;
import com.denzcoskun.schoolassistant.weeklyschedule.models.DayModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout = null;
    private TabsPagerAdapter tabsPagerAdapter =null;
    private List<DayModel> dayModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDays();
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        tabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager(),getBaseContext(),dayModels);

        for (int i = 0 ; i<7 ; i++){
            tabLayout.addTab(tabLayout.newTab());
        }

        pager.setAdapter(tabsPagerAdapter);
        tabLayout.setupWithViewPager(pager);
    }

    public void initDays(){
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
