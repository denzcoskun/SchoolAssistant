package com.denzcoskun.schoolassistant.weeklyschedule.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.denzcoskun.schoolassistant.MainActivity;
import com.denzcoskun.schoolassistant.R;
import com.denzcoskun.schoolassistant.weeklyschedule.adapters.MyLessonsAdapter;
import com.denzcoskun.schoolassistant.weeklyschedule.constants.LessonConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyLessonsActivity extends AppCompatActivity {

    @BindView(R.id.my_lessons_list)
    ListView myLessonsList;

    @BindView(R.id.lesson_add_button)
    FloatingActionButton floatingActionButton;

    MyLessonsAdapter myLessonsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_lessons);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.my_lessons_title);

        myLessonsAdapter =new MyLessonsAdapter(MyLessonsActivity.this);
        myLessonsList.setAdapter(myLessonsAdapter);

        myLessonsList.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MyLessonsActivity.this, EditLessonNameActivity.class);
            intent.putExtra(LessonConstants.POSITION, position);
            startActivity(intent);
        });

        floatingActionButton.setOnClickListener(v ->
                startActivity(new Intent(MyLessonsActivity.this,AddLessonNameActivity.class)));
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
