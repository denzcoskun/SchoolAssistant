package com.denzcoskun.schoolassistant.screens.homework.adapters;

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
import com.denzcoskun.schoolassistant.screens.homework.models.HomeworkModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Denx on 26.02.2018.
 */

public class HomeworkAdapter extends BaseAdapter {

    @BindView(R.id.textview_homework_name)
    TextView textViewHomeworkName;

    @BindView(R.id.textview_homework_subject)
    TextView textViewHomeworkSubject;

    @BindView(R.id.textview_homework_date)
    TextView textViewHomeworkDate;

    @BindView(R.id.imagebutton_homework_delete)
    ImageButton imageButtonHomeworkDelete;

    private Context context;
    private LayoutInflater mInflater;

    public HomeworkAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return HomeActivity.mainModel.homeworkModels.size();
    }

    @Override
    public HomeworkModel getItem(int position) {
        return HomeActivity.mainModel.homeworkModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.homework_row, null);
        ButterKnife.bind(this, view);

        DataHelper dataHelper = new DataHelper(context);

        imageButtonHomeworkDelete.setOnClickListener(v -> {
            HomeActivity.mainModel.homeworkModels.remove(position);
            dataHelper.setModel(HomeActivity.mainModel);
            this.notifyDataSetChanged();

        });

        final HomeworkModel homeworkModel = HomeActivity.mainModel.homeworkModels.get(position);

        textViewHomeworkName.setText(homeworkModel.getHomeworkName());
        textViewHomeworkSubject.setText(homeworkModel.getHomeworkSubject());
        textViewHomeworkDate.setText(homeworkModel.getHomeworkDate());

        return view;
    }
}
