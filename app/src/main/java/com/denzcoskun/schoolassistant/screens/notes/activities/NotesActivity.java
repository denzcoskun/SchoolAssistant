package com.denzcoskun.schoolassistant.screens.notes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.widget.ListView;

import com.denzcoskun.libdenx.activities.BaseActivity;
import com.denzcoskun.schoolassistant.R;
import com.denzcoskun.schoolassistant.activities.HomeActivity;
import com.denzcoskun.schoolassistant.screens.homework.activities.HomeworkActivity;
import com.denzcoskun.schoolassistant.screens.notes.adapters.NoteAdapter;
import com.denzcoskun.schoolassistant.screens.notes.constants.NoteConstant;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Denx on 5.06.2018.
 */
public class NotesActivity extends BaseActivity {

    @BindView(R.id.listview_notes)
    ListView listviewNotes;

    @BindView(R.id.note_add_button)
    FloatingActionButton noteAddButton;

    NoteAdapter noteAdapter;

    @Override
    protected void onViewReady(Bundle saedInstanceState, Intent intent) {
        super.onViewReady(saedInstanceState, intent);
        addBackButton();
        setTitle(R.string.notes);

        if (HomeActivity.mainModel.noteModels == null){
            initNotes();
        }

        noteAdapter = new NoteAdapter(NotesActivity.this);

        listviewNotes.setAdapter(noteAdapter);
        listviewNotes.setClickable(false);
        listviewNotes.setOnItemClickListener((parent, view, position, id) -> {
            Intent i = new Intent(this, EditNoteActivity.class);
            i.putExtra(NoteConstant.LISTITEMPOSITION, position);
            startActivity(i);
        });

        noteAddButton.setOnClickListener(v -> startActivity(new Intent(NotesActivity.this, AddNoteActivity.class)));

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_notes;
    }

    public void initNotes(){
        HomeActivity.mainModel.noteModels = new ArrayList<>();
    }

    @Override
    public void onResume() {
        super.onResume();
        noteAdapter.notifyDataSetChanged();
    }
}
