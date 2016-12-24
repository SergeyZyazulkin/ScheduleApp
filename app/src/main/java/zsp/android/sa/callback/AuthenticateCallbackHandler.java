package zsp.android.sa.callback;

import reqlib.hmi.response.AuthenticateResponse;
import reqlib.hmi.response.SignUpResponse;
import zsp.android.sa.activity.BaseActivity;
import zsp.android.sa.data.AppData;
import zsp.android.sa.dialog.DialogFactory;
import zsp.android.sa.util.StateController;

public class AuthenticateCallbackHandler extends BaseCallbackHandler<AuthenticateResponse> {

    public AuthenticateCallbackHandler(BaseActivity baseActivity) {
        super(baseActivity);
    }

    @Override
    public boolean onSuccess(AuthenticateResponse response) {
        if (response.isSuccess()) {
            String login = AppData.savedLogin;
            AppData.token = response.getToken();
            AppData.savedLogin = null;
            AppData.savedPassword = null;
            baseActivity.setUserName(login);
            baseActivity.dismissDialogPlus();
        } else {
            baseActivity.showDialogPlus(DialogFactory.createErrorDialog(
                    baseActivity, "Неверный почтовый адрес/пароль пользователя"));
        }

        return true;
    }
}
