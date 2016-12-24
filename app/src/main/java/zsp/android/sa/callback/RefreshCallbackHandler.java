package zsp.android.sa.callback;

import android.support.v7.widget.RecyclerView;

import reqlib.hmi.response.ScheduleJson;
import zsp.android.sa.activity.BaseActivity;
import zsp.android.sa.adapter.SubjectsViewAdapter;
import zsp.android.sa.data.AppData;
import zsp.android.sa.fragment.DaySubjectsFragment;
import zsp.android.sa.fragment.SubjectsFragment;

public class RefreshCallbackHandler extends BaseCallbackHandler<ScheduleJson[]> {

    public RefreshCallbackHandler(BaseActivity baseActivity) {
        super(baseActivity);
    }

    @Override
    public boolean onSuccess(ScheduleJson[] response) {
        baseActivity.dismissDialogPlus();
        SubjectsFragment.ViewPagerAdapter adapter = ((SubjectsFragment) AppData.currentFragment).getAdapter();

        if (AppData.schedules != null) {
            for (int i = 0; i < AppData.schedules.length; ++i) {
                if (AppData.schedules[i].equals(AppData.currentSchedule)) {
                    AppData.schedules[i] = response[0];
                }
            }

            AppData.currentSchedule = response[0];
            AppData.editingSchedule = response[0].getCopy();
        } else {
            AppData.savedSchedule = response[0];
            baseActivity.saveSavedSchedule();
            AppData.currentSchedule = response[0];
            AppData.editingSchedule = response[0].getCopy();
        }

        for (int i = 0; i < 7; ++i) {
            DaySubjectsFragment fragment = (DaySubjectsFragment) ((SubjectsFragment) AppData.currentFragment).getAdapter().getmFragmentList().get(i);
            RecyclerView rc = fragment.getRc();

            if (rc != null) {
                rc.setAdapter(new SubjectsViewAdapter(baseActivity, i + 1));
                rc.invalidate();
            }
        }

        return true;
    }
}
