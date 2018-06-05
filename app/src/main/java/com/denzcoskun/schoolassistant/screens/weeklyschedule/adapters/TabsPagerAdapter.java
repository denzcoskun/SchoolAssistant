package com.denzcoskun.schoolassistant.screens.weeklyschedule.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.denzcoskun.schoolassistant.screens.weeklyschedule.fragments.DayFragment;
import com.denzcoskun.schoolassistant.screens.weeklyschedule.models.DayModel;

import java.util.List;


/**
 * Created by Denx on 11.02.2018.
 */

public class TabsPagerAdapter extends FragmentPagerAdapter {
    Context context = null;
    List<DayModel> dayModels;
    public TabsPagerAdapter(FragmentManager fragmentManager, Context context , List<DayModel> dayModels) {
        super(fragmentManager);
        this.context = context;
        this.dayModels = dayModels;
    }

    @Override
    public Fragment getItem(int position) {
        DayFragment dayFragment = new DayFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position",position);
        dayFragment.setArguments(bundle);
        return dayFragment;
    }

    @Override
    public int getCount() {
        return dayModels.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return dayModels.get(position).getName();
    }

}