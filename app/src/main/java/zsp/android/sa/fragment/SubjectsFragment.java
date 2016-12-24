package zsp.android.sa.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import reqlib.hmi.response.ScheduleJson;
import zsp.android.sa.R;
import zsp.android.sa.adapter.SchedulesViewAdapter;
import zsp.android.sa.data.AppData;

public class SubjectsFragment extends BaseFragment {

    protected TabLayout tabLayout;
    protected ViewPager viewPager;
    protected ViewPagerAdapter adapter;
    protected ScheduleJson schedule;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        AppData.editing = false;
        AppData.changes = false;
        View view = inflater.inflate(R.layout.fragment_subjects, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    protected void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getChildFragmentManager());
        String[] days = { "ПН", "ВТ", "СР", "ЧТ", "ПТ", "СБ", "ВС" };

        for (int i = 1; i <= 7; ++i) {
            DaySubjectsFragment fragment = new DaySubjectsFragment();
            fragment.setSubjects(i);
            adapter.addFragment(fragment, days[i - 1]);
        }

        viewPager.setAdapter(adapter);
    }

    public ViewPagerAdapter getAdapter() {
        return adapter;
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        public List<Fragment> getmFragmentList() {
            return mFragmentList;
        }
    }

    public ScheduleJson getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleJson schedule) {
        this.schedule = schedule;
    }
}
