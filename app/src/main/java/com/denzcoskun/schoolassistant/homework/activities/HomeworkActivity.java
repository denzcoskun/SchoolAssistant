package com.denzcoskun.schoolassistant.homework.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.denzcoskun.schoolassistant.R;
import com.denzcoskun.schoolassistant.activities.HomeActivity;
import com.denzcoskun.schoolassistant.homework.adapters.HomeworkAdapter;
import com.denzcoskun.schoolassistant.weeklyschedule.models.DayModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeworkActivity extends AppCompatActivity {

    @BindView(R.id.listview_homeworks)
    ListView listviewHomeworks;

    @BindView(R.id.homework_add_button)
    FloatingActionButton homeworkAddButton;

    HomeworkAdapter homeworkAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_work);
        ButterKnife.bind(this);

        if (HomeActivity.mainModel.homeworkModels == null){
            initHomeworks();
        }

        homeworkAdapter = new HomeworkAdapter(this);
        listviewHomeworks.setAdapter(homeworkAdapter);
        listviewHomeworks.setClickable(false);
        listviewHomeworks.setOnItemClickListener((parent, view, position, id) -> {

        });

        homeworkAddButton.setOnClickListener(v -> startActivity(new Intent(HomeworkActivity.this,AddHomeworkActivity.class)));
    }
    public void initHomeworks() {
        HomeActivity.mainModel.homeworkModels = new ArrayList<>();
    }

    @Override
    public void onResume(){
        super.onResume();
        homeworkAdapter.notifyDataSetChanged();
    }
}
