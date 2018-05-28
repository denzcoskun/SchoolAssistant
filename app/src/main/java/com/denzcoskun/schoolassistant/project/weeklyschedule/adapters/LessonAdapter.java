package com.denzcoskun.schoolassistant.project.weeklyschedule.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.denzcoskun.schoolassistant.R;
import com.denzcoskun.schoolassistant.project.weeklyschedule.models.LessonModel;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Denx on 12.02.2018.
 */

public class LessonAdapter extends BaseAdapter {

    @BindView(R.id.random_lesson_color)
    LinearLayout randomLessonColor;

    @BindView(R.id.lesson_name)
    TextView lessonName;

    @BindView(R.id.lesson_time)
    TextView lessonTime;

    @BindView(R.id.lesson_classroom)
    TextView lessonClassroom;

    @BindView(R.id.lesson_delete)
    ImageButton lessonDeleteButton;

    private LayoutInflater mInflater;
    private List<LessonModel> lessons;

    public LessonAdapter(Activity activity, List<LessonModel> lessons) {
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        this.lessons = lessons;
    }

    @Override
    public int getCount() {
        return lessons.size();
    }

    @Override
    public LessonModel getItem(int position) {
        return lessons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view= convertView;
        view = mInflater.inflate(R.layout.day_lesson_row, null);
        ButterKnife.bind(this,view);



        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

        randomLessonColor.setBackgroundColor(color);

        lessonDeleteButton.setOnClickListener(click-> {
                lessons.remove(position);
                this.notifyDataSetChanged();
        });

        final LessonModel lesson = lessons.get(position);

        lessonName.setText(lesson.getName());
        lessonTime.setText(lesson.getStartTime()+" - "+lesson.getFinishTime());
        lessonClassroom.setText(lesson.getClassroom());

        return view;
    }
}
