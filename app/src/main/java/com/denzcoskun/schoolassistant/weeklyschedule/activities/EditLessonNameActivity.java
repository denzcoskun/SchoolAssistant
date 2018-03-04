package com.denzcoskun.schoolassistant.weeklyschedule.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.denzcoskun.schoolassistant.activities.HomeActivity;
import com.denzcoskun.schoolassistant.R;
import com.denzcoskun.schoolassistant.helpers.DataHelper;
import com.denzcoskun.schoolassistant.weeklyschedule.constants.LessonConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditLessonNameActivity extends AppCompatActivity {

    @BindView(R.id.edit_text_lesson_name)
    EditText editTextLessonName;

    @BindView(R.id.button_edit_lesson)
    TextView buttonEditLesson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_lesson_name);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
