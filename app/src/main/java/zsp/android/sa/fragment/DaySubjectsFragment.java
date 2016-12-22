package zsp.android.sa.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import zsp.android.sa.R;
import zsp.android.sa.adapter.SubjectsViewAdapter;

public class DaySubjectsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_day_subjects, container, false);
        RecyclerView rc = ((RecyclerView) view.findViewById(R.id.subject_view));
        rc.setLayoutManager(new LinearLayoutManager(getActivity()));
        rc.setAdapter(new SubjectsViewAdapter(getActivity()));
        return view;
    }
}
