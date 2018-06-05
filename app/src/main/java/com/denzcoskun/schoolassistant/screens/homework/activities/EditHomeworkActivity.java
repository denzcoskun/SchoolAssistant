package com.denzcoskun.schoolassistant.screens.homework.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.denzcoskun.libdenx.activities.BaseActivity;
import com.denzcoskun.schoolassistant.R;
import com.denzcoskun.schoolassistant.activities.HomeActivity;
import com.denzcoskun.schoolassistant.helpers.DataHelper;
import com.denzcoskun.schoolassistant.screens.homework.constants.HomeworkConstants;
import com.denzcoskun.schoolassistant.screens.homework.models.HomeworkModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import butterknife.BindView;

public class EditHomeworkActivity extends BaseActivity {

    @BindView(R.id.textview_add_homework_name)
    EditText textviewAddHomeworkName;

    @BindView(R.id.textview_add_homework_subject)
    EditText textviewAddHomeworkSubject;

    @BindView(R.id.textview_add_homework_date)
    TextView textviewAddHomeworkDate;

    @BindView(R.id.imagebutton_choose_date)
    ImageButton imagebuttonChooseDate;

    @BindView(R.id.button_edit_homework)
    Button buttonAddHomework;

    private int year;
    private int month;
    private int day;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        addBackButton();
        setTitle(R.string.edit_homework);
        DataHelper dataHelper = new DataHelper(EditHomeworkActivity.this);

        int position = getIntent().getIntExtra(HomeworkConstants.LISTITEMPOSITION, 0);
        init(position);

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
            HomeActivity.mainModel.homeworkModels.set(position, new HomeworkModel(
                    textviewAddHomeworkName.getText().toString(),
                    textviewAddHomeworkSubject.getText().toString(),
                    dateOfString(date)));
            dataHelper.setModel(HomeActivity.mainModel);
            finish();
        });
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_edit_homework;
    }

    public void init(int position) {
        textviewAddHomeworkName.setText(HomeActivity.mainModel.homeworkModels.get(position).getHomeworkName());
        textviewAddHomeworkSubject.setText(HomeActivity.mainModel.homeworkModels.get(position).getHomeworkSubject());
        textviewAddHomeworkDate.setText(HomeActivity.mainModel.homeworkModels.get(position).getHomeworkDate());
    }

    public static String dateOfString(Date date) {
        return new SimpleDateFormat("dd MMMM EEEE yyyy").format(date);
    }
}
