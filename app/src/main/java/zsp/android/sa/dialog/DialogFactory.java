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
                .setCancelable(true)
                .setGravity(Gravity.CENTER)
                .setContentBackgroundResource(android.R.color.white)
                .create();

        final EditText etLogin = (EditText) dialog.findViewById(R.id.etLogin);
        final EditText etPassword = (EditText) dialog.findViewById(R.id.etPassword);
        Button btAuth = (Button) dialog.findViewById(R.id.btAuth);

        return dialog;
    }

    public static DialogPlus createRegisterDialog(final BaseActivity activity) {
        DialogPlus dialog = DialogPlus
                .newDialog(activity)
                .setContentHolder(new ViewHolder(R.layout.dialog_register))
                .setCancelable(true)
                .setGravity(Gravity.CENTER)
                .setContentBackgroundResource(android.R.color.white)
                .create();

        final EditText etLogin = (EditText) dialog.findViewById(R.id.etLogin);
        final EditText etPassword = (EditText) dialog.findViewById(R.id.etPassword);
        final EditText etRepeatPassword = (EditText) dialog.findViewById(R.id.etRepeatPassword);
        Button btRegister = (Button) dialog.findViewById(R.id.btRegister);

        return dialog;
    }

    public static DialogPlus createSubjectDialog(final BaseActivity activity) {
        DialogPlus dialog = DialogPlus
                .newDialog(activity)
                .setContentHolder(new ViewHolder(R.layout.dialog_subject))
                .setCancelable(true)
                .setGravity(Gravity.CENTER)
                .setContentBackgroundResource(android.R.color.white)
                .create();

        final TextView tvSubject = (TextView) dialog.findViewById(R.id.tvSubject);
        tvSubject.setText("Предмет:");
        final TextView tvTime = (TextView) dialog.findViewById(R.id.tvTime);
        tvTime.setText("Время:");
        final TextView tvProfessor = (TextView) dialog.findViewById(R.id.tvProfessor);
        tvProfessor.setText("Преподаватель:");
        final TextView tvCabinet = (TextView) dialog.findViewById(R.id.tvCabinet);
        tvCabinet.setText("Кабинет:");
        final TextView tvInfo = (TextView) dialog.findViewById(R.id.tvInfo);
        tvInfo.setText("Описание:");
        final EditText etSubject = (EditText) dialog.findViewById(R.id.etSubject);
        etSubject.setText("ЧМИ (лекция)");
        final EditText etTime = (EditText) dialog.findViewById(R.id.etTime);
        etTime.setText("13:00");
        final EditText etProfessor = (EditText) dialog.findViewById(R.id.etProfessor);
        etProfessor.setText("Давидовская М.И.");
        final EditText etCabinet = (EditText) dialog.findViewById(R.id.etCabinet);
        etCabinet.setText("508");
        final EditText etInfo = (EditText) dialog.findViewById(R.id.etInfo);
        etInfo.setText("");
        Button btClose = (Button) dialog.findViewById(R.id.btClose);
        Button btSave = (Button) dialog.findViewById(R.id.btSave);

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
