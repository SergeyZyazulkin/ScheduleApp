package zsp.android.sa.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import reqlib.hmi.response.ScheduleJson;
import zsp.android.sa.R;
import zsp.android.sa.data.AppData;


public class NoScheduleFragment extends SubjectsFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        AppData.editing = false;
        AppData.changes = false;
        View view = inflater.inflate(R.layout.fragment_no_schedule, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    @Override
    public void onResume() {
        if (AppData.savedSchedule != null && AppData.currentSchedule != AppData.savedSchedule) {
            AppData.currentSchedule = AppData.savedSchedule;
            AppData.editingSchedule = AppData.savedSchedule.getCopy();
        }

        AppData.schedules = null;
        super.onResume();
    }
}
