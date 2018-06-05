package com.denzcoskun.schoolassistant.screens.exams.adapters;

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
import com.denzcoskun.schoolassistant.screens.exams.models.ExamModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Denx on 30.05.2018.
 */
public class ExamAdapter extends BaseAdapter {

    @BindView(R.id.textview_exam_name)
    TextView textViewExamName;

    @BindView(R.id.textview_exam_subject)
    TextView textViewExamSubject;

    @BindView(R.id.textview_exam_date)
    TextView textViewExamDate;

    @BindView(R.id.textview_exam_time)
    TextView textViewExamTime;

    @BindView(R.id.imagebutton_exam_delete)
    ImageButton imageButtonExamDelete;

    private Context context;
    private LayoutInflater mInflater;

    public ExamAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return HomeActivity.mainModel.examModels.size();
    }

    @Override
    public ExamModel getItem(int position) {
        return HomeActivity.mainModel.examModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.exam_row, null);
        ButterKnife.bind(this, view);

        DataHelper dataHelper = new DataHelper(context);

        imageButtonExamDelete.setOnClickListener(v -> {
            HomeActivity.mainModel.examModels.remove(position);
            dataHelper.setModel(HomeActivity.mainModel);
            this.notifyDataSetChanged();

        });

        final ExamModel examModel = HomeActivity.mainModel.examModels.get(position);

        textViewExamName.setText(examModel.getExamName());
        textViewExamSubject.setText(examModel.getExamSubject());
        textViewExamDate.setText(examModel.getExamDate());
        textViewExamTime.setText(examModel.getExamTime());

        return view;
    }
}