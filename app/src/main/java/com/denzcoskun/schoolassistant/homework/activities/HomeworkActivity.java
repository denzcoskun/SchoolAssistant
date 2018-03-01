package com.denzcoskun.schoolassistant.homework.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.denzcoskun.schoolassistant.R;
import com.denzcoskun.schoolassistant.activities.HomeActivity;
import com.denzcoskun.schoolassistant.homework.adapters.HomeworkAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeworkActivity extends AppCompatActivity {

    @BindView(R.id.listview_homeworks)
    ListView listviewHomeworks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_work);
        ButterKnife.bind(this);

        HomeworkAdapter homeworkAdapter = new HomeworkAdapter(this);
        listviewHomeworks.setAdapter(homeworkAdapter);
        listviewHomeworks.setClickable(false);
        listviewHomeworks.setOnClickListener(v ->{

        });

    }
}
