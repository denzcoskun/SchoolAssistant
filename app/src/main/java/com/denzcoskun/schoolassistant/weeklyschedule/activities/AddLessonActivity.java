package com.denzcoskun.schoolassistant.weeklyschedule.activities;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.denzcoskun.schoolassistant.MainActivity;
import com.denzcoskun.schoolassistant.R;
import com.denzcoskun.schoolassistant.helpers.DataHelper;
import com.denzcoskun.schoolassistant.weeklyschedule.models.LessonModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddLessonActivity extends AppCompatActivity {

    @BindView(R.id.spinner_lessons)
    Spinner spinnerLessons;

    @BindView(R.id.image_button_edit_lesson)
    ImageButton imageButtonEditLesson;

    @BindView(R.id.edittext_classroom)
    EditText edittextClassroom;

    @BindView(R.id.text_view_start_time)
    TextView textViewStartTime;

    @BindView(R.id.image_button_edit_start_time)
    ImageButton imageButtonEditStartTime;

    @BindView(R.id.text_view_finish_time)
    TextView textViewFinishTime;

    @BindView(R.id.image_button_edit_finish_time)
    ImageButton imageButtonEditFinishTime;

    @BindView(R.id.button_add_lesson)
    Button buttonAddLesson;

    private String startHour;
    private String startMinute;
    private String finishHour;
    private String finishMinute;
    private boolean isStart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lesson);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DataHelper dataHelper = new DataHelper(AddLessonActivity.this);
        int position = getIntent().getIntExtra("position",0);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, hourOfDay, minute) -> {
            if (isStart) {
                startHour = Integer.toString(hourOfDay);
                startMinute = Integer.toString(minute);
                textViewStartTime.setText((startHour+":"+startMinute));
            } else {
                finishHour = Integer.toString(hourOfDay);
                finishMinute = Integer.toString(minute);
                textViewFinishTime.setText((finishHour+":"+finishMinute));
            }
        }, 0, 0, false);

        imageButtonEditStartTime.setOnClickListener(v -> {
            timePickerDialog.show();
            isStart = true;
        });

        imageButtonEditFinishTime.setOnClickListener(v -> {
            timePickerDialog.show();
            isStart = false;
        });

        buttonAddLesson.setOnClickListener(v -> {
            MainActivity.mainModel.dayModels.get(position).getLessons()
                .add(new LessonModel(spinnerLessons.getSelectedItem().toString(),
                        spinnerLessons.getSelectedItemPosition(),
                        edittextClassroom.getText().toString(),
                        startHour+":"+startMinute,
                        finishHour+":"+finishMinute));
            MainActivity.lessonAdapters[position].notifyDataSetChanged();
            dataHelper.setModel(MainActivity.mainModel);
            finish();
        });

        imageButtonEditLesson.setOnClickListener(v ->startActivity(new Intent( AddLessonActivity.this,MyLessonsActivity.class)));

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, MainActivity.mainModel.lessonsNames);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLessons.setAdapter(dataAdapter);
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
