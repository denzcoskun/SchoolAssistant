package com.denzcoskun.schoolassistant.weeklyschedule.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.denzcoskun.schoolassistant.MainActivity;
import com.denzcoskun.schoolassistant.R;
import com.denzcoskun.schoolassistant.helpers.DataHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditLessonNameActivity extends AppCompatActivity {

    @BindView(R.id.edit_text_lesson_name)
    EditText editTextLessonName;

    @BindView(R.id.button_add_lesson)
    TextView buttonAddLesson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_lesson_name);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int position = getIntent().getIntExtra("position",0);

        DataHelper dataHelper = new DataHelper(EditLessonNameActivity.this);

        buttonAddLesson.setOnClickListener(v -> {
            MainActivity.mainModel.lessonsNames.set(position,editTextLessonName.getText().toString());
            dataHelper.setModel(MainActivity.mainModel);
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
