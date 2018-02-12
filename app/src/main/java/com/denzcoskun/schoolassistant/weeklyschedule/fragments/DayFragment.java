package com.denzcoskun.schoolassistant.weeklyschedule.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.denzcoskun.schoolassistant.R;
import com.denzcoskun.schoolassistant.weeklyschedule.adapters.LessonAdapter;
import com.denzcoskun.schoolassistant.weeklyschedule.models.LessonModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Denx on 12.02.2018.
 */

public class DayFragment extends Fragment {
    private ListView listView;
    private LessonAdapter lessonAdapter;
    private List<LessonModel> lessons;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle == null) {
            lessons = new ArrayList<>();
        }else {
            lessons = (List<LessonModel>) bundle.getSerializable("deneme");
        }

        listView = (ListView) getView().findViewById(R.id.day_list);
        lessonAdapter = new LessonAdapter(getActivity(), lessons);
        listView.setAdapter(lessonAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
          /*  Intent intent = new Intent(getActivity(), sonDersDuzenle.class);
            CourseCalendar.poso = CourseCalendar.tabs.getSelectedTabPosition();
            int pos = position;
            intent.putExtra("pos", Integer.toString(pos));
            startActivity(intent);*/
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.day_fragment, viewGroup, false);
    }
}
