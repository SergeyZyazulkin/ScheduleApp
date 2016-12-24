package zsp.android.sa.callback;

import android.widget.TextView;

import reqlib.hmi.request.AuthenticateRequest;
import reqlib.hmi.response.SignUpResponse;
import zsp.android.sa.R;
import zsp.android.sa.activity.BaseActivity;
import zsp.android.sa.data.AppData;
import zsp.android.sa.dialog.DialogFactory;

public class SignUpCallbackHandler extends BaseCallbackHandler<SignUpResponse> {

    public SignUpCallbackHandler(BaseActivity baseActivity) {
        super(baseActivity);
    }

    @Override
    public boolean onSuccess(SignUpResponse response) {
        if (response.isSuccess()) {
            AuthenticateRequest authenticateRequest = new AuthenticateRequest(
                    AppData.savedLogin,
                    AppData.savedPassword
            );

            ((TextView) baseActivity.getCurrentDialogPlus().findViewById(R.id.body))
                    .setText("Выполнение авторизации...");

            AppData.clientInterface.authenticate(authenticateRequest,
                    new AuthenticateCallbackHandler(baseActivity));
        } else {
            baseActivity.showDialogPlus(DialogFactory.createErrorDialog(
                    baseActivity, "Пользователь с таким email уже существует"));
        }

        return true;
    }
}
