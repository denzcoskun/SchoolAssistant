package com.denzcoskun.schoolassistant.project.screens.weeklyschedule.activities;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.denzcoskun.libdenx.activities.BaseActivity;
import com.denzcoskun.schoolassistant.R;
import com.denzcoskun.schoolassistant.project.activities.HomeActivity;
import com.denzcoskun.schoolassistant.project.helpers.DataHelper;
import com.denzcoskun.schoolassistant.project.screens.weeklyschedule.constants.LessonConstants;
import com.denzcoskun.schoolassistant.project.screens.weeklyschedule.models.LessonModel;

import butterknife.BindView;

public class AddLessonActivity extends BaseActivity {

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

    private String startHour = "00";
    private String startMinute = "00";
    private String finishHour = "00";
    private String finishMinute = "00";
    private boolean isStart;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        addBackButton();
        setTitle(R.string.add_course);

        DataHelper dataHelper = new DataHelper(AddLessonActivity.this);
        int position = getIntent().getIntExtra(LessonConstants.POSITION, 0);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, hourOfDay, minute) -> {
            if (isStart) {
                if (hourOfDay < 10) {
                    startHour = "0" + Integer.toString(hourOfDay);
                } else {
                    startHour = Integer.toString(hourOfDay);
                }
                if (minute < 10) {
                    startMinute = "0" + Integer.toString(minute);
                } else {
                    startMinute = Integer.toString(minute);
                }
                textViewStartTime.setText((startHour + ":" + startMinute));
            } else {
                if (hourOfDay < 10) {
                    finishHour = "0" + Integer.toString(hourOfDay);
                } else {
                    finishHour = Integer.toString(hourOfDay);
                }
                if (minute < 10) {
                    finishMinute = "0" + Integer.toString(minute);
                } else {
                    finishMinute = Integer.toString(minute);
                }
                textViewFinishTime.setText((finishHour + ":" + finishMinute));
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

            HomeActivity.mainModel.dayModels.get(position).getLessons()
                    .add(new LessonModel(spinnerLessons.getSelectedItem().toString(),
                            spinnerLessons.getSelectedItemPosition(),
                            edittextClassroom.getText().toString(),
                            startHour + ":" + startMinute,
                            finishHour + ":" + finishMinute));
            WeeklyScheduleActivity.lessonAdapters[position].notifyDataSetChanged();
            dataHelper.setModel(HomeActivity.mainModel);
            finish();
        });

        imageButtonEditLesson.setOnClickListener(v -> startActivity(new Intent(AddLessonActivity.this, MyLessonsActivity.class)));

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, HomeActivity.mainModel.lessonsNames);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLessons.setAdapter(dataAdapter);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_add_lesson;
    }
}
