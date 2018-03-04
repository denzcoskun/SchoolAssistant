package com.denzcoskun.schoolassistant.homework.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.denzcoskun.schoolassistant.R;
import com.denzcoskun.schoolassistant.activities.HomeActivity;
import com.denzcoskun.schoolassistant.helpers.DataHelper;
import com.denzcoskun.schoolassistant.homework.models.HomeworkModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddHomeworkActivity extends AppCompatActivity {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_homework);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        DataHelper dataHelper = new DataHelper(AddHomeworkActivity.this);

        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, dateOfYear, monthOfYear, dayOfMonth) -> {
            this.year = dateOfYear;
            this.month = monthOfYear;
            this.day = dayOfMonth;
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
