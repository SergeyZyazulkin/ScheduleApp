package zsp.android.sa.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import zsp.android.sa.R;
import zsp.android.sa.activity.BaseActivity;
import zsp.android.sa.activity.MainActivity;
import zsp.android.sa.adapter.SubjectsViewAdapter;
import zsp.android.sa.data.AppData;
import zsp.android.sa.dialog.DialogFactory;
import zsp.android.sa.util.StateController;

public class DaySubjectsFragment extends Fragment {

    private RecyclerView rc;
    private int day;
    private FloatingActionButton fbAddSubject;
    private FloatingActionButton fbSaveSchedule;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_day_subjects, container, false);
        rc = ((RecyclerView) view.findViewById(R.id.subject_view));
        rc.setLayoutManager(new LinearLayoutManager(getActivity()));
        rc.setAdapter(new SubjectsViewAdapter(getActivity(), day));

        fbAddSubject = (FloatingActionButton) view.findViewById(R.id.fbAddSubject);
        fbSaveSchedule = (FloatingActionButton) view.findViewById(R.id.fbSaveSchedule);

        fbAddSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BaseActivity) getActivity()).showDialogPlus(
                        DialogFactory.createAddSubjectDialog((BaseActivity) getActivity(), day, rc));
            }
        });

        fbSaveSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppData.savedSchedule = AppData.currentSchedule;
                ((BaseActivity) getActivity()).saveSavedSchedule();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((SubjectsFragment) AppData.currentFragment).getAdapter().getmFragmentList().set(day - 1, this);
        StateController.update((MainActivity) getActivity());
    }

    public RecyclerView getRc() {
        return rc;
    }

    public void setSubjects(int day) {
        this.day = day;
    }

    public FloatingActionButton getFbAddSubject() {
        return fbAddSubject;
    }

    public FloatingActionButton getFbSaveSchedule() {
        return fbSaveSchedule;
    }
}
