package zsp.android.sa.dialog;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import zsp.android.sa.R;
import zsp.android.sa.activity.BaseActivity;

public class DialogFactory {

    public static DialogPlus createLoginDialog(final BaseActivity activity) {
        DialogPlus dialog = DialogPlus
                .newDialog(activity)
                .setContentHolder(new ViewHolder(R.layout.dialog_login))
                .setCancelable(false)
                .setGravity(Gravity.CENTER)
                .setContentBackgroundResource(android.R.color.white)
                .create();

        final EditText etLogin = (EditText) dialog.findViewById(R.id.etLogin);
        final EditText etPassword = (EditText) dialog.findViewById(R.id.etPassword);
        Button btAuth = (Button) dialog.findViewById(R.id.btAuth);

        return dialog;
    }

    public static DialogPlus createProgressDialog(BaseActivity activity, String text) {
        DialogPlus dialog = DialogPlus
                .newDialog(activity)
                .setContentHolder(new ViewHolder(R.layout.dialog_progress))
                .setCancelable(false)
                .setGravity(Gravity.CENTER)
                .setContentBackgroundResource(android.R.color.white)
                .create();

        ((TextView) dialog.findViewById(R.id.body)).setText(text);
        return dialog;
    }

    public static DialogPlus createErrorDialog(final BaseActivity activity, String text) {
        DialogPlus dialog = DialogPlus
                .newDialog(activity)
                .setContentHolder(new ViewHolder(R.layout.dialog_error))
                .setCancelable(false)
                .setGravity(Gravity.CENTER)
                .setContentBackgroundResource(android.R.color.white)
                .create();

        ((TextView) dialog.findViewById(R.id.body)).setText(text);
        Button btClose = (Button) dialog.findViewById(R.id.btClose);

        btClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.dismissDialogPlus();
            }
        });

        return dialog;
    }
}
