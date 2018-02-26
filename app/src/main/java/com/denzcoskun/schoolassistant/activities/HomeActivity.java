package com.denzcoskun.schoolassistant.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.denzcoskun.schoolassistant.R;
import com.denzcoskun.schoolassistant.helpers.DataHelper;
import com.denzcoskun.schoolassistant.models.MainModel;
import com.denzcoskun.schoolassistant.weeklyschedule.WeeklyScheduleActivity;

import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

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

    }
}
