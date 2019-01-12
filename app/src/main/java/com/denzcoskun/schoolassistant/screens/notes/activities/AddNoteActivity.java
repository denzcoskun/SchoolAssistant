package com.denzcoskun.schoolassistant.screens.notes.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.denzcoskun.libdenx.activities.BaseActivity;
import com.denzcoskun.schoolassistant.R;
import com.denzcoskun.schoolassistant.activities.HomeActivity;
import com.denzcoskun.schoolassistant.helpers.DataHelper;
import com.denzcoskun.schoolassistant.screens.notes.models.NoteModel;

import butterknife.BindView;

public class AddNoteActivity extends BaseActivity {

    @BindView(R.id.textview_add_note_name)
    EditText textViewNoteName;
    @BindView(R.id.textview_add_note_subject)
    EditText textViewNoteSubject;
    @BindView(R.id.button_add_note)
    Button buttonAddNote;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent){
        super.onViewReady(savedInstanceState, intent);
        addBackButton();
        setTitle(R.string.add_note);

        DataHelper dataHelper = new DataHelper(AddNoteActivity.this);

        buttonAddNote.setOnClickListener(v -> {
            HomeActivity.mainModel.noteModels.add(new NoteModel(textViewNoteName.getText().toString(),
                    textViewNoteSubject.getText().toString()));
            dataHelper.setModel(HomeActivity.mainModel);
            finish();
        });
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_add_note;
    }
}
