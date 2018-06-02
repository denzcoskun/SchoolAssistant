package com.denzcoskun.schoolassistant.project.screens.homework.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.widget.ListView;

import com.denzcoskun.libdenx.activities.BaseActivity;
import com.denzcoskun.schoolassistant.R;
import com.denzcoskun.schoolassistant.project.activities.HomeActivity;
import com.denzcoskun.schoolassistant.project.screens.homework.adapters.HomeworkAdapter;
import com.denzcoskun.schoolassistant.project.screens.homework.constants.HomeworkConstants;

import java.util.ArrayList;

import butterknife.BindView;

public class HomeworkActivity extends BaseActivity {

    @BindView(R.id.listview_homeworks)
    ListView listviewHomeworks;

    @BindView(R.id.homework_add_button)
    FloatingActionButton homeworkAddButton;

    HomeworkAdapter homeworkAdapter;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        setTitle(R.string.home_homework);
        addBackButton();
        if (HomeActivity.mainModel.homeworkModels == null) {
            initHomeworks();
        }

        homeworkAdapter = new HomeworkAdapter(this);
        listviewHomeworks.setAdapter(homeworkAdapter);
        listviewHomeworks.setClickable(false);
        listviewHomeworks.setOnItemClickListener((parent, view, position, id) -> {
            Intent i = new Intent(this, EditHomeworkActivity.class);
            i.putExtra(HomeworkConstants.LISTITEMPOSITION, position);
            startActivity(i);
        });

        homeworkAddButton.setOnClickListener(v -> startActivity(new Intent(HomeworkActivity.this, AddHomeworkActivity.class)));
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_home_work;
    }

    public void initHomeworks() {
        HomeActivity.mainModel.homeworkModels = new ArrayList<>();
    }

    @Override
    public void onResume() {
        super.onResume();
        homeworkAdapter.notifyDataSetChanged();
    }
}
