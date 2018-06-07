package com.denzcoskun.schoolassistant.screens.notes.activities;

import android.content.Intent;
import android.os.Bundle;

import com.denzcoskun.libdenx.activities.BaseActivity;
import com.denzcoskun.schoolassistant.R;

/**
 * Created by Denx on 5.06.2018.
 */
public class NotesActivity extends BaseActivity {

    @Override
    protected void onViewReady(Bundle saedInstanceState, Intent intent) {
        super.onViewReady(saedInstanceState, intent);
        addBackButton(); // back button
        setTitle(R.string.notes); // title
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_notes;
    }
}
