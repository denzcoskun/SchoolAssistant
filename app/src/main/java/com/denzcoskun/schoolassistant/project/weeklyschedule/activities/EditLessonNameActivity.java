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
import com.denzcoskun.schoolassistant.project.weeklyschedule.constants.LessonConstants;

import java.util.Objects;

import butterknife.BindView;

public class EditLessonNameActivity extends BaseActivity {

    @BindView(R.id.edit_text_lesson_name)
    EditText editTextLessonName;

    @BindView(R.id.button_edit_lesson)
    TextView buttonEditLesson;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.edit_lesson);

        int position = getIntent().getIntExtra(LessonConstants.POSITION,0);

        init(position);

        DataHelper dataHelper = new DataHelper(EditLessonNameActivity.this);

        buttonEditLesson.setOnClickListener(v -> {
            HomeActivity.mainModel.lessonsNames.set(position,editTextLessonName.getText().toString());
            dataHelper.setModel(HomeActivity.mainModel);
            finish();
        });
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_edit_lesson_name;
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

    public void init(int position){
        editTextLessonName.setText(HomeActivity.mainModel.lessonsNames.get(position));
    }
}
