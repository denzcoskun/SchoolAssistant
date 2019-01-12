package com.denzcoskun.schoolassistant.screens.notes.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.denzcoskun.schoolassistant.R;
import com.denzcoskun.schoolassistant.activities.HomeActivity;
import com.denzcoskun.schoolassistant.helpers.DataHelper;
import com.denzcoskun.schoolassistant.screens.notes.models.NoteModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Denx on 5.06.2018.
 */
public class NoteAdapter extends BaseAdapter {

    @BindView(R.id.textview_note_name)
    TextView textViewNoteName;

    @BindView(R.id.textview_note_subject)
    TextView textViewNoteSubject;

    @BindView(R.id.imagebutton_note_delete)
    ImageButton imageButtonNoteDelete;

    private Context context;
    private LayoutInflater mInflater;

    public NoteAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return HomeActivity.mainModel.noteModels.size();
    }

    @Override
    public NoteModel getItem(int position) {
        return HomeActivity.mainModel.noteModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.note_row, null);
        ButterKnife.bind(this, view);

        DataHelper dataHelper = new DataHelper(context);

        imageButtonNoteDelete.setOnClickListener(v -> {
            HomeActivity.mainModel.noteModels.remove(position);
            dataHelper.setModel(HomeActivity.mainModel);
            this.notifyDataSetChanged();

        });

        final NoteModel note = HomeActivity.mainModel.noteModels.get(position);

        textViewNoteName.setText(note.getNoteName());
        textViewNoteSubject.setText(note.getNoteText());

        return view;
    }
}
