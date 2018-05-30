package com.denzcoskun.schoolassistant.project.screens.exams.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.denzcoskun.schoolassistant.R;
import com.denzcoskun.schoolassistant.base.activities.BaseActivity;
import com.denzcoskun.schoolassistant.project.activities.HomeActivity;
import com.denzcoskun.schoolassistant.project.helpers.DataHelper;
import com.denzcoskun.schoolassistant.project.screens.exams.constants.ExamConstants;
import com.denzcoskun.schoolassistant.project.screens.exams.models.ExamModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

import butterknife.BindView;

/**
 * Created by Denx on 30.05.2018.
 */
public class EditExamActivity extends BaseActivity {

    @BindView(R.id.textview_add_exam_name)
    EditText textviewAddExamName;

    @BindView(R.id.textview_add_exam_subject)
    EditText textviewAddExamSubject;

    @BindView(R.id.textview_add_exam_date)
    TextView textviewAddExamDate;

    @BindView(R.id.textview_add_exam_time)
    TextView textviewAddExamTime;

    @BindView(R.id.imagebutton_choose_date)
    ImageButton imagebuttonChooseDate;

    @BindView(R.id.imagebutton_choose_time)
    ImageButton imagebuttonChooseTime;

    @BindView(R.id.button_edit_exam)
    Button buttonEditExam;

    private int year;
    private int month;
    private int day;
    private String hour;
    private String minute;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.edit_exam);

        int position = getIntent().getIntExtra(ExamConstants.LISTITEMPOSITION,0);
        init(position);

        DataHelper dataHelper = new DataHelper(EditExamActivity.this);

        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, dateOfYear, monthOfYear, dayOfMonth) -> {
            this.year = dateOfYear;
            this.month = monthOfYear;
            this.day = dayOfMonth;
            Date date = new GregorianCalendar(year, month, day).getTime();
            textviewAddExamDate.setText(dateOfString(date));
        }, year, month, day);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view, hourOfDay, minuteOfHour) -> {
            if (hourOfDay < 10) {
                hour = "0" + Integer.toString(hourOfDay);
            } else {
                hour = Integer.toString(hourOfDay);
            }
            if (minuteOfHour < 10) {
                minute = "0" + Integer.toString(minuteOfHour);
            } else {
                minute = Integer.toString(minuteOfHour);
            }
            textviewAddExamTime.setText((hour + ":" + minute));

        }, 0, 0, false);

        imagebuttonChooseDate.setOnClickListener(v -> datePickerDialog.show());
        imagebuttonChooseTime.setOnClickListener(v -> timePickerDialog.show());

        buttonEditExam.setOnClickListener(v -> {
            Date date = new Date(year - 1900, month, day);
            HomeActivity.mainModel.examModels.set(position, new ExamModel(
                    textviewAddExamName.getText().toString(),
                    textviewAddExamSubject.getText().toString(),
                    dateOfString(date),
                    textviewAddExamTime.getText().toString()));
            dataHelper.setModel(HomeActivity.mainModel);
            finish();
        });
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_edit_exam;
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

    public static String dateOfString(Date date) {
        return new SimpleDateFormat("dd MMMM EEEE yyyy").format(date);
    }

    public void init(int position){
        textviewAddExamName.setText(HomeActivity.mainModel.examModels.get(position).getExamName());
        textviewAddExamSubject.setText(HomeActivity.mainModel.examModels.get(position).getExamSubject());
        textviewAddExamDate.setText(HomeActivity.mainModel.examModels.get(position).getExamDate());
        textviewAddExamTime.setText(HomeActivity.mainModel.examModels.get(position).getExamTime());
    }
}
