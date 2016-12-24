package zsp.android.sa.callback;

import android.support.v7.widget.RecyclerView;

import reqlib.hmi.response.ScheduleJson;
import zsp.android.sa.R;
import zsp.android.sa.activity.BaseActivity;
import zsp.android.sa.adapter.SchedulesViewAdapter;
import zsp.android.sa.data.AppData;

public class CreateCallbackHandler extends BaseCallbackHandler<ScheduleJson> {

    public CreateCallbackHandler(BaseActivity baseActivity) {
        super(baseActivity);
    }

    @Override
    public boolean onSuccess(ScheduleJson response) {
        baseActivity.dismissDialogPlus();
        ScheduleJson[] schedules = new ScheduleJson[AppData.schedules.length + 1];

        for (int i = 0; i < AppData.schedules.length; ++i) {
            schedules[i] = AppData.schedules[i];
        }

        schedules[AppData.schedules.length] = response;
        AppData.schedules = schedules;
        RecyclerView rc = (RecyclerView) AppData.currentFragment.getView().findViewById(R.id.schedule_view);
        rc.setAdapter(new SchedulesViewAdapter(baseActivity, schedules));
        rc.invalidate();
        return true;
    }
}
