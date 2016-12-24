package zsp.android.sa.callback;

import android.support.v7.widget.RecyclerView;

import reqlib.hmi.response.ScheduleJson;
import zsp.android.sa.R;
import zsp.android.sa.activity.BaseActivity;
import zsp.android.sa.adapter.SchedulesViewAdapter;
import zsp.android.sa.data.AppData;
import zsp.android.sa.fragment.SchedulesFragment;

public class FindCallbackHandler extends BaseCallbackHandler<ScheduleJson[]> {

    public FindCallbackHandler(BaseActivity baseActivity) {
        super(baseActivity);
    }

    @Override
    public boolean onSuccess(ScheduleJson[] response) {
        baseActivity.dismissDialogPlus();

        if (AppData.currentFragment instanceof SchedulesFragment) {
            RecyclerView rc = (RecyclerView) AppData.currentFragment.getView().findViewById(R.id.schedule_view);
            rc.setAdapter(new SchedulesViewAdapter(baseActivity, response));
            rc.invalidate();
        } else {
            SchedulesFragment fragment = new SchedulesFragment();
            baseActivity.replaceFragment(R.id.fragment_container, fragment, true, true);
            AppData.schedules = response;
        }

        return true;
    }
}
