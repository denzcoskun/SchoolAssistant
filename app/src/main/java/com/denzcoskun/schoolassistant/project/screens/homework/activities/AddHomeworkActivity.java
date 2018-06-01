package com.denzcoskun.schoolassistant.project.screens.homework.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.denzcoskun.libdenx.activities.BaseActivity;
import com.denzcoskun.schoolassistant.R;
import com.denzcoskun.schoolassistant.project.activities.HomeActivity;
import com.denzcoskun.schoolassistant.project.helpers.DataHelper;
import com.denzcoskun.schoolassistant.project.screens.homework.models.HomeworkModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

import butterknife.BindView;

public class AddHomeworkActivity extends BaseActivity {

    @BindView(R.id.textview_add_homework_name)
    EditText textviewAddHomeworkName;

    @BindView(R.id.textview_add_homework_subject)
    EditText textviewAddHomeworkSubject;

    @BindView(R.id.textview_add_homework_date)
    TextView textviewAddHomeworkDate;

    @BindView(R.id.imagebutton_choose_date)
    ImageButton imagebuttonChooseDate;

    @BindView(R.id.button_add_homework)
    Button buttonAddHomework;

    private int year;
    private int month;
    private int day;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.add_homework);
        DataHelper dataHelper = new DataHelper(AddHomeworkActivity.this);

        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, dateOfYear, monthOfYear, dayOfMonth) -> {
            this.year = dateOfYear;
            this.month = monthOfYear;
            this.day = dayOfMonth;
            Date date = new GregorianCalendar(year, month, day).getTime();
            textviewAddHomeworkDate.setText(dateOfString(date));
        }, year, month, day);

        imagebuttonChooseDate.setOnClickListener(v -> datePickerDialog.show());

        buttonAddHomework.setOnClickListener(v -> {
            Date date = new Date(year - 1900, month, day);
            HomeActivity.mainModel.homeworkModels.add(new HomeworkModel(
                    textviewAddHomeworkName.getText().toString(),
                    textviewAddHomeworkSubject.getText().toString(),
                    dateOfString(date)));
            dataHelper.setModel(HomeActivity.mainModel);
            finish();
        });
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_add_homework;
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
}
