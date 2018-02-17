package com.denzcoskun.schoolassistant.weeklyschedule.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.denzcoskun.schoolassistant.MainActivity;
import com.denzcoskun.schoolassistant.R;
import com.denzcoskun.schoolassistant.weeklyschedule.activities.EditLessonActivity;
import com.denzcoskun.schoolassistant.weeklyschedule.adapters.LessonAdapter;
import com.denzcoskun.schoolassistant.weeklyschedule.constants.LessonConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Denx on 12.02.2018.
 */

public class DayFragment extends Fragment {
    @BindView(R.id.day_list)
    ListView listView;

    private int position;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = this.getArguments();
        position = bundle.getInt(LessonConstants.POSITION);

        listView = (ListView) getView().findViewById(R.id.day_list);
        MainActivity.lessonAdapters[position] = new LessonAdapter(getActivity(), MainActivity.mainModel.dayModels.get(position).getLessons());
        listView.setAdapter(MainActivity.lessonAdapters[position]);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getActivity(), EditLessonActivity.class);
            int listItemPosition = position;
            intent.putExtra(LessonConstants.LISTITEMPOSITION, listItemPosition);
            intent.putExtra(LessonConstants.POSITION, this.position);
            startActivity(intent);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.day_fragment, viewGroup, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
