package com.denzcoskun.schoolassistant.homework.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.denzcoskun.schoolassistant.R;

import butterknife.ButterKnife;

public class HomeworkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_work);
        ButterKnife.bind(this);

    }
}
