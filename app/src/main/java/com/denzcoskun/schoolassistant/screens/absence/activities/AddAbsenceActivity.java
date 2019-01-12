package com.denzcoskun.schoolassistant.screens.absence.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.denzcoskun.libdenx.activities.BaseActivity;
import com.denzcoskun.schoolassistant.R;
import com.denzcoskun.schoolassistant.activities.HomeActivity;
import com.denzcoskun.schoolassistant.helpers.DataHelper;
import com.denzcoskun.schoolassistant.screens.absence.models.AbsenceModel;
import com.denzcoskun.schoolassistant.screens.weeklyschedule.activities.MyLessonsActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import butterknife.BindView;

public class AddAbsenceActivity extends BaseActivity {

    @BindView(R.id.spinner_absence_name)
    Spinner spinnerAbsenceName;
    @BindView(R.id.image_button_edit_lesson)
    ImageButton imageButtonEditLesson;
    @BindView(R.id.image_button_date)
    ImageButton imageButtonDate;
    @BindView(R.id.button_add_absence)
    Button buttonAddAbsence;
    @BindView(R.id.radiobutton_all_day)
    RadioButton radiobuttonAllDay;
    @BindView(R.id.radiobutton_half_day)
    RadioButton radiobuttonHalfDay;
    @BindView(R.id.radiobutton_excused)
    RadioButton radiobuttonExcused;
    @BindView(R.id.radiobutton_unexcused)
    RadioButton radiobuttonUnexcused;
    @BindView(R.id.text_view_date)
    TextView textViewDate;


    private int year;
    private int month;
    private int day;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        setTitle(R.string.add_absence);
        addBackButton();

        DataHelper dataHelper = new DataHelper(AddAbsenceActivity.this);

        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, dateOfYear, monthOfYear, dayOfMonth) -> {
            this.year = dateOfYear;
            this.month = monthOfYear;
            this.day = dayOfMonth;
            Date date = new GregorianCalendar(year, month, day).getTime();
            textViewDate.setText(dateOfString(date));
        }, year, month, day);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, HomeActivity.mainModel.lessonsNames);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAbsenceName.setAdapter(dataAdapter);

        imageButtonDate.setOnClickListener(v -> datePickerDialog.show());

        imageButtonEditLesson.setOnClickListener(v -> startActivity(new Intent(AddAbsenceActivity.this, MyLessonsActivity.class)));

        buttonAddAbsence.setOnClickListener(v -> {
            HomeActivity.mainModel.absenceModels.add(new AbsenceModel(
                    HomeActivity.mainModel.lessonsNames.get(spinnerAbsenceName.getSelectedItemPosition()),
                    getStatus(),
                    getKind(),
                    textViewDate.getText().toString()));
            dataHelper.setMainModel();
            finish();
        });
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_add_absence;
    }

    public static String dateOfString(Date date) {
        return new SimpleDateFormat("dd MMMM EEEE yyyy").format(date);
    }

    public boolean getStatus() {
        return radiobuttonAllDay.isChecked();
    }

    public boolean getKind() {
        return radiobuttonExcused.isChecked();
    }
}
