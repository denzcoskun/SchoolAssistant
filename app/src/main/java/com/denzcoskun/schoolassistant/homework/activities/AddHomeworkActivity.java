package com.denzcoskun.schoolassistant.homework.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.denzcoskun.schoolassistant.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_homework);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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
