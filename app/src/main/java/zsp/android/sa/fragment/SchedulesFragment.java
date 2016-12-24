package zsp.android.sa.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import reqlib.hmi.response.ScheduleJson;
import zsp.android.sa.R;
import zsp.android.sa.activity.BaseActivity;
import zsp.android.sa.adapter.SchedulesViewAdapter;
import zsp.android.sa.adapter.SubjectsViewAdapter;
import zsp.android.sa.data.AppData;
import zsp.android.sa.dialog.DialogFactory;


public class SchedulesFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_schedules, container, false);
        RecyclerView rc = ((RecyclerView) view.findViewById(R.id.schedule_view));
        rc.setLayoutManager(new LinearLayoutManager(getActivity()));
        rc.setAdapter(new SchedulesViewAdapter(getActivity(), AppData.schedules));

        view.findViewById(R.id.fbAddSchedule).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BaseActivity) getActivity()).showDialogPlus(DialogFactory.createAddScheduleDialog((BaseActivity) getActivity()));
            }
        });

        return view;
    }
}
