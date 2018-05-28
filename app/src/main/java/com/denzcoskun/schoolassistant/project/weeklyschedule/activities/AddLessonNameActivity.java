package com.denzcoskun.schoolassistant.project.weeklyschedule.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.denzcoskun.schoolassistant.R;
import com.denzcoskun.schoolassistant.base.activities.BaseActivity;
import com.denzcoskun.schoolassistant.project.activities.HomeActivity;
import com.denzcoskun.schoolassistant.project.helpers.DataHelper;

import java.util.Objects;

import butterknife.BindView;

public class AddLessonNameActivity extends BaseActivity {

    @BindView(R.id.edit_text_lesson_name)
    EditText editTextLessonName;

    @BindView(R.id.button_add_lesson)
    TextView buttonAddLesson;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.add_lesson);

        DataHelper dataHelper = new DataHelper(AddLessonNameActivity.this);

        buttonAddLesson.setOnClickListener(v -> {
            HomeActivity.mainModel.lessonsNames.add(editTextLessonName.getText().toString());
            dataHelper.setModel(HomeActivity.mainModel);
            finish();
        });
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_add_lesson_name;
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
