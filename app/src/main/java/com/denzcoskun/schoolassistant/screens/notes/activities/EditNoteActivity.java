package com.denzcoskun.schoolassistant.screens.notes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.denzcoskun.libdenx.activities.BaseActivity;
import com.denzcoskun.schoolassistant.R;
import com.denzcoskun.schoolassistant.activities.HomeActivity;
import com.denzcoskun.schoolassistant.helpers.DataHelper;
import com.denzcoskun.schoolassistant.screens.notes.constants.NoteConstant;
import com.denzcoskun.schoolassistant.screens.notes.models.NoteModel;

import butterknife.BindView;

public class EditNoteActivity extends BaseActivity {

    @BindView(R.id.textview_edit_note_name)
    EditText textViewNoteName;
    @BindView(R.id.textview_edit_note_subject)
    EditText textViewNoteSubject;
    @BindView(R.id.button_edit_note)
    Button buttonEditNote;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        addBackButton();
        setTitle(R.string.edit_note);

        int position = getIntent().getIntExtra(NoteConstant.LISTITEMPOSITION, 0);
        init(position);

        DataHelper dataHelper = new DataHelper(EditNoteActivity.this);

        buttonEditNote.setOnClickListener(v -> {
            HomeActivity.mainModel.noteModels.set(position, new NoteModel(textViewNoteName.getText().toString(),
                    textViewNoteSubject.getText().toString()));
            dataHelper.setModel(HomeActivity.mainModel);
            finish();
        });
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_edit_note;
    }

    public void init(int position){
        textViewNoteName.setText(HomeActivity.mainModel.noteModels.get(position).getNoteName());
        textViewNoteSubject.setText(HomeActivity.mainModel.noteModels.get(position).getNoteText());
    }
}
