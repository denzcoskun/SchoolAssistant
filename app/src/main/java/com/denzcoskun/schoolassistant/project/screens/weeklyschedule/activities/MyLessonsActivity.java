package com.denzcoskun.schoolassistant.project.screens.weeklyschedule.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.denzcoskun.schoolassistant.R;
import com.denzcoskun.schoolassistant.base.activities.BaseActivity;
import com.denzcoskun.schoolassistant.project.screens.weeklyschedule.adapters.MyLessonsAdapter;
import com.denzcoskun.schoolassistant.project.screens.weeklyschedule.constants.LessonConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyLessonsActivity extends BaseActivity {

    @BindView(R.id.my_lessons_list)
    ListView myLessonsList;

    @BindView(R.id.lesson_add_button)
    FloatingActionButton floatingActionButton;

    MyLessonsAdapter myLessonsAdapter;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.my_lessons_title);

        myLessonsAdapter =new MyLessonsAdapter(MyLessonsActivity.this);
        myLessonsList.setAdapter(myLessonsAdapter);

        myLessonsList.setOnItemClickListener((parent, view, position, id) -> {
            Intent i = new Intent(MyLessonsActivity.this, EditLessonNameActivity.class);
            i.putExtra(LessonConstants.POSITION, position);
            startActivity(i);
        });

        floatingActionButton.setOnClickListener(v ->
                startActivity(new Intent(MyLessonsActivity.this,AddLessonNameActivity.class)));
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

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
