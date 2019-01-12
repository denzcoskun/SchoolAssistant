package com.denzcoskun.schoolassistant.screens.absence.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.widget.ListView;

import com.denzcoskun.libdenx.activities.BaseActivity;
import com.denzcoskun.schoolassistant.R;
import com.denzcoskun.schoolassistant.activities.HomeActivity;
import com.denzcoskun.schoolassistant.screens.absence.adapters.AbsenceAdapter;
import com.denzcoskun.schoolassistant.screens.absence.constants.AbsenceConstants;

import java.util.ArrayList;

import butterknife.BindView;

public class AbsencesActivity extends BaseActivity {

    @BindView(R.id.listview_absences)
    ListView listviewAbsences;

    @BindView(R.id.absence_add_button)
    FloatingActionButton absenceAddButton;

    AbsenceAdapter absenceAdapter;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        setTitle(R.string.absences);
        addBackButton();
        if (HomeActivity.mainModel.absenceModels == null) {
            initAbsences();
        }

        absenceAdapter = new AbsenceAdapter(this);
        listviewAbsences.setAdapter(absenceAdapter);
        listviewAbsences.setClickable(false);
        listviewAbsences.setOnItemClickListener((parent, view, position, id) -> {
            Intent i = new Intent(this, EditAbsenceActivity.class);
            i.putExtra(AbsenceConstants.LISTITEMPOSITION, position);
            startActivity(i);
        });

        absenceAddButton.setOnClickListener(v -> startActivity(new Intent(AbsencesActivity.this, AddAbsenceActivity.class)));
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_absence;
    }

    public void initAbsences() {
        HomeActivity.mainModel.absenceModels = new ArrayList<>();
    }

    @Override
    public void onResume() {
        super.onResume();
        absenceAdapter.notifyDataSetChanged();
    }
}
