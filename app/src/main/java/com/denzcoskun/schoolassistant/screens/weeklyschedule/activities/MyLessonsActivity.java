package com.denzcoskun.schoolassistant.screens.weeklyschedule.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.widget.ListView;

import com.denzcoskun.libdenx.activities.BaseActivity;
import com.denzcoskun.schoolassistant.R;
import com.denzcoskun.schoolassistant.screens.weeklyschedule.adapters.MyLessonsAdapter;
import com.denzcoskun.schoolassistant.screens.weeklyschedule.constants.LessonConstants;

import butterknife.BindView;

public class MyLessonsActivity extends BaseActivity {

    @BindView(R.id.my_lessons_list)
    ListView myLessonsList;

    @BindView(R.id.lesson_add_button)
    FloatingActionButton floatingActionButton;

    MyLessonsAdapter myLessonsAdapter;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        addBackButton();
        setTitle(R.string.my_lessons_title);

        myLessonsAdapter = new MyLessonsAdapter(MyLessonsActivity.this);
        myLessonsList.setAdapter(myLessonsAdapter);

        myLessonsList.setOnItemClickListener((parent, view, position, id) -> {
            Intent i = new Intent(MyLessonsActivity.this, EditLessonNameActivity.class);
            i.putExtra(LessonConstants.POSITION, position);
            startActivity(i);
        });

        floatingActionButton.setOnClickListener(v ->
                startActivity(new Intent(MyLessonsActivity.this, AddLessonNameActivity.class)));
    }


    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_my_lessons;
    }

    @Override
    protected void onResume() {
        super.onResume();
        myLessonsAdapter.notifyDataSetChanged();
    }

}
