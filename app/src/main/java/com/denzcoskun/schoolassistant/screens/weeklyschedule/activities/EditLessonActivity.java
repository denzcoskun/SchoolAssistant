package com.denzcoskun.schoolassistant.screens.weeklyschedule.activities;

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
import com.denzcoskun.schoolassistant.activities.HomeActivity;
import com.denzcoskun.schoolassistant.helpers.DataHelper;
import com.denzcoskun.schoolassistant.screens.weeklyschedule.constants.LessonConstants;
import com.denzcoskun.schoolassistant.screens.weeklyschedule.models.LessonModel;

import butterknife.BindView;

public class EditLessonActivity extends BaseActivity {

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
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        addBackButton();
        setTitle(R.string.edit_course);

        DataHelper dataHelper = new DataHelper(EditLessonActivity.this);
        int position = getIntent().getIntExtra(LessonConstants.POSITION, 0);
        int listItemPosition = getIntent().getIntExtra(LessonConstants.LISTITEMPOSITION, 0);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, HomeActivity.mainModel.lessonsNames);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLessons.setAdapter(dataAdapter);

        initViews(position, listItemPosition);

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
                    .set(listItemPosition, new LessonModel(spinnerLessons.getSelectedItem().toString(),
                            spinnerLessons.getSelectedItemPosition(),
                            edittextClassroom.getText().toString(),
                            startHour + ":" + startMinute,
                            finishHour + ":" + finishMinute));
            WeeklyScheduleActivity.lessonAdapters[position].notifyDataSetChanged();
            dataHelper.setModel(HomeActivity.mainModel);
            finish();
        });

        imageButtonEditLesson.setOnClickListener(v -> startActivity(new Intent(EditLessonActivity.this, MyLessonsActivity.class)));
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_edit_lesson;
    }

    public void initViews(int position, int listItemPosition) {
        LessonModel lessonModel = HomeActivity.mainModel.dayModels.get(position).getLessons().get(listItemPosition);

        startHour = lessonModel.getStartTime().split(":")[0];
        startMinute = lessonModel.getStartTime().split(":")[1];
        finishHour = lessonModel.getFinishTime().split(":")[0];
        finishMinute = lessonModel.getFinishTime().split(":")[1];

        textViewStartTime.setText((lessonModel.getStartTime().split(":")[0] + ":"
                + lessonModel.getStartTime().split(":")[1]));
        textViewFinishTime.setText((lessonModel.getFinishTime().split(":")[0] + ":"
                + lessonModel.getFinishTime().split(":")[1]));
        edittextClassroom.setText(lessonModel.getClassroom());
        spinnerLessons.setSelection(lessonModel.getPosition());
    }
}
