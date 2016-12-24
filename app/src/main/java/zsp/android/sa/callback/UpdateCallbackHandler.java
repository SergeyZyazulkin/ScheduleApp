package zsp.android.sa.callback;

import reqlib.hmi.request.FindRequest;
import reqlib.hmi.response.ScheduleJson;
import zsp.android.sa.R;
import zsp.android.sa.activity.BaseActivity;
import zsp.android.sa.data.AppData;
import zsp.android.sa.dialog.DialogFactory;
import zsp.android.sa.fragment.NoScheduleFragment;
import zsp.android.sa.util.StateController;

public class UpdateCallbackHandler extends BaseCallbackHandler<ScheduleJson> {

    public UpdateCallbackHandler(BaseActivity baseActivity) {
        super(baseActivity);
    }

    @Override
    public boolean onSuccess(ScheduleJson response) {
        baseActivity.dismissDialogPlus();
        AppData.editing = !AppData.editing;
        AppData.changes = false;

        if (AppData.schedules != null) {
            for (int i = 0; i < AppData.schedules.length; ++i) {
                if (AppData.schedules[i].equals(AppData.currentSchedule)) {
                    AppData.schedules[i] = AppData.editingSchedule;
                }
            }
        }

        if (AppData.savedSchedule != null && AppData.currentSchedule.equals(AppData.savedSchedule)) {
            AppData.savedSchedule = AppData.editingSchedule;
            baseActivity.saveSavedSchedule();
        }

        AppData.currentSchedule = AppData.editingSchedule;

        if (AppData.logout) {
            baseActivity.setUserName(null);
        } else if (AppData.startSearch) {
            FindRequest findRequest = new FindRequest(AppData.search);
            baseActivity.showDialogPlus(DialogFactory.createProgressDialog(baseActivity, "Поиск расписаний..."));
            AppData.clientInterface.find(findRequest, new FindCallbackHandler(baseActivity));
        } else {
            if (AppData.pop) {
                baseActivity.getSupportFragmentManager().popBackStack();
                AppData.pop = false;
            }

            if (AppData.home) {
                baseActivity.replaceFragment(R.id.fragment_container, new NoScheduleFragment(), false, true);
                AppData.home = false;
            }
        }

        StateController.update(baseActivity);
        return true;
    }
}
