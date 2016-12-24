package zsp.android.sa.callback;

import reqlib.callback.client.ClientCallbackHandler;
import zsp.android.sa.activity.BaseActivity;
import zsp.android.sa.dialog.DialogFactory;

public abstract class BaseCallbackHandler<RES>
        implements ClientCallbackHandler<Void, RES> {

    protected BaseActivity baseActivity;

    public BaseCallbackHandler(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Override
    public boolean onSuccess(RES response) {
        return handle(response);
    }

    @Override
    public boolean onError(Void error) {
        baseActivity.showDialogPlus(DialogFactory.createErrorDialog(baseActivity,
                "Внутренняя ошибка"));

        return true;
    }

    @Override
    public boolean onConnectionError() {
        baseActivity.showDialogPlus(DialogFactory.createErrorDialog(baseActivity,
                "Ошибка соединения с сервером"));

        return true;
    }

    @Override
    public boolean onInternalError() {
        baseActivity.showDialogPlus(DialogFactory.createErrorDialog(baseActivity,
                "Внутренняя ошибка"));

        return true;
    }

    protected boolean handle(RES response) {
        return true;
    }
}
