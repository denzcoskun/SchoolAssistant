package com.denzcoskun.schoolassistant.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.denzcoskun.libdenx.activities.BaseActivity;
import com.denzcoskun.schoolassistant.R;
import com.denzcoskun.schoolassistant.adapters.RecyclerviewAdapter;
import com.denzcoskun.schoolassistant.helpers.DataHelper;
import com.denzcoskun.schoolassistant.models.ItemModel;
import com.denzcoskun.schoolassistant.models.MainModel;
import com.denzcoskun.schoolassistant.screens.absence.activities.AbsencesActivity;
import com.denzcoskun.schoolassistant.screens.exams.activities.ExamsActivity;
import com.denzcoskun.schoolassistant.screens.homework.activities.HomeworkActivity;
import com.denzcoskun.schoolassistant.screens.notes.activities.NotesActivity;
import com.denzcoskun.schoolassistant.screens.weeklyschedule.activities.WeeklyScheduleActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeActivity extends BaseActivity implements RecyclerviewAdapter.ItemClickListener {

    @BindView(R.id.recyclerview_main_items)
    RecyclerView recyclerView;

    public static MainModel mainModel = new MainModel();

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        DataHelper dataHelper = new DataHelper(HomeActivity.this);

        if (dataHelper.getModel() != null) {
            mainModel = dataHelper.getModel();
        }
        List<ItemModel> items = new ArrayList<>();
        items.add(new ItemModel(R.string.home_calendar, R.drawable.calendar, WeeklyScheduleActivity.class));
        items.add(new ItemModel(R.string.home_exam, R.drawable.exam, ExamsActivity.class));
        items.add(new ItemModel(R.string.home_homework, R.drawable.homework, HomeworkActivity.class));
        items.add(new ItemModel(R.string.notes, R.drawable.homework, NotesActivity.class));
        items.add(new ItemModel(R.string.absences, R.drawable.homework, AbsencesActivity.class));

        int numberOfColumns = 3;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        RecyclerviewAdapter adapter = new RecyclerviewAdapter(this, items);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        recyclerView.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, ExamsActivity.class)));


    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_home;
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
