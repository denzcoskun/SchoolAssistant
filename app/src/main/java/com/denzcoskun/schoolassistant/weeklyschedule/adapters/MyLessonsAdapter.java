package com.denzcoskun.schoolassistant.weeklyschedule.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.denzcoskun.schoolassistant.MainActivity;
import com.denzcoskun.schoolassistant.R;
import com.denzcoskun.schoolassistant.helpers.DataHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Denx on 16.02.2018.
 */

public class MyLessonsAdapter extends BaseAdapter {

    @BindView(R.id.text_view_my_lessons_name)
    TextView textViewMyLessonsName;

    @BindView(R.id.image_button_my_lessons_delete)
    ImageButton imageButtonMyLessonsDelete;

    private Context context;
    private LayoutInflater mInflater;

    public MyLessonsAdapter(Context context) {
        this.context = context;

        mInflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return MainActivity.mainModel.lessonsNames.size();
    }

    @Override
    public String getItem(int position) {
        //şöyle de olabilir: public Object getItem(int position)
        return MainActivity.mainModel.lessonsNames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        view = mInflater.inflate(R.layout.my_lessons_row, null);
        ButterKnife.bind(this, view);


        DataHelper dataHelper = new DataHelper(context);
        imageButtonMyLessonsDelete.setOnClickListener(v -> {

            MainActivity.mainModel.lessonsNames.remove(position);
            dataHelper.setModel(MainActivity.mainModel);
            this.notifyDataSetChanged();

        });
        final String lessonName = MainActivity.mainModel.lessonsNames.get(position);


        textViewMyLessonsName.setText(lessonName);

        return view;
    }
}
