package com.denzcoskun.schoolassistant.project.screens.exams.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.widget.ListView;

import com.denzcoskun.libdenx.activities.BaseActivity;
import com.denzcoskun.schoolassistant.R;
import com.denzcoskun.schoolassistant.project.activities.HomeActivity;
import com.denzcoskun.schoolassistant.project.screens.exams.adapters.ExamAdapter;
import com.denzcoskun.schoolassistant.project.screens.exams.constants.ExamConstants;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Denx on 30.05.2018.
 */
public class ExamsActivity extends BaseActivity {

    @BindView(R.id.listview_exams)
    ListView listviewExams;

    @BindView(R.id.exam_add_button)
    FloatingActionButton examAddButton;

    ExamAdapter examAdapter;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        setTitle(R.string.home_exam);
        addBackButton();
        if (HomeActivity.mainModel.examModels == null) {
            initExams();
        }

        examAdapter = new ExamAdapter(this);
        listviewExams.setAdapter(examAdapter);
        listviewExams.setClickable(false);
        listviewExams.setOnItemClickListener((parent, view, position, id) -> {
            Intent i = new Intent(this, EditExamActivity.class);
            i.putExtra(ExamConstants.LISTITEMPOSITION, position);
            startActivity(i);
        });

        examAddButton.setOnClickListener(v -> startActivity(new Intent(ExamsActivity.this, AddExamActivity.class)));
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_exams;
    }


    public void initExams() {
        HomeActivity.mainModel.examModels = new ArrayList<>();
    }

    @Override
    public void onResume() {
        super.onResume();
        examAdapter.notifyDataSetChanged();
    }
}
