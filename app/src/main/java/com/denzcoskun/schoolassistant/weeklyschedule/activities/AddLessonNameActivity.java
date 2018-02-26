package com.denzcoskun.schoolassistant.weeklyschedule.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.denzcoskun.schoolassistant.R;
import com.denzcoskun.schoolassistant.activities.HomeActivity;
import com.denzcoskun.schoolassistant.helpers.DataHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddLessonNameActivity extends AppCompatActivity {

    @BindView(R.id.edit_text_lesson_name)
    EditText editTextLessonName;

    @BindView(R.id.button_add_lesson)
    TextView buttonAddLesson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lesson_name);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.add_lesson);

        DataHelper dataHelper = new DataHelper(AddLessonNameActivity.this);

        buttonAddLesson.setOnClickListener(v -> {
            HomeActivity.mainModel.lessonsNames.add(editTextLessonName.getText().toString());
            dataHelper.setModel(HomeActivity.mainModel);
            finish();
        });
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
