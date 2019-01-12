package com.denzcoskun.schoolassistant.screens.absence.adapters;

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
import com.denzcoskun.schoolassistant.screens.absence.models.AbsenceModel;
import com.denzcoskun.schoolassistant.screens.exams.models.ExamModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Denx on 24.06.2018.
 */
public class AbsenceAdapter extends BaseAdapter {

    @BindView(R.id.textview_absence_name)
    TextView textViewAbsenceName;

    @BindView(R.id.textview_absence_kind)
    TextView textViewAbsenceKind;

    @BindView(R.id.textview_absence_status)
    TextView textViewAbsenceStatus;

    @BindView(R.id.textview_absence_date)
    TextView textViewAbsenceDate;

    @BindView(R.id.imagebutton_absence_delete)
    ImageButton imageButtonAbsenceDelete;

    private Context context;
    private LayoutInflater mInflater;

    public AbsenceAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return HomeActivity.mainModel.absenceModels.size();
    }

    @Override
    public AbsenceModel getItem(int position) {
        return HomeActivity.mainModel.absenceModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.absence_row, null);
        ButterKnife.bind(this, view);

        DataHelper dataHelper = new DataHelper(context);

        imageButtonAbsenceDelete.setOnClickListener(v -> {
            HomeActivity.mainModel.absenceModels.remove(position);
            dataHelper.setModel(HomeActivity.mainModel);
            this.notifyDataSetChanged();

        });

        final AbsenceModel absenceModel = HomeActivity.mainModel.absenceModels.get(position);

        textViewAbsenceName.setText(absenceModel.getCourseName());
        textViewAbsenceDate.setText(absenceModel.getTime());
        if (absenceModel.isKind()){
            textViewAbsenceKind.setText(context.getString(R.string.all_day));
        }else {
            textViewAbsenceKind.setText(context.getString(R.string.half_day));
        }
        if (absenceModel.isStatus()){
            textViewAbsenceStatus.setText(context.getString(R.string.excused));
        }else {
            textViewAbsenceStatus.setText(context.getString(R.string.unexcused));
        }

        return view;
    }
}
